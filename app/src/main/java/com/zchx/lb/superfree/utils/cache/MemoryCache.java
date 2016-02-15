package com.zchx.lb.superfree.utils.cache;


import android.support.v4.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.Map;

/**
 * Created by
 * Author: wswenyue
 * Email: wswenyue@163.com
 * Date: 2015/10/12
 */

/**
 * 内存缓存
 */
public final class MemoryCache {
    private static final int LRU_CACHE_SIZE = 2 * 1024 * 1024;//默认2M。一级缓存的大小，一级缓存在内存中获取最快，但空间较小
    //强引用缓存，一级缓存，特点使用最近最少使用的算法，将旧数据移除，为新数据提供空间
    private static LruCache<String, byte[]> lruCache;
    private static Map<String, SoftReference<byte[]>> softCache;//软引用缓存，二级缓存
    private static MemoryCache memoryCache;
    private static Integer mcaheSize;

    public static MemoryCache initialize(Integer cacheSizeFactorial) {
        if (memoryCache == null) {
            memoryCache = new MemoryCache();
        }
        mcaheSize = cacheSizeFactorial;
        initLruCache();

        return memoryCache;
    }

    public static MemoryCache getInstance() {
        if (memoryCache == null) {
            throw new IllegalStateException("initialize must be invoke before this method!!!");
        }
        return memoryCache;
    }

    private MemoryCache() {
    }

    private static void initLruCache() {
        if (mcaheSize == null || mcaheSize <= 1) {
            mcaheSize = LRU_CACHE_SIZE;
        } else {
            mcaheSize = LRU_CACHE_SIZE * mcaheSize;
        }
        lruCache = new LruCache<String, byte[]>(mcaheSize) {
            @Override
            protected int sizeOf(String key, byte[] value) {
                //计算存放成员的大小，返回字节大小
                return value.length;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, byte[] oldValue, byte[] newValue) {
                //移除旧数据，并将旧数据放入到二级缓存中
                softCache.put(key, new SoftReference<byte[]>(oldValue));
                super.entryRemoved(evicted, key, oldValue, newValue);
            }

        };
    }

    public byte[] getData(String url) {
        byte[] ret = null;
        //从缓存中获取数据
        //先从一级缓存中获取数据
        byte[] bytes = lruCache.get(url);
        if (bytes == null) {
            //从二级缓存中获取数据
            SoftReference<byte[]> reference = softCache.get(url);
            if (reference != null) {
                bytes = reference.get();//如果为空说明被gc回收
                if (bytes != null) {//软引用有可能被内存回收
                    //将图片对象存放到一级缓存中
                    lruCache.put(url, bytes);
                    //从二级缓存中移除
                    softCache.remove(reference);
                } else {//被gc回收，那么从扩展卡读取
                    //从三级缓存中读取数据---扩展卡
                    bytes = FileCache.getInstance().load(url);
                    if (bytes != null) {
                        //将图片存放到一级缓存
                        lruCache.put(url, bytes);
                    }
                }
            } else {//TODO 这里待考证，如果二级缓存中没有url这个数据项，SD卡中会有吗？
                //从三级缓存中读取数据---扩展卡
                bytes = FileCache.getInstance().load(url);
                if (bytes != null) {
                    //将图片存放到一级缓存
                    lruCache.put(url, bytes);
                }
            }
            ret = bytes;
        }
        return ret;
    }


    public void saveData(String url, byte[] data) {
        //将图片存放到一级缓存中
        lruCache.put(url, data);
    }

}

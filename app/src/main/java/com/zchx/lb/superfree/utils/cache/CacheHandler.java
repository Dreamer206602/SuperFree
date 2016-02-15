package com.zchx.lb.superfree.utils.cache;

/**
 * Created by
 * Author: wswenyue
 * Email: wswenyue@163.com
 * Date: 2015/10/12
 */

/**
 * Cache的操作
 */
public class CacheHandler {
    public static void saveData(String url, byte[] data) {
        //存内存
        MemoryCache.getInstance().saveData(url, data);
        FileCache.getInstance().save(url, data);
    }

    public static byte[] getData(String url) {
        byte[] ret = null;
        ret = MemoryCache.getInstance().getData(url);
        if (ret == null) {
            ret = FileCache.getInstance().load(url);
        }
        return ret;
    }
}

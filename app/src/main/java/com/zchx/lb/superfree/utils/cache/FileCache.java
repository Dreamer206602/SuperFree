package com.zchx.lb.superfree.utils.cache;

import android.content.Context;
import android.os.Environment;


import com.zchx.lb.superfree.utils.EncodingUtils;
import com.zchx.lb.superfree.utils.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by
 * Author: wswenyue
 * Email: wswenyue@163.com
 * Date: 2015/10/12
 */

/**
 * 文件缓存类
 */
public class FileCache {
    private static FileCache ourInstance;

    public static FileCache newInstance(Context context) {
        if (context != null) {
            if (ourInstance == null) {
                ourInstance = new FileCache(context);
            }
        } else {
            throw new IllegalArgumentException("Context must be set.");
        }
        return ourInstance;
    }

    public static FileCache getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException("newInstance invoke");
        }
        return ourInstance;
    }

    private Context context;

    private FileCache(Context context) {
        this.context = context;
    }

    /**
     * 从文件存储加载对应网址的内容
     *
     * @param url
     * @return
     */
    public byte[] load(String url) {
        //通过网址找文件
        byte[] ret = null;
        if (url != null) {
            //最终获取出来的文件缓存目录
            File cacheDir = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                //获取存储卡上面应用程序的缓存目录
                cacheDir = context.getExternalCacheDir();
            } else {
                //内部存储缓存
                cacheDir = context.getCacheDir();
            }
            //映射文件名称
            String fileName = EncodingUtils.toHashStr(url);
            File targetFile = new File(cacheDir, fileName);
            if (targetFile.exists()) {
                ret = getBytes(ret, targetFile);

            }
        }
        return ret;
    }

    public static byte[] getBytes(byte[] ret, File targetFile) {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(targetFile);

            ret = StreamUtils.readStream(fin);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            StreamUtils.close(fin);
        }
        return ret;
    }

    /***
     * 保存对应网址的数据到文件中
     *
     * @param url
     * @param data
     */
    public void save(String url, byte[] data) {
        //通过网址存文件

        if (url != null && data != null) {
            //最终获取出来的文件缓存目录
            File cacheDir = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                //获取存储卡上面应用程序的缓存目录
                cacheDir = context.getExternalCacheDir();
            } else {
                //内部存储缓存
                cacheDir = context.getCacheDir();
            }

            //映射文件名称
            String fileName = EncodingUtils.toHashStr(url);
            File targetFile = new File(cacheDir, fileName);
            //IO
            FileOutputStream fout = null;
            try {
                fout = new FileOutputStream(targetFile);
                fout.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtils.close(fout);
            }
        }
    }

}

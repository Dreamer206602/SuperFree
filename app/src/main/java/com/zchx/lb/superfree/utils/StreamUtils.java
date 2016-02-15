package com.zchx.lb.superfree.utils;

/**
 * Created by
 * Author: wswenyue
 * Email: wswenyue@163.com
 * Date: 2015/10/12
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;

/**
 * IO流的工具类
 */
public final class StreamUtils {
    private StreamUtils() {
    }

    /**
     * 关闭IO流
     *
     * @param stream
     */
    public static void close(Object stream) {
        if (stream != null) {
            try {
                if (stream instanceof InputStream) {
                    ((InputStream) stream).close();
                } else if (stream instanceof OutputStream) {
                    ((OutputStream) stream).close();
                } else if (stream instanceof Reader) {
                    ((Reader) stream).close();
                } else if (stream instanceof Writer) {
                    ((Writer) stream).close();
                } else if (stream instanceof HttpURLConnection) {
                    ((HttpURLConnection) stream).disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 从inputStream流中读取数据到字节数组
     *
     * @param inputStream 读取流
     * @return 返回字节数组
     * @throws IOException
     */
    public static byte[] readStream(InputStream inputStream) throws IOException {
        byte[] ret = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[4 * 1024];
        int len = -1;
        while ((len = inputStream.read(buf)) != -1) {
            bos.write(buf, 0, len);
        }
        //注意 buf必须要进行 = null的操作
        //减少内存溢出的可能性
        buf = null;
        ret = bos.toByteArray();
        return ret;
    }



}

package com.zchx.lb.superfree.utils;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by
 * Author: wswenyue
 * Email: wswenyue@163.com
 * Date: 2015/10/12
 */

/**
 * 编解码工具
 */
public final class EncodingUtils {
    private EncodingUtils() {
    }

    /**
     * 对数据（字节）进行Base64编码
     *
     * @param data 要编码的数据（字节数组）
     * @return 返回编码后的字符串
     */
    public static String Base64Encode(byte[] data) {
        String ret = null;
        if (data != null && data.length > 0) {
            ret = Base64.encodeToString(data, Base64.NO_WRAP);
        }
        return ret;
    }

    /**
     * 对Base64编码后的数据进行还原
     *
     * @param data 使用Base64编码过的数据
     * @return 返回还原后的数据（字节数组）
     */
    public static byte[] Base64Decode(String data) {
        byte[] ret = null;
        if (data != null && data.length() > 0) {
            ret = Base64.decode(data, Base64.NO_WRAP);
        }
        return ret;
    }


    /**
     * 将网址映射为文件名
     *
     * @param stringContext
     * @return
     */
    public static String md5(String stringContext) {
        String ret = null;
        if (stringContext != null) {
            try {
                //创建消息摘要对象使用MD5算法
                MessageDigest digest = MessageDigest.getInstance("MD5");
                //计算url对应的MD5数据，生成的数据是字节数组
                //内部包含了不可显示的字节，需要进行编码，才可以转换成字符串
                //不要使用new String(byte[])!!!
                //需要转换成十六进制内容
                byte[] data = digest.digest(stringContext.getBytes());
                ret = toHex(data);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 使用MD5获取数据的摘要信息
     *
     * @param data 数据
     * @return 摘要信息
     */
    public static String toMD5(byte[] data) {
        String ret = null;
        try {
            byte[] digest = MessageDigest.getInstance("md5").digest(data);
            ret = Base64Encode(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 将字节数据转换为16进制编码的字符串
     *
     * @param data
     * @return
     */
    public static String toHex(byte[] data) {
        String ret = null;
        if (data != null && data.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (byte b : data) {
                int v = b & 0x0FF;
                String hexString = Integer.toHexString(v);
                if (v > 0x0F) {
                    //0x3C -> "3C"
                    sb.append(hexString);
                } else {
                    sb.append('0').append(hexString);
                }
            }
            ret = sb.toString();
        }
        return ret;
    }


    /**
     * 获取url唯一的映射字符串<p/>
     * 这个方法是参考Velloy中对于url映射的处理。
     *
     * @param url url地址
     * @return 唯一的映射字符串
     */
    public static String toHashStr(String url) {
        String ret = null;
        if (url == null || url.trim().equals("")) {
            throw new IllegalArgumentException("url not null");
        } else {
            int length = url.length();
            String substring = url.substring(0, length / 2);
            String substring1 = url.substring(length / 2);
            ret = substring.hashCode() + "" + substring1.hashCode();
        }
        return ret;
    }


}

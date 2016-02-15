package com.zchx.lb.superfree.utils;

/**
 * Created by
 * Author: wswenyue
 * Email: wswenyue@163.com
 * Date: 2015/10/13
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Bitmap的工具类
 * 包含对图片的二次采样
 */
public class BitmapUtils {
    /**
     * 图片的二次采样
     *
     * @param data
     * @param mRequestWidth
     * @param mRequestHeight
     * @return
     */
    public static Bitmap bitmapSampler(byte[] data, int mRequestWidth, int mRequestHeight) {
        Bitmap ret = null;
        //按照原始的图片尺寸，进行bitmap的生成
        //Bitmap生成，是按照图片原始宽高，进行生成，并且每个像素占用4个字节，即ARGB

        //采用二次采样（缩小图片尺寸的方式）
        //1、步骤1 获取原始图片的宽高信息，用于进行采样的计算
        //创建Options，给BitmapFactory的内部解码器传递参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置inJustDecodeBounds来控制解码器，来控制解码器，只进行图片宽度的获取，不会加载Bitmap
        //不占用内存，当使用这个参数，代表BitmapFactory.decodeXXX 类似的方法，不会返回Bitmap
        options.inJustDecodeBounds = true;
        //解码，使用Options参数设置，解码方式
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        //步骤2，根据图片的真实尺寸，与当前需要显示的尺寸，进行计算，生成图片采样率。

        //准备 显示在 手机上的尺寸
        // 尺寸是根据程序需要来设置的。

        //计算 设置 图片采样率
        options.inSampleSize =
                calculateInSampleSize(options, mRequestWidth, mRequestHeight);

        //开放 解码，实际生成Bitmap
        options.inJustDecodeBounds = false;

        //要求解码器对于每一个采样的像素，使用RGB_565
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        //一个过时的设置
//        options.inPurgeable = true;

        ret = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        return ret;
    }

    /**
     * 计算图片二次采样的采样率，使用获取图片宽高之后的Options作为第一个参数；
     * 并且，通过请求的 宽度 、高度尺寸，进行采样率的计算
     *
     * @param options
     * @param reqWidth  请求的宽度
     * @param reqHeight 请求的高度
     * @return int 采样率
     */
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        //如果
        if (reqHeight > 0 && reqWidth > 0) {
            if (height > reqHeight || width > reqWidth) {

                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) >= reqHeight
                        && (halfWidth / inSampleSize) >= reqWidth) {
                    inSampleSize *= 2;
                }
            }
        }


        return inSampleSize;
    }
}

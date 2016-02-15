package com.zchx.lb.superfree.utils.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;


import com.zchx.lb.superfree.utils.EncodingUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wswenyue on 2015/9/14.
 */
public final class SDCardUtils {
    //用到的常量：
    public static final int FORMAT_PNG = 1;
    public static final int FORMAT_JPEG = 6;
    public static final int FORMAT_WEBP = 4;
    public static final String IMAGE_URL = Environment
            .getExternalStorageDirectory() + File.separator + "images";

    public static final int IMAGE_QUALITY = 70;//图片压缩后的质量
    private static final String TAG = "SDCardUtils";

    private SDCardUtils() {
    }

    /**
     * 判断扩展卡是否挂载
     *
     * @return
     */
    public static boolean isMounted() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取扩展卡的剩余空间，并返回，上层如果需要判断空间够不够用，可以在上层处理
     */
    public static int getAble() {
        //文件系统状态管理对象
        StatFs fs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        int count = fs.getFreeBlocks();//空闲的数据块个数
        int size = fs.getBlockSize();//返回每个数据块的大小

        //剩余空间大小
        long total = count * size;//单位是字节
        int t = (int) (total / 1024 / 1024);//返回单位为M（以便处理）
        return t;
    }

    /**
     * 根据文件的下载路径获取文件名
     *
     * @param url
     * @return
     */
    public static String getFileName(String url) {
        return EncodingUtils.toHashStr(url) + ".jpg";
    }

    /**
     * 保存图片到扩展卡的功能
     *
     * @throws IOException
     */
    public static void saveImage(String url, Bitmap bitmap) throws FileNotFoundException {
        saveImage(url, bitmap, FORMAT_WEBP);
    }

    public static void saveImage(String url, byte[] data) throws IOException { // 判断扩展卡是否挂载
        if (!isMounted())
            return;
        // 判断存储目录是否存在
        File dir = new File(IMAGE_URL);
        if (!dir.exists())
            dir.mkdirs();

        // 把图片数据写入到一个图片文件
        FileOutputStream fos = new FileOutputStream(new File(dir,
                getFileName(url)));
        fos.write(data);

        fos.close();

    }


    /**
     * 保存图片到扩展卡的功能
     *
     * @throws FileNotFoundException
     */
    public static void saveImage(String url, Bitmap bitmap, Integer format)
            throws FileNotFoundException {
        // 判断扩展卡是否挂载
        if (!isMounted())
            return;

        // 判断存储目录是否存在
        File dir = new File(IMAGE_URL);
        if (!dir.exists())
            dir.mkdirs();
        // 把图片数据写入到一个图片文件
        FileOutputStream fos = new FileOutputStream(new File(dir,
                getFileName(url)));

        // 图片的压缩 CompressFormat.PNG:压缩之后的格式
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.WEBP;
        switch (format) {
            case FORMAT_JPEG:
                compressFormat = Bitmap.CompressFormat.JPEG;
                break;
            case FORMAT_PNG:
                compressFormat = Bitmap.CompressFormat.PNG;
                break;
            default:
                compressFormat = Bitmap.CompressFormat.WEBP;
                break;
        }

        bitmap.compress(compressFormat, IMAGE_QUALITY, fos);
    }

    /**
     * 从扩展卡读取图片的功能
     */
    public static Bitmap readImage(String url) {
        // 判断扩展卡是否挂载
        if (!isMounted())
            return null;

        String filename = getFileName(url);
        File file = new File(IMAGE_URL, filename);
        Bitmap bitmap = null;
        if (file.exists())
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (bitmap == null) {
            Log.d(TAG, "readImage 获取sd卡图片--->图片不存在" + url);
        }
        return bitmap;
    }
}

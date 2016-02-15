package com.zchx.lb.superfree.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolsUtil {

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 验证银卡卡号
     *
     * @param cardNo
     * @return
     */
    public static boolean isBankCard(String cardNo) {
        Pattern p = Pattern.compile("^\\d{16,19}$|^\\d{6}[- ]\\d{10,13}$|^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4,7}$");
        Matcher m = p.matcher(cardNo);

        return m.matches();
    }

    /**
     * 身份证验证
     * @param idCard
     * @return
     */
    public static boolean validateIdCard(String idCard) {
        //15位和18位身份证号码的正则表达式
        String regIdCard = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";
        Pattern p = Pattern.compile(regIdCard);
        return p.matcher(idCard).matches();
    }


    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhMMss");
        return sdf.format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     *格式化银行卡
     * @param cardNo
     * @return
     */
    public static String formatCard(String cardNo) {
        String card = "";
        card = cardNo.substring(0, 4) + " **** **** ";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }
    /**
     *银行卡后四位
     * @param cardNo
     * @return
     */
    public static String formatCardEndFour(String cardNo) {
        String card = "";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    public static  String fomatMoney(float money){
        DecimalFormat myformat = new DecimalFormat();
        if(money == 0){
            return "0.00";
        }else {
            myformat.applyPattern("##,###.00");
            return myformat.format(money);
        }
    }

    /**
     * 根据指定的图像路径和大小来获取缩略图
     */
    public static Bitmap getImageToBitmap(String imagePath, int width, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false; // 设为 false
        // 计算缩放比
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / width;
        int beHeight = h / height;
        int be = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        return bitmap;
    }

    //取到小数点后两位的数据
    //方案一:
    //get_double = (double)(Math.round(result_value*100)/100.0)

    //方案二:
   // DecimalFormat df = new DecimalFormat("#.##");
    //get_double = Double.ParseDouble(df.format(result_value));

    //方案三:
    //get_double = Double.ParseDouble(String.format("%.2f",result_value));

    //方案四:
   // BigDecimal bd = new BigDecimalresult_value();
    //BigDecimal  bd2 = bd.setScale(2,BigDecimal  .ROUND_HALF_UP);
    //get_double = Double.ParseDouble(bd2.ToString());

    public static double getDouble(double result){
        return  Double.parseDouble(String.format("%.2f",result));
    }







}

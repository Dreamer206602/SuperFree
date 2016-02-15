package com.zchx.lb.superfree.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * TheBeautiful
 * Created by meluo
 * on 2015/11/11.
 */
public class TimeUtils {
    //获取今天的日期 在直播里面要用
    public  static  String getNowDate(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd");
        return simpleDateFormat.format(new Date());
    }
    //获取昨天的时间
    public static String getTomorrowDate(){
        Calendar cal =Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return  new SimpleDateFormat( "MM/dd ").format(cal.getTime());
    }

    //将播放时长格式 化
    public static String formatDuration(int duration){
        String ret=null;
        duration=duration/1000;
        int s=duration%60;
        int d=duration/60;
        int m=d%60;
         d=d/60;
        int h=m%60;
        return h+":"+m+":"+s;
    }

    //将播放时长格式 化
    public static String formatDuration2(int duration){


        String ret=null;
        int s=duration%60;
        int d=duration/60;
        return String.format("%02d",d)+":"+String.format("%02d",s);

    }




}

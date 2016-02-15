package com.zchx.lb.superfree.utils;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by wswenyue on 2015/11/2.
 */
public class HttpRequest {
    public static String post(String url, StringMap stringMap, String cookie) {
        StringBuilder ret = new StringBuilder();
        try {
            HttpURLConnection conn = HttpTools.getConn(url, HttpTools.METHOD_POST);
            if (cookie != null) {
                conn.setRequestProperty("Cookie", cookie);
            }

            conn.connect();
            OutputStream outputStream = conn.getOutputStream();
            if (outputStream != null && stringMap != null) {
                String data = stringMap.formString();
                outputStream.write(data.getBytes());
                outputStream.close();
            }
            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                ret.append("Header:\n\n");
                //get all headers
                Map<String, List<String>> map = conn.getHeaderFields();
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    ret.append("Key : " + entry.getKey() +
                            " ,Value : " + entry.getValue()).append("\n");
                }
                ret.append("\n\n").append("Body:\n\n");
                ret.append(HttpTools.inputStreamToStr(inputStream));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.toString();
    }

    public static String post(String url, StringMap stringMap) {
        return post(url, stringMap, null);
    }

    public static String get(String url) {
        StringBuilder ret = new StringBuilder();
        try {
            HttpURLConnection conn = HttpTools.getConn(url, HttpTools.METHOD_POST);

            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                ret.append("Header:\n\n");
                //get all headers
                Map<String, List<String>> map = conn.getHeaderFields();
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    ret.append("Key : " + entry.getKey() +
                            " ,Value : " + entry.getValue()).append("\n");
                }
                ret.append("\n\n").append("Body:\n\n");
                ret.append(HttpTools.inputStreamToStr(inputStream));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.toString();
    }

}

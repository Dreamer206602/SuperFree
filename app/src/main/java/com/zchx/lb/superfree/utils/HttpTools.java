package com.zchx.lb.superfree.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Created by wswenyue on 2015/9/5.
 */
public class HttpTools {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static int connectTimeout = 50000;

    /**
     * 获取连接的URL
     *
     * @param netPath 请求的URL（字符串）
     * @return 返回URL
     * @throws MalformedURLException
     */
    public static URL getUrl(String netPath) throws MalformedURLException {
        return new URL(netPath);
    }

    /**
     * 获取请求资源的大小
     *
     * @param netPath 请求的URL（字符串）
     * @return 返回请求资源的大小
     * @throws IOException
     */
    public static Integer getFileSize(String netPath) throws IOException {
        return getConn(netPath, METHOD_GET).getContentLength();
    }

    /**
     * 获取HttpURLConnection连接对象
     *
     * @param url    URL地址
     * @param method 请求方式
     * @return HttpURLConnection对象
     * @throws IOException
     */
    public static HttpURLConnection getConn(String url, String method) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) getUrl(url).openConnection();
        conn.setRequestMethod(method);
        conn.setConnectTimeout(connectTimeout);
        setParameters(conn);
        if (method.equals(METHOD_POST)) {
            conn.setDoOutput(true);
        }
        conn.setDoInput(true);
        return conn;
    }

    /**
     * 获取HttpURLConnection中的读取流，
     * 如果服务器端传输的数据是gzip压缩的自动解压缩
     *
     * @param conn HttpURLConnection
     * @return 返回InputStream读取流
     * @throws IOException
     */
    public static InputStream getInputStream(HttpURLConnection conn) throws IOException {
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("网络连接错误！！错误码:" + responseCode);
        }
        InputStream in = null;
        String encoding = conn.getContentEncoding();
        if ("gzip".equals(encoding)) {
            in = new GZIPInputStream(conn.getInputStream());
        } else {
            in = conn.getInputStream();
        }
        return in;
    }


    /**
     * 设置连接参数
     *
     * @param conn HttpURLConnection连接对象
     */
    private static void setParameters(HttpURLConnection conn) {
        conn.setRequestProperty("User-Agent",
                " Mozilla/5.0 (Linux; Android 4.4.4; Nexus 5 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.114 Mobile Safari/537.36");

    }

    /**
     * 设置分段请求
     *
     * @param conn  HttpURLConnection连接对象
     * @param start 起点
     * @param end   终点
     */
    public static void setRange(HttpURLConnection conn, String start, String end) {
        conn.setRequestProperty("range", "bytes=" + start + "-" + end);
    }

    /**
     * 获取json字符串
     *
     * @param urlPath api请求的URL
     * @return json字符串
     * @throws IOException
     */
    public static String getJsonStr(String urlPath) throws IOException {
        HttpURLConnection conn = getConn(urlPath, METHOD_GET);
        if (conn.getResponseCode() == 200) {
            InputStream in = conn.getInputStream();
            return inputStreamToStr(in);
        }
        return null;

    }

    /**
     * 获取输入流中的数据并转换成字符串
     *
     * @param in 输入流
     * @return 字符串
     * @throws IOException
     */
    public static String inputStreamToStr(InputStream in) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] arr = new byte[1024];
        int len = 0;
        while ((len = in.read(arr)) != -1) {
            bos.write(arr, 0, len);
        }
        return new String(bos.toByteArray(), "utf-8");
    }

}

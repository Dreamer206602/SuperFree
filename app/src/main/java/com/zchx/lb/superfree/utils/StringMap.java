package com.zchx.lb.superfree.utils;




import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by
 * Author: wswenyue
 * Email: wswenyue@163.com
 * GitHub: https://github.com/wswenyue
 * Date: 2015/11/3
 */

/**
 * 用于提交post或get参数的一个类
 * put(String key, Object value)，用于添加参数</p>
 * String formString() 用来获取经过urlEncoding编码过的字符串</p>
 */
public final class StringMap {
    private Map<String, Object> map;

    public StringMap() {
        this(new HashMap<String, Object>());
    }

    public StringMap(Map<String, Object> map) {
        this.map = map;
    }

    public StringMap put(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public StringMap putNotEmpty(String key, String value) {
        if (!StringUtils.isNullOrEmpty(value)) {
            map.put(key, value);
        }
        return this;
    }

    public StringMap putNotNull(String key, Object value) {
        if (value != null) {
            map.put(key, value);
        }
        return this;
    }


    public StringMap putWhen(String key, Object val, boolean when) {
        if (when) {
            map.put(key, val);
        }
        return this;
    }

    public StringMap putAll(Map<String, Object> map) {
        this.map.putAll(map);
        return this;
    }

    public StringMap putAll(StringMap map) {
        this.map.putAll(map.map);
        return this;
    }

    private void forEach(Consumer imp) {
        for (Map.Entry<String, Object> i : map.entrySet()) {
            imp.accept(i.getKey(), i.getValue());
        }
    }

    public int size() {
        return map.size();
    }

    public Map<String, Object> map() {
        return this.map;
    }

    public Object get(String key) {
        return map.get(key);
    }

    public String formString() {
        final StringBuilder b = new StringBuilder();
        forEach(new Consumer() {
            private boolean notStart = false;

            @Override
            public void accept(String key, Object value) {
                if (notStart) {
                    b.append("&");
                }
                try {
                    b.append(URLEncoder.encode(key, Cfig.NET_CONFIG_CHARSET)).append('=')
                            .append(URLEncoder.encode(value.toString(), Cfig.NET_CONFIG_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    throw new AssertionError(e);
                }
                notStart = true;
            }
        });
        return b.toString();
    }

    private interface Consumer {
        void accept(String key, Object value);
    }
}

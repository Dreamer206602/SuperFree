package com.zchx.lb.superfree.utils.storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by wswenyue on 2015/10/6.
 */
public class PreferencesStore {
    private static String SHARE_CONFIG = "shareConfig";

    private static PreferencesStore store;
    private static SharedPreferences myPreferences;

    private PreferencesStore() {
    }

    public static PreferencesStore getInstance(Context context) {
        if (store == null) {
            store = new PreferencesStore();
        }
        myPreferences = context.getSharedPreferences(SHARE_CONFIG, Context.MODE_PRIVATE);
        return store;
    }

    public void save(String key, String value) {
        myPreferences.edit().putString(key, value).commit();
    }

    public void save(String key, int value) {
        myPreferences.edit().putInt(key, value).commit();
    }

    public void save(String key, float value) {
        myPreferences.edit().putFloat(key, value).commit();
    }

    public void save(String key, boolean value) {
        myPreferences.edit().putBoolean(key, value).commit();
    }

    public void save(String key, long value) {
        myPreferences.edit().putLong(key, value).commit();
    }

    public void save(String key, Set<String> values) {
        myPreferences.edit().putStringSet(key, values).commit();
    }


    public String readString(String key, String defValue) {
        String value = defValue != null ? defValue : "";
        return myPreferences.getString(key, value);
    }

    public boolean readBoolean(String key, Boolean defValue) {
        boolean value = defValue != null ? defValue : false;
        return myPreferences.getBoolean(key, value);
    }

    /**
     * @param key
     * @param defValue 如果为null 则默认0
     * @return
     */
    public int readInt(String key, Integer defValue) {
        int value = defValue != null ? defValue : 0;
        return myPreferences.getInt(key, value);
    }

    /**
     * @param key
     * @param defValue 如果为null则默认0f
     * @return
     */
    public float readFloat(String key, Float defValue) {
        float value = defValue != null ? defValue : 0f;
        return myPreferences.getFloat(key, value);
    }

    public Set<String> readStringSet(String key, Set<String> defValue) {
        return myPreferences.getStringSet(key, defValue);
    }

    public Map<String, ?> readAll() {
        return myPreferences.getAll();
    }

}

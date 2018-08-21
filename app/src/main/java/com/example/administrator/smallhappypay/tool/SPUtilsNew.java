package com.example.administrator.smallhappypay.tool;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import com.example.administrator.smallhappypay.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 存储工具类
 */
public class SPUtilsNew {
    public static final String SP_NAME = "config";


    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreference(Context context, String key) {
        return context.getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
    }

    public static int getInt(Context ctx, String key) {
        return getSharedPreference(ctx).getInt(key, 0);
    }

    public static String getString(Context ctx, String key) {
        return getSharedPreference(ctx).getString(key, null);
    }


    public static boolean getBoolean(Context ctx, String key) {
        return getSharedPreference(ctx).getBoolean(key, true);
    }


    public static void put(Context ctx, String key, Object value) {
        SharedPreferences sp = getSharedPreference(ctx);
        Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        }
        edit.commit();
    }

    /**
     * 存储Map对象
     *
     * @param context
     * @param key
     * @param datas
     */
    public static void saveInfo(Context context, String key, List<Map<String, String>> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            Map<String, String> itemMap = datas.get(i);
            Iterator<Map.Entry<String, String>> iterator = itemMap.entrySet().iterator();

            JSONObject object = new JSONObject();

            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mJsonArray.put(object);
        }
        SharedPreferences sp = context.getSharedPreferences("finals", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }

    /**
     * 取Map值
     *
     * @param context
     * @param key
     * @return
     */
    public static List<Map<String, String>> getInfo(Context context, String key) {
        List<Map<String, String>> datas = new ArrayList<>();
        SharedPreferences sp = context.getSharedPreferences("finals", Context.MODE_PRIVATE);
        String result = sp.getString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                Map<String, String> itemMap = new HashMap<>();
                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
                datas.add(itemMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return datas;
    }

    /**
     * 存储Object对象
     *
     * @param context
     * @param key
     * @param datas
     */
    public static void saveOtherInfo(Context context, String key, List<Map<String, Object>> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            Map<String, Object> itemMap = datas.get(i);
            Iterator<Map.Entry<String, Object>> iterator = itemMap.entrySet().iterator();
            JSONObject object = new JSONObject();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mJsonArray.put(object);
        }
        SharedPreferences sp = context.getSharedPreferences("finals", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }


    /**
     * 缓存对象
     *
     * @param key 缓存键
     * @param t   缓存的数值
     * @param <T> 数据类型
     * @return boolean 缓存状态，默认一般都是会成功的
     */
    public static <T> boolean putObject(String key, T t) {
        return saveObject(App.getInstance(), SP_NAME, key, t);
    }


    /**
     * 从缓存中获取存储的对象
     *
     * @param context 内容实体
     * @param key     键
     * @param <T>     数据类型
     * @return T 返回数据
     */
    public static <T> T getObject(Context context, String key) {
        return getObject(context, SP_NAME, key);
    }

    /**
     * 缓存对象
     *
     * @param context 内容实体
     * @param name    缓存器名称
     * @param key     键
     * @param t       数据
     * @param <T>     数据类型
     * @return boolean 缓存状态，默认一般都是成功的
     */
    public static <T> boolean saveObject(Context context, String name, String key, T t) {
        try {
            SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            if (t == null) {
                editor.putString(key, "");
                editor.commit();
                return true;
            }
            ByteArrayOutputStream toByte = new ByteArrayOutputStream();
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(toByte);
            oos.writeObject(t);
            String payCityMapBase64 = new String(Base64.encode(toByte.toByteArray(), Base64.DEFAULT));

            editor.putString(key, payCityMapBase64);
            editor.commit();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 从缓存中获取存储的对象
     *
     * @param context 内容实体
     * @param name    数据缓存器名称
     * @param key     键
     * @param <T>     数据类型
     * @return T 返回数据
     */
    public static <T> T getObject(Context context, String name, String key) {
        try {
            SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            String payCityMapBase64 = sp.getString(key, "");
            if (payCityMapBase64.length() == 0) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(payCityMapBase64, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void clear(Context ctx) {
        SharedPreferences sp = getSharedPreference(ctx);
        Editor edit = sp.edit();
        edit.clear();
        edit.commit();
    }

    public static void clear(Context ctx, String key) {
        SharedPreferences sp = getSharedPreference(ctx, key);
        Editor edit = sp.edit();
        edit.remove(key);
        edit.commit();
    }
}

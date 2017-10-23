package jing.honngshi.com.videodatapracticefromcibn.utils.otherutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by JIngYuchun on 2017/10/19.
 */

public class PreferenceUtils {
    public static String getPrefString(Context context, String key,
                                       final String defaultValue) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        return settings.getString(key, defaultValue);
    }

    public static void setPrefString(Context context, final String key,
                                     final String value) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        settings.edit().putString(key, value).apply();
    }

    public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context context, final String key) {
        return getDefaultSharedPreferences(context).contains(
                key);
    }

    public static void setPrefBoolean(Context context, final String key,
                                      final boolean value) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        settings.edit().putBoolean(key, value).apply();
    }

    public static void setPrefInt(Context context, final String key,
                                  final int value) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        settings.edit().putInt(key, value).apply();
    }

    public static int getPrefInt(Context context, final String key,
                                 final int defaultValue) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        return settings.getInt(key, defaultValue);
    }

    public static void setPrefFloat(Context context, final String key,
                                    final float value) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        settings.edit().putFloat(key, value).apply();
    }

    public static float getPrefFloat(Context context, final String key,
                                     final float defaultValue) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        return settings.getFloat(key, defaultValue);
    }

    public static void setSettingLong(Context context, final String key,
                                      final long value) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        settings.edit().putLong(key, value).apply();
    }

    public static long getPrefLong(Context context, final String key,
                                   final long defaultValue) {
        final SharedPreferences settings =
                getDefaultSharedPreferences(context);
        return settings.getLong(key, defaultValue);
    }

    public static void clearPreference(Context context,
                                       final SharedPreferences p) {
        final SharedPreferences.Editor editor = p.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private static String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param context    上下文
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public static void save(Context context, String key, Object saveObject) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String string = Object2String(saveObject);
        editor.putString(key, string);
        editor.commit();
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param context 上下文
     * @param key     储存对象的key
     * @return object 返回根据key得到的对象
     */
    public static Object get(Context context, String key) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        String string = sharedPreferences.getString(key, null);
        if (string != null) {
            Object object = String2Object(string);
            return object;
        } else {
            return null;
        }
    }
}

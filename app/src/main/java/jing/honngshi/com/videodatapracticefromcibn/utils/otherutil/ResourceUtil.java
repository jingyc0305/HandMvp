package jing.honngshi.com.videodatapracticefromcibn.utils.otherutil;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by JIngYuchun on 2017/10/30.
 */

public class ResourceUtil {

    /**
     * dip转pix
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = getResources(context).getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 获得资源
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }
    /**
     * 得到字符数组
     */
    public static String[] getStringArray(Context context, int id) {
        return getResources(context).getStringArray(id);
    }
}

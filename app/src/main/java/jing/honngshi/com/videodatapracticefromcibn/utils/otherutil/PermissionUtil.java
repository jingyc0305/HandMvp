package jing.honngshi.com.videodatapracticefromcibn.utils.otherutil;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by JIngYuchun on 2017/10/20.
 */

public class PermissionUtil {
    private static StringBuffer logStringBuffer = new StringBuffer();
    // 查看权限是否已申请
    public static String checkPermissions(Context context, String... permissions) {
        logStringBuffer.delete(0,logStringBuffer.length());
        for (String permission : permissions) {
            logStringBuffer.append(permission);
            logStringBuffer.append(" is applied? \n     ");
            logStringBuffer.append(isAppliedPermission(context,permission));
            logStringBuffer.append("\n\n");
        }
        return logStringBuffer.toString();
    }
    // 查看权限是否已申请
    public static boolean isAppliedPermission(Context context,String permission) {
        return ContextCompat.checkSelfPermission(context,permission) ==
                PackageManager.PERMISSION_GRANTED;
    }
}

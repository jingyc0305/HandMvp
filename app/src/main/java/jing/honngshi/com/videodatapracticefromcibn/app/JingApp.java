package jing.honngshi.com.videodatapracticefromcibn.app;

import android.app.Activity;
import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.HashSet;
import java.util.Set;

import jing.honngshi.com.videodatapracticefromcibn.BuildConfig;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.LogUtil;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public class JingApp extends Application {

    private Set<Activity> allActivities;
    private static JingApp instance;
    public static synchronized JingApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化logger
        LogUtil.init(BuildConfig.APP_DEBUG);
        //初始化QQ平台
        PlatformConfig.setQQZone("1106406334", "snGN26dsQXCT7IyE");
        //初始化微博平台
        PlatformConfig.setSinaWeibo("85580524", "3ea78b582a57e200592415f61f073cc3","http://open.weibo.com/apps/85580524/privilege/oauth");
        //初始化友盟
        UMShareAPI.get(this);
        //初始化fragmention
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出  默认NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                // 开发环境：true时，遇到异常："Can not perform this action after onSaveInstanceState!"时，抛出，并Crash;
                // 生产环境：false时，不抛出，不会Crash，会捕获，可以在handleException()里监听到
                .debug(BuildConfig.DEBUG) // 实际场景建议.debug(BuildConfig.DEBUG)
                // 生产环境时，捕获上述异常（避免crash），会捕获
                // 建议在回调处上传下面异常到崩溃监控服务器
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}

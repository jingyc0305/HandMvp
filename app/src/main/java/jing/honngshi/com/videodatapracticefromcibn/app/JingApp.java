package jing.honngshi.com.videodatapracticefromcibn.app;

import android.app.Activity;
import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.HashSet;
import java.util.Set;

import jing.honngshi.com.videodatapracticefromcibn.BuildConfig;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.LogUtil;

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

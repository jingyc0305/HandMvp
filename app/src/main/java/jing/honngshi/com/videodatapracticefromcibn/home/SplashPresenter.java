package jing.honngshi.com.videodatapracticefromcibn.home;

import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.NetUtil;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.OkHttpImageDownloader;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.TimeUtil;

/**
 * Created by JIngYuchun on 2017/10/19.
 */

public class SplashPresenter extends AbsBasePresenter<SplashContract.ISplashView> implements SplashContract.ISplashPresenter{

    SplashContract.ISplashView mISplashView;
    public SplashPresenter(SplashContract.ISplashView mISplashView){
        this.mISplashView = mISplashView;
    }

    @Override
    public void getSplash(String deviceId) {
        String client = "android";
        String version = "1.3.0";
        Long time = TimeUtil.getCurrentSeconds();
        RetrofitFactory.getSplashService().getSplash(client,version,time,deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<SplashEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SplashEntity splashEntity) {
                        if (NetUtil.isWifi(JingApp.getInstance().getApplicationContext())){
                            if (splashEntity != null){
                                List<String> imgs = splashEntity.getImages();
                                for (String url:imgs) {
                                    OkHttpImageDownloader.download(url);
                                }
                            }
                        }else{
                            Logger.i("不是WIFI环境,就不去下载图片了");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.i("获取splash出错");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

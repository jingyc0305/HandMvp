package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.presenter;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;
import jing.honngshi.com.videodatapracticefromcibn.cache.Repository;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryTagBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.VodByTagMTBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract.VarietyContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PreferenceUtils;

import static jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory
        .getVodService;


/**
 * Created by JIngYuchun on 2017/10/17.
 */

public class VarietyPresenter extends VarietyContract.IVarietyVodPresenter {
    VarietyContract.IVarietyVodView mIVarietyVodView;
    private final String VARIETY_TYPE_LOVE = "love";
    private final String VARIETY_TYPE_PERSON = "person";
    public VarietyPresenter (VarietyContract.IVarietyVodView mIVarietyVodView){
        this.mIVarietyVodView = mIVarietyVodView;
    }

    private int categoryVarietyId = 0;
    private int categotyVarietyFirstId = 0;
    ArrayList<CategoryBean> categoryBeanList;
    ArrayList<CategoryTagBean> categoryByTagBeanList;
    @Override
    public void getVarietyTypeData() {
        //获取首页分类缓存数据
        categoryBeanList = (ArrayList<CategoryBean>) PreferenceUtils.get(JingApp.getInstance(), AppCommon.CATEGORY);
        categoryByTagBeanList =  (ArrayList<CategoryTagBean>) PreferenceUtils.get(JingApp.getInstance(), AppCommon.CATEGORY_TAG_Variety);
        if(null != categoryBeanList && categoryBeanList.size() > 0){
            categoryVarietyId = categoryBeanList.get(1).getCategoryId();
            if(null != categoryByTagBeanList && categoryByTagBeanList.size() > 0){
                categotyVarietyFirstId = categoryByTagBeanList.get(0).getTagId();
            }else{
                // TODO: 2017/10/23 这里默认写死 后面改成网络获取的值 通过fragment 进行参数传递
                categotyVarietyFirstId = 19;
            }
        }else{
            // TODO: 2017/10/23 这里默认写死 后面改成网络获取的值 通过fragment 进行参数传递
            categoryVarietyId = 10;
            categotyVarietyFirstId = 19;
        }
        //获取综艺下的栏目菜单
        Observable<List<CategoryTagBean>> mCategoryByTagBean = getVodService().getCategoryTag(categoryVarietyId, "Android", "2.0","MzljMjU0N2UwYjk3",
                "6.0.1", "com.sumavision.sanping.gudou");
        //加入缓存
        Repository.getICache().getCategoryTag(mCategoryByTagBean,new DynamicKey("getCategoryTag_Variety"),new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<List<CategoryTagBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<CategoryTagBean> categoryTagBeen) {
                        //这里后面缓存到Perfenerce中
                        categotyVarietyFirstId = categoryTagBeen.get(0).getTagId();
                        //将栏目分类下的子栏目分类数据保存下来 以便无网络时候可以取到缓存
                        PreferenceUtils.save(JingApp.getInstance(),AppCommon.CATEGORY_TAG_Variety,categoryTagBeen);
                        mIVarietyVodView.onShowCategoryName(categoryTagBeen);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mIVarietyVodView.showNetError();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getVarietyDetailData() {
        mIVarietyVodView.showLoading();
        //获取分类下的数据 综艺-真人秀
        Logger.d("当前栏目ID: "+ categoryVarietyId);
        Logger.d("综艺下的栏目ID: "+ categotyVarietyFirstId);
        Observable<VodByTagMTBean> mVodByTagMtBean = RetrofitFactory.getVodService().getVodByTagMTDatas("Android", "2.0","MzljMjU0N2UwYjk3",
                "6.0.1", "com.sumavision.sanping.gudou","1","10",categoryVarietyId,categotyVarietyFirstId);
        //加入缓存
        Repository.getICache().getVodByTagMTDatas(mVodByTagMtBean,new DynamicKey("getVodByTag_Variety"),new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VodByTagMTBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull VodByTagMTBean vodByTagMTBean) {
                        ArrayList<VodByTagMTBean.RowsBeanX> mTvGuDataList = new ArrayList<>();
                        for (int i = 0; i < vodByTagMTBean.getRows().size(); i++) {
                            VodByTagMTBean item = new VodByTagMTBean();
                            item.setRows(vodByTagMTBean.getRows());
                            item.getRows().get(i).setVideoName(vodByTagMTBean.getRows().get(i).getVideoName());
                            item.getRows().get(i).setVideoImage(vodByTagMTBean.getRows().get(i).getVideoImageOttY());
                            //菜单1-爱情
                            mTvGuDataList.add(new VodByTagMTBean.RowsBeanX(true,false,VARIETY_TYPE_LOVE,VodByTagMTBean
                                    .RowsBeanX.IMG_TEXT_UP_DOWN, 2));
                            //菜单2-真人秀
                            mTvGuDataList.add(new VodByTagMTBean.RowsBeanX(true,false,VARIETY_TYPE_PERSON,VodByTagMTBean
                                    .RowsBeanX.IMG_TEXT_UP_DOWN, 2));
                            //内容分组名称
                            mTvGuDataList.add(new VodByTagMTBean.RowsBeanX(false,true, VARIETY_TYPE_PERSON,VodByTagMTBean
                                    .RowsBeanX.HEADER_NAME_MORE, 1));
                            //具体内容
                            VodByTagMTBean.RowsBeanX mRowsBeanX = item.getRows().get(i);
                            mRowsBeanX.setMenuItem(false);
                            mRowsBeanX.setHeader(false);
                            mRowsBeanX.setItemType(VodByTagMTBean.RowsBeanX.VOD_IMG_TEXT_UP_DOWN);
                            mRowsBeanX.setSpanSize(3);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            //内容分组名称
                            mTvGuDataList.add(new VodByTagMTBean.RowsBeanX(false,true, VARIETY_TYPE_LOVE,VodByTagMTBean
                                    .RowsBeanX.HEADER_NAME_MORE, 1));
                            //具体内容
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                        }
                        mIVarietyVodView.onDataSucess();
                        mIVarietyVodView.showData(mTvGuDataList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mIVarietyVodView.showNetError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    @Override
    public void cacheVarietyVodData() {

    }
}

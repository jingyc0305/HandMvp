package jing.honngshi.com.videodatapracticefromcibn.category.vod.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.VodByTagMTBean;

/**
 * Created by JIngYuchun on 2017/10/17.
 */

public class VodByTagMTAdapter extends BaseMultiItemQuickAdapter<VodByTagMTBean.RowsBeanX,BaseViewHolder> {
    List<String> adUrlList ;
    List<String> adTitles ;
    public VodByTagMTAdapter(List<VodByTagMTBean.RowsBeanX> data, List<String> adUrlList,List<String> adTitles){
        super(data);
        this.adUrlList = adUrlList;
        this.adTitles = adTitles;
        addItemType(VodByTagMTBean.RowsBeanX.IMG_TEXT_UP_DOWN, R.layout.vod_tv_menu_item);
        addItemType(VodByTagMTBean.RowsBeanX.HEADER_NAME_MORE, R.layout.vod_tv_title_section_more);
        addItemType(VodByTagMTBean.RowsBeanX.VOD_IMG_TEXT_UP_DOWN, R.layout.vod_tv_item);
        //addItemType(VodByTagMTBean.RowsBeanX.IMG_BANNER, R.layout.ad_variety_banner_layout);
    }
    @Override
    protected void convert(BaseViewHolder helper, VodByTagMTBean.RowsBeanX item) {
        Logger.d("convert "+helper.getItemViewType());
        switch (helper.getItemViewType()){

//            case VodByTagMTBean.RowsBeanX.IMG_BANNER:
//                Logger.d("Banner");
//                //初始化banner 对象
//                Banner mBanner = (Banner)helper.getView(R.id.banner);
//                //mBanner = (Banner) mAdView.findViewById(R.id.banner);
//                mBanner.setImages(adUrlList);
//                mBanner.setBannerTitles(adTitles);
//                mBanner.setBannerAnimation(Transformer.RotateDown);
//                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//                mBanner.setDelayTime(3000);
//                mBanner.setIndicatorGravity(BannerConfig.RIGHT);
//                mBanner.setImageLoader(new GlideImageLoader());
//                mBanner.start();
//                break;
            case VodByTagMTBean.RowsBeanX.IMG_TEXT_UP_DOWN:
                helper.setText(R.id.tv_series_text,item.getMenuName());
                helper.setImageResource(R.id.tv_series_imageView,item.getMenuIcon());
                break;
            case VodByTagMTBean.RowsBeanX.HEADER_NAME_MORE:
                helper.setText(R.id.section_title_content_tv,item.getHeaderName());
                //helper.setImageResource(R.id.section_title_more_tv,item.getMenuIcon());
                break;
            case VodByTagMTBean.RowsBeanX.VOD_IMG_TEXT_UP_DOWN:
                helper.setText(R.id.video_name,item.getVideoName());
                helper.setText(R.id.vod_text,String.valueOf(item.getVideoInfo().get(0).getValue()));
                ImageView iv = helper.getView(R.id.vod_imageview);
                Glide.with(mContext).load(item.getVideoImageY()).centerCrop().dontAnimate().placeholder(R.mipmap.ic_not_network).into(iv);
                break;
        }
    }
}

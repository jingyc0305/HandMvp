package jing.honngshi.com.videodatapracticefromcibn.category.live.adapter;

import android.view.View;
import android.widget.ImageView;

import com.allen.library.SuperButton;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommendMultiItem;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.GlideImageLoader;

/**
 * Created by JIngYuchun on 2017/11/22.
 */

public class LiveRecommendAdapter extends BaseMultiItemQuickAdapter<LiveRecommendMultiItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public LiveRecommendAdapter(List<LiveRecommendMultiItem> data) {
        super(data);
        addItemType(LiveRecommendMultiItem.RECOMMEND_BANNER, R.layout.ad_live_recommend_banner_layout);
        addItemType(LiveRecommendMultiItem.RECOMMEND_AD, R.layout.ad_live_recommend_banner_layout);
        addItemType(LiveRecommendMultiItem.RECOMMEND_HEADER, R.layout.live_recommend_title);
        addItemType(LiveRecommendMultiItem.RECOMMEND_ITEM_HORIZONTAL, R.layout.live_remmend_item);
        addItemType(LiveRecommendMultiItem.RECOMMEND_ITEM_GRID, R.layout.live_remmend_item);
    }

//    public LiveRecommendAdapter(int layoutResId, int sectionHeadResId, List<LiveRecommend
//            .RoomBean.ListBean> data) {
//        super(layoutResId, sectionHeadResId, data);
//        this.data = data;
//
//    }

//    @Override
//    protected void convertHead(BaseViewHolder helper, LiveRecommend.RoomBean.ListBean item) {
//        SuperButton mbtn = helper.getView(R.id.live_refresh_btn);
//        if(!item.isShowRefreshBtn()){
//            mbtn.setVisibility(View.GONE);
//        }else{
//            mbtn.setVisibility(View.VISIBLE);
//        }
//        ImageView section_iv = helper.getView(R.id.section_title_tv);
//        helper.setText(R.id.section_title_content_tv, item.header);
//        Glide.with(mContext).load(item.getHeaderIcon()).centerCrop().dontAnimate().placeholder(R.mipmap
//                .default_recommend_icon).into(section_iv);
//        helper.addOnClickListener(R.id.section_title_more_tv).addOnClickListener(R.id.live_refresh_btn);
//    }

    @Override
    protected void convert(BaseViewHolder helper, LiveRecommendMultiItem item) {

        switch (helper.getItemViewType()){
            case LiveRecommendMultiItem.RECOMMEND_BANNER:
                //显示banner
                Banner mBanner = helper.getView(R.id.live_recommend_banner);
                mBanner.setImages(item.getBanner_img_url());
                mBanner.setBannerTitles(item.getBanner_title());
                mBanner.setBannerAnimation(Transformer.DepthPage);
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                mBanner.setDelayTime(4000);
                mBanner.setIndicatorGravity(BannerConfig.RIGHT);
                mBanner.setImageLoader(new GlideImageLoader());
                mBanner.start();
                break;
            case LiveRecommendMultiItem.RECOMMEND_AD:
                //显示广告图
//                ImageView section_iv = helper.getView(R.id.section_title_tv);
//                helper.setText(R.id.section_title_content_tv,item.getName());
//                Glide.with(mContext).load(item.get()).centerCrop().dontAnimate().placeholder(R.mipmap.default_recommend_icon).into(section_iv);
                break;
            case LiveRecommendMultiItem.RECOMMEND_HEADER:
                //显示分组头部
                SuperButton mbtn = helper.getView(R.id.live_refresh_btn);
                helper.addOnClickListener(R.id.live_refresh_btn);
                if (!item.isShowRefreshBtn()) {
                    mbtn.setVisibility(View.GONE);
                } else {
                    mbtn.setVisibility(View.VISIBLE);
                }
                ImageView section_iv = helper.getView(R.id.section_title_tv);
                helper.setText(R.id.section_title_content_tv, item.getHeader_name());
                Glide.with(mContext).load(item.getHeader_img_url()).centerCrop().dontAnimate().placeholder(R
                        .mipmap.default_recommend_icon).into(section_iv);
                break;
            case LiveRecommendMultiItem.RECOMMEND_ITEM_HORIZONTAL:
                //显示横向列表
                break;
            case LiveRecommendMultiItem.RECOMMEND_ITEM_GRID:
                //显示网格数据
                Glide.with(mContext)
                        .load(item.getItem_img_url())
                        .centerCrop()
                        .dontAnimate()
                        .placeholder(R.mipmap.live_default)
                        .into((ImageView) helper.getView(R.id.iv));
                helper.setText(R.id.tvTitle, item.getItem_sub_title());
                helper.setText(R.id.tvName, item.getItem_title());
                helper.setText(R.id.tvViewer, item.getItem_people_count());
                helper.addOnClickListener(R.id.iv);
                break;
        }
    }

}

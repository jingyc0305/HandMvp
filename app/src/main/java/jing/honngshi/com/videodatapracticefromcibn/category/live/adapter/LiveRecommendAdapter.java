package jing.honngshi.com.videodatapracticefromcibn.category.live.adapter;

import android.view.View;
import android.widget.ImageView;

import com.allen.library.SuperButton;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommend;

/**
 * Created by JIngYuchun on 2017/11/22.
 */

public class LiveRecommendAdapter extends BaseSectionQuickAdapter<LiveRecommend.RoomBean.ListBean,BaseViewHolder> {
    List<LiveRecommend.RoomBean.ListBean> data;
    public static final int RECOMMEND_BANNER = 1;
    public static final int RECOMMEND_TITLE = 2;
    public static final int ROOM_ITEM = 3;
    public static final int ROOM_ITEM_FOOTER = 4;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public LiveRecommendAdapter(int layoutResId, int sectionHeadResId, List<LiveRecommend.RoomBean.ListBean> data) {
        super(layoutResId, sectionHeadResId, data);
        this.data = data;
//        addItemType(LiveRecommend.RoomBean.RECOMMEND_BANNER, R.layout.live_recommend_banner);
//        addItemType(LiveRecommend.RoomBean.RECOMMEND_TITLE, R.layout.live_recommend_title);
//        addItemType(LiveRecommend.RoomBean.ROOM_ITEM, R.layout.live_remmend_item);
//        addItemType(LiveRecommend.RoomBean.ROOM_ITEM_FOOTER, R.layout.live_recommend_footer);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, LiveRecommend.RoomBean.ListBean item) {
        SuperButton mbtn = helper.getView(R.id.live_refresh_btn);
        if(!item.isShowRefreshBtn()){
            mbtn.setVisibility(View.GONE);
        }else{
            mbtn.setVisibility(View.VISIBLE);
        }
        ImageView section_iv = helper.getView(R.id.section_title_tv);
        helper.setText(R.id.section_title_content_tv, item.header);
        Glide.with(mContext).load(item.getHeaderIcon()).centerCrop().dontAnimate().placeholder(R.mipmap
                .default_recommend_icon).into(section_iv);
        helper.addOnClickListener(R.id.section_title_more_tv).addOnClickListener(R.id.live_refresh_btn);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveRecommend.RoomBean.ListBean item) {
        Glide.with(mContext).load(item.getLive_thumb()).centerCrop().dontAnimate
                ().placeholder(R.mipmap.live_default).into((ImageView) helper.getView(R.id.iv));
        helper.setText(R.id.tvTitle, item.getTitle());
        helper.setText(R.id.tvName, item.getNick());
        helper.setText(R.id.tvViewer, item.getView());
        helper.addOnClickListener(R.id.iv);
//        switch (helper.getItemViewType()){
//            case LiveRecommend.RoomBean.RECOMMEND_BANNER:
//                break;
//            case LiveRecommend.RoomBean.RECOMMEND_TITLE:
//                ImageView section_iv = helper.getView(R.id.section_title_tv);
//                helper.setText(R.id.section_title_content_tv,item.getName());
//                Glide.with(mContext).load(item.getIcon()).centerCrop().dontAnimate().placeholder(R.mipmap.default_recommend_icon).into(section_iv);
//                break;
//            case LiveRecommend.RoomBean.ROOM_ITEM:
//                helper.setText(R.id.tvTitle,item.getList().get(0).getTitle());
//                Glide.with(mContext).load(item.getList().get(0).getLive_thumb()).centerCrop().dontAnimate().placeholder(R.mipmap.live_default).into((ImageView) helper.getView(R.id.iv));
//                helper.setText(R.id.tvName,item.getList().get(0).getNick());
//                helper.setText(R.id.tvViewer,item.getList().get(0).getView());
//                break;
//            case LiveRecommend.RoomBean.ROOM_ITEM_FOOTER:
//
//                break;
//        }
    }
}

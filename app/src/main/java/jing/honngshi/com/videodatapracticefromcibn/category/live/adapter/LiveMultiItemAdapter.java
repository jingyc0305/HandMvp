package jing.honngshi.com.videodatapracticefromcibn.category.live.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveMultiItem;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.ResourceUtil;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.TimeUtil;

public class LiveMultiItemAdapter extends BaseMultiItemQuickAdapter<LiveMultiItem, BaseViewHolder> {

    private Context mContext;
    public LiveMultiItemAdapter(List<LiveMultiItem> data, Context context) {
        super(data);
        this.mContext = context;
        addItemType(LiveMultiItem.TITLE, R.layout.item_title_live);
        addItemType(LiveMultiItem.ITEM, R.layout.item_item_live);
        addItemType(LiveMultiItem.BANNER, R.layout.item_banner_live);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveMultiItem item) {
        switch (item.getItemType()) {
            case LiveMultiItem.TITLE:
//                mAppComponent
//                        .imageLoader()
//                        .loadImage(mContext, ImageConfigImpl
//                                .builder()
//                                .imageView(helper.getView(R.id.iv_title_icon_src))
//                                .url(item.getTitleIconSrc())
//                                .build());
                Glide.with(mContext).load(item.getTitleIconSrc()).into((ImageView) helper.getView(R.id.iv_title_icon_src));
                helper.setText(R.id.tv_title_name, item.getTitleName())
                        .setText(R.id.tv_title_count, item.getTitleCount() + "");
                break;
            case LiveMultiItem.ITEM:
//                mAppComponent
//                        .imageLoader()
//                        .loadImage(mContext, ImageConfigImpl
//                                .builder()
//                                .imageView(helper.getView(R.id.iv_item_cover_src))
//                                .url(item.getItemCoverSrc())
//                                .build());
                Glide.with(mContext).load(item.getItemCoverSrc()).into((ImageView)helper.getView(R.id.iv_item_cover_src));
                int leftPadding = item.isOdd() ? ResourceUtil.dip2px(mContext, 8) : 0;
                int rightPadding = item.isOdd() ? 0 : ResourceUtil.dip2px(mContext, 8);
                helper.getView(R.id.fl_item).setPadding(leftPadding, 0, rightPadding, 0);
                helper.setText(R.id.tv_item_owner_name, item.getItemOwnerName())
                        .setText(R.id.tv_item_title, item.getItemTitle())
                        .setText(R.id.tv_item_sub_title, item.getItemSubTitle())
                        .setText(R.id.tv_item_online, TimeUtil.handleCount2TenThousand(item.getItemOnline()));
                break;
            case LiveMultiItem.BANNER:
//                mAppComponent
//                        .imageLoader()
//                        .loadImage(mContext, ImageConfigImpl
//                                .builder()
//                                .imageView(helper.getView(R.id.iv_banner_cover_src))
//                                .url(item.getBannerCoverSrc())
//                                .build());
                Glide.with(mContext).load(item.getBannerCoverSrc()).into((ImageView)helper.getView(R.id.iv_banner_cover_src));
                helper.setText(R.id.tv_banner_title, item.getBannerTitle());
                break;
//            case LiveMultiItem.BOTTOM:
//                break;
        }
    }
}

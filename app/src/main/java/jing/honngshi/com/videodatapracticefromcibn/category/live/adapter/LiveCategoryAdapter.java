package jing.honngshi.com.videodatapracticefromcibn.category.live.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveCategory;

/**
 * Created by JIngYuchun on 2017/11/26.
 */

public class LiveCategoryAdapter extends BaseQuickAdapter<LiveCategory,BaseViewHolder>{
    public LiveCategoryAdapter(@LayoutRes int layoutResId, @Nullable List<LiveCategory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveCategory item) {

        ImageView category_img = helper.getView(R.id.categoty_img);
        ImageView category_icon = helper.getView(R.id.categoty_icon);
        Glide.with(mContext).load(item.getIcon_image()).placeholder(R.mipmap.live_default).centerCrop().dontAnimate().into(category_img);
        Glide.with(mContext).load(item.getIcon_red()).placeholder(R.mipmap.default_recommend_icon).dontAnimate().into(category_icon);
        helper.setText(R.id.categoty_name,item.getName());
    }
}

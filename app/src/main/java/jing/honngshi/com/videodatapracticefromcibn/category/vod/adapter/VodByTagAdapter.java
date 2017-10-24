package jing.honngshi.com.videodatapracticefromcibn.category.vod.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.VodByTagBean;

/**
 * Created by JIngYuchun on 2017/10/13.
 */

public class VodByTagAdapter extends BaseSectionQuickAdapter<VodByTagBean.RowsBeanX,BaseViewHolder> implements BaseQuickAdapter.RequestLoadMoreListener{
    private Context mContext;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */

    public VodByTagAdapter(@LayoutRes int layoutResId, int sectionHeadResId, @Nullable List<VodByTagBean.RowsBeanX> data, Context context) {
        super(layoutResId, sectionHeadResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, VodByTagBean.RowsBeanX item) {
        helper.setText(R.id.video_name,item.getVideoName());
        helper.setText(R.id.vod_text,String.valueOf(item.getVideoInfo().get(0).getValue()));
        ImageView iv = helper.getView(R.id.vod_imageview);
        Glide.with(mContext).load(item.getVideoImageY()).centerCrop().dontAnimate().placeholder(R.mipmap.avatar).into(iv);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, VodByTagBean.RowsBeanX item) {
        helper.setText(R.id.section_title_content_tv,item.header);
        helper.setVisible(R.id.section_title_more_tv, item.isMore());
        helper.addOnClickListener(R.id.section_title_more_tv);
    }

    @Override
    public void onLoadMoreRequested() {

    }
}

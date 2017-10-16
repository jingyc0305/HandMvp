package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.TvCommodBean;

/**
 * Created by JIngYuchun on 2017/10/13.
 */

public class TvRecommdAdapter extends BaseQuickAdapter<TvCommodBean,BaseViewHolder>{
    public TvRecommdAdapter(@LayoutRes int layoutResId, @Nullable List<TvCommodBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TvCommodBean item) {
        helper.setText(R.id.vod_text,item.getTitle());
        helper.setImageResource(R.id.vod_imageview,item.getImageResourse());
    }
}

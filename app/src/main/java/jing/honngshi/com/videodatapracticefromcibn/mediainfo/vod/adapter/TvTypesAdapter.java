package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.TvTypesBean;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class TvTypesAdapter extends BaseQuickAdapter<TvTypesBean,BaseViewHolder> {

    public TvTypesAdapter(int layoutResId , List data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TvTypesBean item) {

        helper.setText(R.id.tv_series_text,item.getName());
        //helper.setImageResource(R.id.tv_series_imageView,R.mipmap.movie);
    }
}

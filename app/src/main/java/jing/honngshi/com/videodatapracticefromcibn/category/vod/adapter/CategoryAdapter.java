package jing.honngshi.com.videodatapracticefromcibn.category.vod.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryTagBean;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class CategoryAdapter extends BaseQuickAdapter<CategoryTagBean,BaseViewHolder> {

    public CategoryAdapter(int layoutResId , List data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryTagBean item) {

        helper.setText(R.id.tv_series_text,item.getName());
        //helper.setImageResource(R.id.tv_series_imageView,R.mipmap.movie);
    }
}

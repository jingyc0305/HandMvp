package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.VodByTagMTBean;

/**
 * Created by JIngYuchun on 2017/10/17.
 */

public class VodByTagMTAdapter extends BaseMultiItemQuickAdapter<VodByTagMTBean.RowsBeanX,BaseViewHolder> {

    public VodByTagMTAdapter(List<VodByTagMTBean.RowsBeanX> data){
        super(data);
        addItemType(VodByTagMTBean.RowsBeanX.IMG_TEXT_UP_DOWN, R.layout.vod_tv_menu_item);
        addItemType(VodByTagMTBean.RowsBeanX.HEADER_NAME_MORE, R.layout.vod_tv_title_section_more);
        addItemType(VodByTagMTBean.RowsBeanX.VOD_IMG_TEXT_UP_DOWN, R.layout.vod_tv_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, VodByTagMTBean.RowsBeanX item) {

        switch (helper.getItemViewType()){
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

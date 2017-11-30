package jing.honngshi.com.videodatapracticefromcibn.category.live.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by JIngYuchun on 2017/11/28.
 */

public class LiveRecommendMultiItem implements MultiItemEntity{

    public static final int RECOMMEND_BANNER = 1;
    public static final int RECOMMEND_AD = 2;
    public static final int RECOMMEND_HEADER = 3;
    public static final int RECOMMEND_ITEM_HORIZONTAL = 4;
    public static final int RECOMMEND_ITEM_GRID = 5;
    //public static final int RECOMMEND_FOOTER = 6;

    private int itemType;
    private int spanSize;
    //banner
    List<String> banner_img_url;
    List<String> banner_title;
    //ad
    String ad_img_url;
    String ad_title;
    //header
    String header_img_url;
    String header_name;
    boolean header_isMore;
    //是否显示换一换
    private boolean isShowRefreshBtn;
    //item
    String item_img_url;
    String item_title;
    String item_sub_title;
    String item_people_count;
    //bottom

    //----------

    private void setSpanSzieItemType(int itemType) {
        switch (itemType) {
            case RECOMMEND_BANNER:
                spanSize = 2;
                break;
            case RECOMMEND_AD:
                spanSize = 2;
                break;
            case RECOMMEND_HEADER:
                spanSize = 2;
                break;
            case RECOMMEND_ITEM_GRID:
                spanSize = 1;
                break;
            case RECOMMEND_ITEM_HORIZONTAL:
                spanSize = 2;
                break;
            //case RECOMMEND_FOOTER:
                //spanSize = 2;
               // break;
        }
    }
    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
        setSpanSzieItemType(itemType);
    }

    public boolean isShowRefreshBtn() {
        return isShowRefreshBtn;
    }

    public void setShowRefreshBtn(boolean showRefreshBtn) {
        isShowRefreshBtn = showRefreshBtn;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public void setBanner_img_url(List<String> banner_img_url) {
        this.banner_img_url = banner_img_url;
    }

    public void setBanner_title(List<String> banner_title) {
        this.banner_title = banner_title;
    }

    public void setAd_img_url(String ad_img_url) {
        this.ad_img_url = ad_img_url;
    }

    public void setAd_title(String ad_title) {
        this.ad_title = ad_title;
    }

    public void setHeader_img_url(String header_img_url) {
        this.header_img_url = header_img_url;
    }

    public void setHeader_name(String header_name) {
        this.header_name = header_name;
    }

    public void setHeader_isMore(boolean header_isMore) {
        this.header_isMore = header_isMore;
    }

    public void setItem_img_url(String item_img_url) {
        this.item_img_url = item_img_url;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public void setItem_sub_title(String item_sub_title) {
        this.item_sub_title = item_sub_title;
    }

    public void setItem_people_count(String item_people_count) {
        this.item_people_count = item_people_count;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public List<String> getBanner_img_url() {
        return banner_img_url;
    }

    public List<String> getBanner_title() {
        return banner_title;
    }

    public String getAd_img_url() {
        return ad_img_url;
    }

    public String getAd_title() {
        return ad_title;
    }

    public String getHeader_img_url() {
        return header_img_url;
    }

    public String getHeader_name() {
        return header_name;
    }

    public boolean getHeader_isMore() {
        return header_isMore;
    }

    public String getItem_img_url() {
        return item_img_url;
    }

    public String getItem_title() {
        return item_title;
    }

    public String getItem_sub_title() {
        return item_sub_title;
    }

    public String getItem_people_count() {
        return item_people_count;
    }
}

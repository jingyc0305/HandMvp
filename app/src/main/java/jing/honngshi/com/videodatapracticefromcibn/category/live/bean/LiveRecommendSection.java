package jing.honngshi.com.videodatapracticefromcibn.category.live.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by JIngYuchun on 2017/11/23.
 */

public class LiveRecommendSection extends SectionEntity<LiveRecommend.RoomBean> {
    //是否显示更多
    private boolean isShowMore;

    public LiveRecommendSection(boolean isHeader, String header, boolean isShowMore) {
        super(isHeader, header);
        this.isShowMore = isShowMore;
    }

    public LiveRecommendSection(LiveRecommend.RoomBean t){
        super(t);
    }

    public boolean isShowMore() {
        return isShowMore;
    }

    public void setShowMore(boolean showMore) {
        isShowMore = showMore;
    }
}

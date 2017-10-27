package jing.honngshi.com.videodatapracticefromcibn.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by JIngYuchun on 2017/10/13.
 */

public class MyGridLayoutManger extends GridLayoutManager {
    private boolean isScrollEnabled = true;
    public MyGridLayoutManger(Context context, int spanCount) {
        super(context,spanCount);
    }
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        }catch (IllegalStateException  e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}

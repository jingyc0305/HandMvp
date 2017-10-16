package jing.honngshi.com.videodatapracticefromcibn.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

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
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

}

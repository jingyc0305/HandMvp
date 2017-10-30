package jing.honngshi.com.videodatapracticefromcibn.category.live.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.ResourceUtil;

/**
 * Created by JIngYuchun on 2017/10/30.
 */

public class LiveViewPageAdapter extends FragmentPagerAdapter{
    private final ArrayList<Fragment> fragments;
    String[] tabTitle;
    Context mContext;
    public LiveViewPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments,Context mContext) {
        super(fm);
        this.fragments = fragments;
        this.mContext = mContext;
        this.tabTitle = ResourceUtil.getStringArray(mContext,R.array.tab_titles_main_home);
    }

    /**
     * 根据位置返回对应的Fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 得到页面的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}

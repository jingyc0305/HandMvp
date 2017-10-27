package jing.honngshi.com.videodatapracticefromcibn.category.vod.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public class VodMainAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragments;
    String[] tabTitle;
    public VodMainAdapter(FragmentManager fm, ArrayList<Fragment> fragments,String[] tabTitle) {
        super(fm);
        this.fragments = fragments;
        this.tabTitle = tabTitle;
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

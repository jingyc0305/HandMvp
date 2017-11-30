package jing.honngshi.com.videodatapracticefromcibn.category.mine.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.httpCommon;

/**
 * Created by JIngYuchun on 2017/10/25.
 */

public class MineFirstFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener{
    @BindView(R.id.mine_tool_bar)
    Toolbar mToolbar;
    ShareAction mShareAction;
    ShareBoardConfig config;
    UMWeb shareWebSit;
    UMusic mUMusic;
    UMVideo mUMVideo;
    public static MineFirstFragment newInstance() {

        Bundle args = new Bundle();

        MineFirstFragment fragment = new MineFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    protected int initLayout() {
        return R.layout.mine_home_fragment;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar,true,"我的");
        mToolbar.inflateMenu(R.menu.toolbar_menu_mine);
        //初始化友盟分享配置
        initShareAction();
        mToolbar.setOnMenuItemClickListener(this);
        shareWebSit = new UMWeb(httpCommon.url);
        mUMusic = new UMusic(httpCommon.musicurl);
        mUMVideo = new UMVideo(httpCommon.videourl);

        config = new ShareBoardConfig();
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM); // 底部弹出
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR); // 圆角背景
    }

    @Override
    protected void initData() {

    }
    /**
     * 初始化友盟分享配置
     */
    private void initShareAction() {
        mShareAction = new ShareAction(_mActivity)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA
                        .MORE)
                .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy",
                        "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl",
                        "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                        if (snsPlatform.mShowWord.equals("umeng_sharebutton_copy")) {
                            Toast.makeText(mContext, "复制文本", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
                            Toast.makeText(mContext, "复制链接", Toast.LENGTH_LONG).show();
                        } else {
                            shareWebSit.setTitle(httpCommon.title);
                            shareWebSit.setThumb(new UMImage(mContext, httpCommon.imageurl));
                            shareWebSit.setDescription(httpCommon.text);

                            mUMVideo.setTitle("战狼Ⅱ");//视频的标题
                            mUMVideo.setThumb(new UMImage(mContext, httpCommon.imageurl));//视频的缩略图
                            mUMVideo.setDescription
                                    ("吴京执导的动作军事电影，由吴京、弗兰克·格里罗、吴刚、张翰、卢靖姗、淳于珊珊、丁海峰等主演");//视频的描述

                            mShareAction
                                    .withMedia(mUMVideo)
                                    .setPlatform(share_media)
                                    .setCallback(umShareListener)
                                    .share();
                        }

                    }
                });
    }
    /**
     * 屏幕横竖屏切换时避免出现window leak的问题
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();
    }
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initVodByTagAdapter() {

    }
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
    }
    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mContext, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_share:
                mShareAction.open(config);
                break;
        }
        return true;
    }
    @Override
    protected Map<String, Object> getRecycleView() {
        Map<String, Object> map = new HashMap<>();

        map.put("RecyclerView", null);
        return map;
    }
}

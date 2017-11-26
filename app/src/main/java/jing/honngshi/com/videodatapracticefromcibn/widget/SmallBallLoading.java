package jing.honngshi.com.videodatapracticefromcibn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import jing.honngshi.com.videodatapracticefromcibn.R;

/**
 * Created by JIngYuchun on 2017/11/3.
 */

public class SmallBallLoading extends LinearLayout {
    private SmallBallView SmallBallViewOne;
    private SmallBallView SmallBallViewTwo;
    private SmallBallView SmallBallViewThree;
    private SmallBallView SmallBallViewFour;
    private SmallBallView SmallBallViewFive;

    ScaleAnimation mScaleAnimation1;//大小
    ScaleAnimation mScaleAnimation2;//大小
    ScaleAnimation mScaleAnimation3;
    ScaleAnimation mScaleAnimation4;
    ScaleAnimation mScaleAnimation5;

    private boolean isStart = false;

    public SmallBallLoading(Context context) {
        super(context);
        initView(context);
    }

    public SmallBallLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SmallBallLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.animation_color_ball, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        SmallBallViewOne = (SmallBallView) findViewById(R.id.ball_one);
        SmallBallViewTwo = (SmallBallView) findViewById(R.id.ball_two);
        SmallBallViewThree = (SmallBallView) findViewById(R.id.ball_three);
        //SmallBallViewFour = (SmallBallView) findViewById(R.id.ball_four);
        //SmallBallViewFive = (SmallBallView) findViewById(R.id.ball_five);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 设置颜色
        SmallBallViewOne.setLoadingColor(getResources().getColor(R.color.red));
        SmallBallViewTwo.setLoadingColor(getResources().getColor(R.color.orange));
        SmallBallViewThree.setLoadingColor(getResources().getColor(R.color.lightgreen));
        //SmallBallViewFour.setLoadingColor(getResources().getColor(R.color.lightgreen));
        //SmallBallViewFive.setLoadingColor(getResources().getColor(R.color.purple));
        //变大变小
        startAni();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }


    public void start() {
        if (!isStart) {
            isStart = true;
            startAni();//开始动画
        }
    }

    public void stop() {
        isStart = false;
        SmallBallViewOne.clearAnimation();
        SmallBallViewTwo.clearAnimation();
        SmallBallViewThree.clearAnimation();
        //SmallBallViewFour.clearAnimation();
        //SmallBallViewFive.clearAnimation();

    }

    public boolean isStart() {
        return isStart;
    }


    public void setLoadingColor(int color) {
        SmallBallViewOne.setLoadingColor(color);
        SmallBallViewTwo.setLoadingColor(color);
        SmallBallViewThree.setLoadingColor(color);
        //SmallBallViewFour.setLoadingColor(color);
        //SmallBallViewFive.setLoadingColor(color);
    }

    public void startAni() {
        mScaleAnimation1 = new ScaleAnimation(1, 0, 1, 0, 1, 0.5f, 1, 0.5f);
        mScaleAnimation1.setDuration(700);
        mScaleAnimation1.setStartOffset(20);
        mScaleAnimation1.setInterpolator(new LinearInterpolator());
        mScaleAnimation1.setRepeatCount(-1);
        mScaleAnimation1.setRepeatMode(Animation.REVERSE);


        mScaleAnimation2 = new ScaleAnimation(1, 0, 1, 0, 1, 0.5f, 1, 0.5f);
        mScaleAnimation2.setDuration(700);
        mScaleAnimation2.setStartOffset(40);
        mScaleAnimation2.setInterpolator(new LinearInterpolator());
        mScaleAnimation2.setRepeatCount(-1);
        mScaleAnimation2.setRepeatMode(Animation.REVERSE);

        mScaleAnimation3 = new ScaleAnimation(1, 0, 1, 0, 1, 0.5f, 1, 0.5f);
        mScaleAnimation3.setDuration(700);
        mScaleAnimation3.setStartOffset(60);
        mScaleAnimation3.setInterpolator(new LinearInterpolator());
        mScaleAnimation3.setRepeatCount(-1);
        mScaleAnimation3.setRepeatMode(Animation.REVERSE);


//        mScaleAnimation4 = new ScaleAnimation(1, 0, 1, 0, 1, 0.5f, 1, 0.5f);
//        mScaleAnimation4.setDuration(700);
//        mScaleAnimation4.setStartOffset(80);
//        mScaleAnimation4.setInterpolator(new LinearInterpolator());
//        mScaleAnimation4.setRepeatCount(-1);
//        mScaleAnimation4.setRepeatMode(Animation.REVERSE);
//
//        mScaleAnimation5 = new ScaleAnimation(1, 0, 1, 0, 1, 0.5f, 1, 0.5f);
//        mScaleAnimation5.setDuration(700);
//        mScaleAnimation5.setStartOffset(100);
//        mScaleAnimation5.setInterpolator(new LinearInterpolator());
//        mScaleAnimation5.setRepeatCount(-1);
//        mScaleAnimation5.setRepeatMode(Animation.REVERSE);


        SmallBallViewOne.startAnimation(mScaleAnimation1);
        SmallBallViewTwo.startAnimation(mScaleAnimation2);
        SmallBallViewThree.startAnimation(mScaleAnimation3);
        //SmallBallViewFour.startAnimation(mScaleAnimation4);
        //SmallBallViewFive.startAnimation(mScaleAnimation5);


    }
}

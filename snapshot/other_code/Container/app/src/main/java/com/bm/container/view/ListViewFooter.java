package com.bm.container.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.container.R;


/**
 * © 2012 amsoft.cn
 * 名称：ListViewFooter.java
 * 描述：加载更多Footer View类.
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2013-01-17 下午11:52:13
 */
public class ListViewFooter extends LinearLayout {

    /**
     * The m context.
     */
    private Context mContext;

    /**
     * The m state.
     */
    private int mState = -1;

    /**
     * The Constant STATE_READY.
     */
    public final static int STATE_READY = 1;

    /**
     * The Constant STATE_LOADING.
     */
    public final static int STATE_LOADING = 2;

    /**
     * The Constant STATE_NO.
     */
    public final static int STATE_NO = 3;

    /**
     * The Constant STATE_EMPTY.
     */
    public final static int STATE_EMPTY = 4;

    /**
     * The footer view.
     */
    private LinearLayout footerView;

    /**
     * The footer text view.
     */
    private TextView footerTextView;

    /**
     * The footer content height.
     */
    private int footerHeight;

    /**
     * Instantiates a new ab list view footer.
     *
     * @param context the context
     */
    public ListViewFooter(Context context) {
        super(context);
        initView(context);

    }

    /**
     * Instantiates a new ab list view footer.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ListViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        setState(STATE_READY);
    }

    /**
     * Inits the view.
     *
     * @param context the context
     */
    private void initView(Context context) {
        mContext = context;

        //底部刷新
        footerView = new LinearLayout(context);
        //设置布局 水平方向
        footerView.setOrientation(LinearLayout.HORIZONTAL);
        footerView.setGravity(Gravity.CENTER);
        footerView.setMinimumHeight(ViewUtil.scaleValue(mContext, 100));
        footerTextView = new TextView(context);
        footerTextView.setGravity(Gravity.CENTER_VERTICAL);
        footerTextView.setTextColor(Color.rgb(107, 107, 107));
        ViewUtil.setTextSize(footerTextView, 30);

        ViewUtil.setPadding(footerView, 0, 10, 0, 10);


        LayoutParams layoutParamsWW1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        footerView.addView(footerTextView, layoutParamsWW1);

        LayoutParams layoutParamsFW = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(footerView, layoutParamsFW);

        //获取View的高度
        ViewUtil.measureView(this);
        footerHeight = this.getMeasuredHeight();
    }

    /**
     * 设置当前状态.
     *
     * @param state the new state
     */
    public void setState(int state) {

        if (state == STATE_READY) {
            footerView.setVisibility(View.VISIBLE);
            footerTextView.setVisibility(View.VISIBLE);
            footerTextView.setText(R.string.purchase_more);
        } else if (state == STATE_LOADING) {
            footerView.setVisibility(View.VISIBLE);
            footerTextView.setVisibility(View.VISIBLE);
            footerTextView.setText(R.string.purchase_more);
        } else if (state == STATE_NO) {
            footerView.setVisibility(View.GONE);
            footerTextView.setVisibility(View.VISIBLE);
            footerTextView.setText(R.string.purchase_more);
        } else if (state == STATE_EMPTY) {
            footerView.setVisibility(View.GONE);
            footerTextView.setVisibility(View.GONE);
            footerTextView.setText(R.string.purchase_more);
        }
        mState = state;
    }

    /**
     * Gets the visiable height.
     *
     * @return the visiable height
     */
    public int getVisiableHeight() {
        LayoutParams lp = (LayoutParams) footerView.getLayoutParams();
        return lp.height;
    }

    /**
     * 隐藏footerView.
     */
    public void hide() {
        LayoutParams lp = (LayoutParams) footerView.getLayoutParams();
        lp.height = 0;
        footerView.setLayoutParams(lp);
        footerView.setVisibility(View.GONE);
    }

    /**
     * 显示footerView.
     */
    public void show() {
        footerView.setVisibility(View.VISIBLE);
        LayoutParams lp = (LayoutParams) footerView.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        footerView.setLayoutParams(lp);
    }


    /**
     * 描述：设置字体颜色.
     *
     * @param color the new text color
     */
    public void setTextColor(int color) {
        footerTextView.setTextColor(color);
    }

    /**
     * 描述：设置字体大小.
     *
     * @param size the new text size
     */
    public void setTextSize(int size) {
        footerTextView.setTextSize(size);
    }

    /**
     * 描述：设置背景颜色.
     *
     * @param color the new background color
     */
    public void setBackgroundColor(int color) {
        footerView.setBackgroundColor(color);
    }


    /**
     * 描述：获取高度.
     *
     * @return the footer height
     */
    public int getFooterHeight() {
        return footerHeight;
    }

    /**
     * 设置高度.
     *
     * @param height 新的高度
     */
    public void setVisiableHeight(int height) {
        if (height < 0) height = 0;
        LayoutParams lp = (LayoutParams) footerView.getLayoutParams();
        lp.height = height;
        footerView.setLayoutParams(lp);
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public int getState() {
        return mState;
    }


}

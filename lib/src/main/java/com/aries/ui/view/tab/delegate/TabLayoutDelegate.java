package com.aries.ui.view.tab.delegate;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.aries.ui.view.tab.R;
import com.aries.ui.view.tab.TextBold;
import com.aries.ui.view.tab.listener.ITabLayout;

/**
 * @Author: AriesHoo on 2018/11/30 15:55
 * @E-Mail: AriesHoo@126.com
 * @Function: TabLayout属性代理类
 * @Description:
 */
public class TabLayoutDelegate<T extends TabLayoutDelegate> {

    protected ITabLayout mITabLayout;
    protected TypedArray mTypedArray;
    protected View mView;
    protected Context mContext;


    protected int mIndicatorColor;
    protected float mIndicatorHeight;
    protected float mIndicatorCornerRadius;
    protected int mIndicatorMarginLeft;
    protected int mIndicatorMarginTop;
    protected int mIndicatorMarginRight;
    protected int mIndicatorMarginBottom;

    protected float mTabPadding;
    protected boolean mTabSpaceEqual;
    protected float mTabWidth;

    /**
     * divider
     */
    protected int mDividerColor;
    protected float mDividerWidth;
    protected float mDividerPadding;

    /**
     * title
     */
    protected int mTextSizeUnit = TypedValue.COMPLEX_UNIT_PX;
    protected float mTextSize;
    protected int mTextSelectColor;
    protected int mTextUnSelectColor;
    protected int mTextBold;
    protected boolean mTextAllCaps;

    public TabLayoutDelegate(View view, AttributeSet attrs, ITabLayout iTabLayout) {
        mITabLayout = iTabLayout;
        mView = view;
        mContext = view.getContext();
        mTypedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TabLayout);

        mIndicatorColor = mTypedArray.getColor(R.styleable.TabLayout_tl_indicator_color, Color.parseColor("#4B6A87"));
        mIndicatorHeight = mTypedArray.getDimension(R.styleable.TabLayout_tl_indicator_height, dp2px(4));
        mIndicatorCornerRadius = mTypedArray.getDimension(R.styleable.TabLayout_tl_indicator_corner_radius, dp2px(0));
        mIndicatorMarginLeft = mTypedArray.getDimensionPixelSize(R.styleable.TabLayout_tl_indicator_margin_left, dp2px(0));
        mIndicatorMarginTop = mTypedArray.getDimensionPixelSize(R.styleable.TabLayout_tl_indicator_margin_top, dp2px(0));
        mIndicatorMarginRight = mTypedArray.getDimensionPixelSize(R.styleable.TabLayout_tl_indicator_margin_right, dp2px(0));
        mIndicatorMarginBottom = mTypedArray.getDimensionPixelSize(R.styleable.TabLayout_tl_indicator_margin_bottom, dp2px(0));

        mDividerColor = mTypedArray.getColor(R.styleable.TabLayout_tl_divider_color, Color.parseColor("#ffffff"));
        mDividerWidth = mTypedArray.getDimension(R.styleable.TabLayout_tl_divider_width, dp2px(0));
        mDividerPadding = mTypedArray.getDimension(R.styleable.TabLayout_tl_divider_padding, dp2px(12));

        mTextSize = mTypedArray.getDimensionPixelSize(R.styleable.TabLayout_tl_textSize, dp2px(14));
        mTextSelectColor = mTypedArray.getColor(R.styleable.TabLayout_tl_textSelectColor, Color.parseColor("#ffffff"));
        mTextUnSelectColor = mTypedArray.getColor(R.styleable.TabLayout_tl_textUnSelectColor, Color.parseColor("#AAffffff"));
        mTextBold = mTypedArray.getInt(R.styleable.TabLayout_tl_textBold, TextBold.NONE);
        mTextAllCaps = mTypedArray.getBoolean(R.styleable.TabLayout_tl_textAllCaps, false);

        mTabSpaceEqual = mTypedArray.getBoolean(R.styleable.TabLayout_tl_tab_space_equal, true);
        mTabWidth = mTypedArray.getDimension(R.styleable.TabLayout_tl_tab_width, dp2px(-1));
        mTabPadding = mTypedArray.getDimension(R.styleable.TabLayout_tl_tab_padding, mTabSpaceEqual || mTabWidth > 0 ? dp2px(0) : dp2px(10));
    }

    protected T back() {
        return back(false);
    }

    protected T back(boolean style) {
        if (style) {
            mITabLayout.updateTabStyles();
        } else {
            mView.invalidate();
        }
        return (T) this;
    }

    public T setIndicatorColor(int indicatorColor) {
        this.mIndicatorColor = indicatorColor;
        return back();
    }

    public T setIndicatorHeight(float indicatorHeight) {
        return setIndicatorHeight(dp2px(indicatorHeight));
    }

    /**
     * 新增方法
     *
     * @param indicatorHeight
     * @return
     */
    public T setIndicatorHeight(int indicatorHeight) {
        this.mIndicatorHeight = indicatorHeight;
        return back();
    }

    public T setIndicatorCornerRadius(float indicatorCornerRadius) {
        this.mIndicatorCornerRadius = dp2px(indicatorCornerRadius);
        return back();
    }

    /**
     * 新增方法
     *
     * @param indicatorMarginLeft
     * @param indicatorMarginTop
     * @param indicatorMarginRight
     * @param indicatorMarginBottom
     * @return
     */
    public T setIndicatorMargin(int indicatorMarginLeft, int indicatorMarginTop,
                                int indicatorMarginRight, int indicatorMarginBottom) {
        this.mIndicatorMarginLeft = indicatorMarginLeft;
        this.mIndicatorMarginTop = indicatorMarginTop;
        this.mIndicatorMarginRight = indicatorMarginRight;
        this.mIndicatorMarginBottom = indicatorMarginBottom;
        return back();
    }


    public T setDividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        return back();
    }

    public T setDividerWidth(float dividerWidth) {
        this.mDividerWidth = dividerWidth;
        return back();
    }

    public T setDividerPadding(float dividerPadding) {
        this.mDividerPadding = dividerPadding;
        return back();
    }


    /**
     * 设置文字尺寸 --新增
     *
     * @param textSizeUnit 单位 {@link TypedValue}
     * @param textSize
     * @return
     */
    public T setTextSize(int textSizeUnit, float textSize) {
        this.mTextSize = textSize;
        this.mTextSizeUnit = textSizeUnit;
        return back(true);
    }

    /**
     * @param textSize
     * @return
     */
    public T setTextSize(float textSize) {
        return setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    public T setTextSelectColor(int textSelectColor) {
        this.mTextSelectColor = textSelectColor;
        return back(true);
    }

    /**
     * 新增
     *
     * @param textColor
     * @return
     */
    public T setTextUnSelectColor(int textColor) {
        this.mTextUnSelectColor = textColor;
        return back(true);
    }

    /**
     * 设置文字粗体效果{@link TextBold#NONE}{@link TextBold#SELECT}{@link TextBold#BOTH}
     *
     * @param textBold
     * @return
     */
    public T setTextBold(int textBold) {
        this.mTextBold = textBold;
        return back(true);
    }

    public T setTextAllCaps(boolean textAllCaps) {
        this.mTextAllCaps = textAllCaps;
        return back(true);
    }

    public int getIndicatorColor() {
        return mIndicatorColor;
    }

    public float getIndicatorHeight() {
        return mIndicatorHeight;
    }


    public float getIndicatorCornerRadius() {
        return mIndicatorCornerRadius;
    }

    public int getIndicatorMarginLeft() {
        return mIndicatorMarginLeft;
    }

    public int getIndicatorMarginTop() {
        return mIndicatorMarginTop;
    }

    public int getIndicatorMarginRight() {
        return mIndicatorMarginRight;
    }

    public int getIndicatorMarginBottom() {
        return mIndicatorMarginBottom;
    }

    public View getView() {
        return mView;
    }

    public Context getContext() {
        return mContext;
    }

    public float getTabPadding() {
        return mTabPadding;
    }

    public boolean isTabSpaceEqual() {
        return mTabSpaceEqual;
    }

    public float getTabWidth() {
        return mTabWidth;
    }

    public int getDividerColor() {
        return mDividerColor;
    }

    public float getDividerWidth() {
        return mDividerWidth;
    }

    public float getDividerPadding() {
        return mDividerPadding;
    }

    public int getTextSizeUnit() {
        return mTextSizeUnit;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public int getTextSelectColor() {
        return mTextSelectColor;
    }

    public int getTextUnSelectColor() {
        return mTextUnSelectColor;
    }

    public int getTextBold() {
        return mTextBold;
    }

    public boolean isTextAllCaps() {
        return mTextAllCaps;
    }

    public int dp2px(float dp) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}

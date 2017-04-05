package com.luck.indicator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * author：luck
 * project：EasyIndicator
 * package：com.luck.indicator
 * email：893855882@qq.com
 * data：2017/4/1
 * GitHub： https://github.com/LuckSiege
 */

public class EasyIndicator extends LinearLayout implements View.OnClickListener, ViewPager.OnPageChangeListener {
    public final static String TAG = "EasyIndicator";
    private View indicator;
    private LinearLayout tab_content;
    private TextView view;
    private TextView[] tvs;
    public int screenWidth;
    public int screenHeight;
    private int currIndex;// 当前页卡编号
    public onTabClickListener onTabClickListener;
    private int indicatorHeight = 45;// tab默认高度
    private int indicator_width = -1;// tab宽度,默认填充全屏
    private int indicator_bottom_line_height = 1;// 指示器底部线条高度
    private int indicator_vertical_line_w = 0;// 中间分割线宽度
    private int indicator_vertical_line_h = 0;// 中间分割线高度
    private int indicator_bottom_height = 3;//指示器默认高度
    private int indicator_bottom_line_color;// 指示器底部线条颜色
    private int indicator_line_color;// 指示器颜色
    private int indicator_vertical_line_color;//// 中间分割线颜色
    private int indicator_selected_color, indicator_normal_color;// 选中颜色和默认颜色
    private float indicator_textSize = 14;// 默认字体大小
    private float indicator_select_textSize = indicator_textSize; // 选中字体大小
    private boolean indicator_isBottom_line = true;// 是否显示指示器
    private int position;
    private ViewPager viewPager;

    public EasyIndicator(Context context) {
        super(context);
    }

    public EasyIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyIndicator);
        setOrientation(VERTICAL);
        initScreenWidth();
        init(a);
    }

    /**
     * 初始化View
     */
    private void init(TypedArray a) {

        // indicator height
        indicatorHeight = (int) getDimensionPixelSize(a, R.styleable.MyIndicator_indicator_height, indicatorHeight);
        // indicator_bottom_height
        indicator_bottom_height = (int) getDimensionPixelSize(a, R.styleable.MyIndicator_indicator_bottom_height, indicator_bottom_height);
        // indicator_bottom_line_height
        indicator_bottom_line_height = (int) getDimensionPixelSize(a, R.styleable.MyIndicator_indicator_bottom_line_height, indicator_bottom_line_height);
        // indicator_bottom_line_color
        indicator_bottom_line_color = a.getColor(R.styleable.MyIndicator_indicator_bottom_line_color, Color.rgb(238, 238, 238));
        // indicator_selected_color
        indicator_selected_color = a.getColor(R.styleable.MyIndicator_indicator_selected_color, Color.rgb(255, 87, 46));
        // indicator_normal_color
        indicator_normal_color = a.getColor(R.styleable.MyIndicator_indicator_normal_color, Color.rgb(112, 112, 112));
        // indicator_line_color
        indicator_line_color = a.getColor(R.styleable.MyIndicator_indicator_line_color, Color.rgb(255, 87, 46));
        // indicator_textSize
        indicator_textSize = getDimensionPixelSize(a, R.styleable.MyIndicator_indicator_textSize, (int) indicator_textSize);
        // indicator_isBottom_line
        indicator_isBottom_line = a.getBoolean(R.styleable.MyIndicator_indicator_isBottom_line, indicator_isBottom_line);
        // indicator_vertical_line
        indicator_vertical_line_w = (int) getDimensionPixelSize(a, R.styleable.MyIndicator_indicator_vertical_line_w, indicator_vertical_line_w);
        // indicator_vertical_line_color
        indicator_vertical_line_color = a.getColor(R.styleable.MyIndicator_indicator_vertical_line_color, indicator_vertical_line_color);
        // indicator_vertical_line_h
        indicator_vertical_line_h = (int) getDimensionPixelSize(a, R.styleable.MyIndicator_indicator_vertical_line_h, indicator_vertical_line_h);
        // indicator_width
        indicator_width = (int) getDimensionPixelSize(a, R.styleable.MyIndicator_indicator_width, indicator_width);
        // indicator_select_textSize
        indicator_select_textSize = getDimensionPixelSize(a, R.styleable.MyIndicator_indicator_select_textSize, 14);
        if (indicator_width == 0) {
            indicator_width = -1;
        }

        a.recycle();

        tab_content = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(indicator_width, LayoutParams.WRAP_CONTENT);
        tab_content.setBackgroundColor(Color.WHITE);
        params.gravity = Gravity.CENTER;
        tab_content.setLayoutParams(params);
    }

    /**
     * 设置tab item
     *
     * @param tabTitles
     */

    public void setTabTitles(String[] tabTitles) {
        // Create tab
        tvs = new TextView[tabTitles.length];

        for (int i = 0; i < tabTitles.length; i++) {
            view = new TextView(getContext());
            view.setTag(i);
            view.setText(tabTitles[i]);

            view.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams lp = new LayoutParams(0, indicatorHeight, 1.0f);
            view.setLayoutParams(lp);
            switch (i) {
                case 0:
                    view.setTextColor(indicator_selected_color);
                    view.setTextSize(TypedValue.COMPLEX_UNIT_PX, indicator_select_textSize);
                    break;
                default:
                    view.setTextColor(indicator_normal_color);
                    view.setTextSize(TypedValue.COMPLEX_UNIT_PX, indicator_textSize);
                    break;
            }
            view.setOnClickListener(this);
            tvs[i] = view;
            tab_content.addView(view);
            if (i != tabTitles.length - 1) {
                View line = new View(getContext());
                line.setBackgroundColor(indicator_vertical_line_color);
                LinearLayoutCompat.LayoutParams compat = new LinearLayoutCompat.LayoutParams(indicator_vertical_line_w, indicator_vertical_line_h);
                line.setLayoutParams(compat);
                tab_content.addView(line);
            }
        }

        addView(tab_content);

        if (indicator_isBottom_line) {
            // Create tab indicator
            indicator = new View(getContext());
            int iw = 0;
            if (indicator_width == 0 || indicator_width == -1) {
                iw = screenWidth / tvs.length;
            }
            indicator.setLayoutParams(new LinearLayoutCompat.LayoutParams(iw, indicator_bottom_height));
            // Executive animation
            tvs[0].post(new Runnable() {
                @Override
                public void run() {
                    buildIndicatorAnimatorTowards(tvs[0]).start();
                }
            });
            indicator.setBackgroundColor(indicator_line_color);
            addView(indicator);
        }

        // Create tab bottom line
        View line = new View(getContext());
        line.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, indicator_bottom_line_height));
        line.setBackgroundColor(indicator_bottom_line_color);
        addView(line);

    }

    /**
     * 内部设置
     *
     * @param adapter
     */
    public void setViewPage(FragmentPagerAdapter adapter) {
        viewPager = new ViewPager(getContext());
        viewPager.setId(R.id.vp);
        viewPager.setLayoutParams(new LinearLayoutCompat.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
        addView(viewPager);
    }

    /**
     * 外部设置ViewPage
     * 自定义
     *
     * @param viewPage
     * @param adapter
     */
    public void setViewPage(ViewPager viewPage, FragmentPagerAdapter adapter) {
        viewPager = viewPage;
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }


    private AnimatorSet buildIndicatorAnimatorTowards(TextView tv) {
        float x = tab_content.getX();
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(indicator, "X", indicator.getX(), tv.getX() + x);
        final ViewGroup.LayoutParams params = indicator.getLayoutParams();
        ValueAnimator widthAnimator = ValueAnimator.ofInt(params.width, tv.getMeasuredWidth());
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.width = (Integer) animation.getAnimatedValue();
                indicator.setLayoutParams(params);
            }
        });
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.playTogether(xAnimator, widthAnimator);
        return set;
    }


    @Override
    public void onClick(View v) {
        TextView tv = ((TextView) v);
        if (onTabClickListener != null) {
            position = (int) v.getTag();
            if (viewPager != null) {
                viewPager.setCurrentItem(position);
            } else {
                setSelectorColor(tv);
                if (indicator_isBottom_line) {
                    buildIndicatorAnimatorTowards(tv).start();
                }
            }
            onTabClickListener.onTabClick(((TextView) v).getText().toString(), position);
        }
    }

    private void setSelectorColor(TextView tv) {
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setTextColor(indicator_normal_color);
            tvs[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, indicator_textSize);
        }
        tv.setTextColor(indicator_selected_color);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, indicator_select_textSize);
    }

    public void setOnTabClickListener(EasyIndicator.onTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) indicator
                .getLayoutParams();
        if (currIndex == position) {
            ll.leftMargin = (int) (currIndex * indicator.getMeasuredWidth() + positionOffset
                    * indicator.getMeasuredWidth());
        } else if (currIndex > position) {
            ll.leftMargin = (int) (currIndex * indicator.getMeasuredWidth() - (1 - positionOffset)
                    * indicator.getMeasuredWidth());
        }

        indicator.setLayoutParams(ll);
    }

    @Override
    public void onPageSelected(int position) {
        currIndex = position;
        setSelectorColor(tvs[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public interface onTabClickListener {
        void onTabClick(String title, int position);
    }


    /**
     * get the dimension pixel size from typeArray which is defined in attr
     *
     * @param typeArray   TypedArray
     * @param stylable    stylable
     * @param defaultSize defaultSize
     * @return the dimension pixel size
     */
    private float getDimensionPixelSize(TypedArray typeArray, int stylable, int defaultSize) {
        int sizeStyleable = typeArray.getResourceId(stylable, 0);
        return sizeStyleable > 0 ? typeArray.getResources().getDimensionPixelSize(sizeStyleable) : typeArray.getDimensionPixelSize(stylable, (int) getPiselSizeBySp(defaultSize));
    }

    /**
     * get Pixel size by sp
     *
     * @param sp sp
     * @return the value of px
     */
    private float getPiselSizeBySp(int sp) {
        Resources res = this.getResources();
        final float scale = res.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }


    /**
     * 初始化屏幕宽高
     */
    private void initScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;

    }

}

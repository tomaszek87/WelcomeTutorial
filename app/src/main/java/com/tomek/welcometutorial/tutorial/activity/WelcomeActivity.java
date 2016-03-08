package com.tomek.welcometutorial.tutorial.activity;

import android.animation.ArgbEvaluator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.tomek.welcometutorial.R;
import com.tomek.welcometutorial.tutorial.adapter.WelcomeAdapter;
import com.tomek.welcometutorial.utils.activity.BaseActivity;

import butterknife.Bind;

public class WelcomeActivity extends BaseActivity {


    @Bind(R.id.container)
    ViewPager mViewPager;
    @Bind(R.id.intro_btn_next)
    ImageButton mNextBtn;
    @Bind(R.id.intro_btn_finish)
    Button mFinishBtn;
    @Bind(R.id.intro_btn_skip)
    Button mSkipBtn;
    @Bind(R.id.intro_indicator_0)
    ImageView zero;
    @Bind(R.id.intro_indicator_1)
    ImageView one;
    @Bind(R.id.intro_indicator_2)
    ImageView two;
    ImageView[] indicators;
    private int page = 0;
    private WelcomeAdapter adapter;
    private int color1;
    private int color2;
    private int color3;
    private int[] colorList;
    private ArgbEvaluator evaluator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initPager();
    }

    private void initPager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_trans80));
        }
        initColors();
        adapter = new WelcomeAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        indicators = new ImageView[]{zero, one, two};
        mViewPager.setCurrentItem(page);
        updateIndicators(page);
        evaluator = new ArgbEvaluator();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int colorUpdate = (Integer) evaluator.evaluate(positionOffset, colorList[position], colorList[position == 2 ? position : position + 1]);
                mViewPager.setBackgroundColor(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                page = position;
                updateIndicators(page);
                switch (position) {
                    case 0:
                        mViewPager.setBackgroundColor(color1);
                        break;
                    case 1:
                        mViewPager.setBackgroundColor(color2);
                        break;
                    case 2:
                        mViewPager.setBackgroundColor(color3);
                        break;
                }
                mNextBtn.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
                mFinishBtn.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        initButtons();
    }

    private void initButtons() {
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page += 1;
                mViewPager.setCurrentItem(page, true);
            }
        });

        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initColors() {
        color1 = ContextCompat.getColor(this, R.color.md_blue_grey_700);
        color2 = ContextCompat.getColor(this, R.color.md_green_500);
        color3 = ContextCompat.getColor(this, R.color.md_blue_500);
        colorList = new int[]{color1, color2, color3};
    }

    void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }

}

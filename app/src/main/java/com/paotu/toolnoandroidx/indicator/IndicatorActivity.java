package com.paotu.toolnoandroidx.indicator;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.paotu.toolnoandroidx.R;

import java.util.ArrayList;

public class IndicatorActivity extends AppCompatActivity implements OnTabSelectListener {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    //    private final String[] mTitles = {
//            "热门", "iOS", "Android"
//            , "前端", "后端", "设计", "工具资源"
//    };
//    private final String[] mTitles = {
//            "最新", "热门", "动画片"
//            , "好友在学", "英语歌曲", "影视", "工具资源"
//    };
    private final String[] mTitles = {
            "最新", "热门", "好友在学"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);

        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }


        final SlidingTabLayout slidingTabLayout = findViewById(R.id.stl);

        slidingTabLayout.setTextsize(16f);
        slidingTabLayout.setTextUnselectColor(Color.parseColor("#666666"));
        slidingTabLayout.setTextSelectColor(Color.parseColor("#32d2ff"));
        slidingTabLayout.setIndicatorColor(Color.parseColor("#32d2ff"));
        slidingTabLayout.setTextBold(SlidingTabLayout.TEXT_BOLD_WHEN_SELECT);

        slidingTabLayout.setIndicatorHeight(4f);
        slidingTabLayout.setIndicatorWidth(20f);
        slidingTabLayout.setIndicatorCornerRadius(2f);


//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        int w = dm.widthPixels;

        if (mTitles.length > 4) {
            slidingTabLayout.setTabPadding(15f);
        } else {
            slidingTabLayout.setTabSpaceEqual(true);
        }


        ViewPager viewPager = findViewById(R.id.viewPager);
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(viewPager);

        slidingTabLayout.setSnapOnTabClick(true);

        slidingTabLayout.setCurrentTab(1);

        if (mTitles.length <= 4) {

            slidingTabLayout.showDot(0);
            slidingTabLayout.showDot(3);



        }
//        slidingTabLayout.hideMsg(0);

        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                slidingTabLayout.hideMsg(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void onTabSelect(int position) {
        Toast.makeText(this, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselect(int position) {
        Toast.makeText(this, "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
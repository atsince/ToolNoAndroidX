package com.paotu.toolnoandroidx.uservisible.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.paotu.toolnoandroidx.R;
import com.paotu.toolnoandroidx.uservisible.BaseFragment;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class FragmentWithViewPager extends BaseFragment implements OnTabSelectListener {


    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "tab1", "tab2", "tab3"

    };


    private String mTitle;

     @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        boolean b = getUserVisibleHint();
        Log.i("Kevin","userVisibleHint:"+b+" "+mTitle);
    }
    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        Log.i("Kevin2","isVisibleToUser: "+isVisibleToUser+" invokeInResumeOrPause:"+invokeInResumeOrPause+" mTitle:"+mTitle);
    }
    public static FragmentWithViewPager getInstance(String title) {
        FragmentWithViewPager sf = new FragmentWithViewPager();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment2.getInstance(title));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_indicator, null);



        final SlidingTabLayout slidingTabLayout = v.findViewById(R.id.stl);

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


        ViewPager viewPager = v.findViewById(R.id.viewPager);
        FragmentWithViewPager.MyPagerAdapter mAdapter = new FragmentWithViewPager.MyPagerAdapter(getChildFragmentManager());
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
        return v;
    }

    @Override
    public void onTabSelect(int position) {
    }

    @Override
    public void onTabReselect(int position) {
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
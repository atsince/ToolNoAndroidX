package com.paotu.toolnoandroidx.uservisible.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paotu.toolnoandroidx.R;
import com.paotu.toolnoandroidx.uservisible.BaseFragment;


@SuppressLint("ValidFragment")
public class SimpleCardFragment2 extends BaseFragment {
    private String mTitle;

    public static SimpleCardFragment2 getInstance(String title) {
        SimpleCardFragment2 sf = new SimpleCardFragment2();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        boolean b = getUserVisibleHint();
        Log.i("Kevin", "userVisibleHint:" + b + " " + mTitle);
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        Log.i("Kevin2", "isVisibleToUser: " + isVisibleToUser + " invokeInResumeOrPause:" + invokeInResumeOrPause + " mTitle:" + mTitle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_simple_card, null);
        TextView card_title_tv = (TextView) v.findViewById(R.id.card_title_tv);
        card_title_tv.setText(mTitle);

        return v;
    }
}
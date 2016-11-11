package com.miao.android.rxjavatest.ui.activity;

import android.support.v4.app.Fragment;

import com.miao.android.rxjavatest.ui.fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}

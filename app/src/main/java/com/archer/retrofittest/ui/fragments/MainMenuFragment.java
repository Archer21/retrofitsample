package com.archer.retrofittest.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.retrofittest.R;

public class MainMenuFragment extends Fragment {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.content_main_menu, container, false);
    }

}

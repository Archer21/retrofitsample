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
import com.archer.retrofittest.ui.adapters.PagerAdapter;

import java.util.ArrayList;

public class MainMenuFragment extends Fragment {

//    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.content_main_menu, container, false);
//        toolbar   = (Toolbar)   root.findViewById(R.id.toolbar);
        viewPager = (ViewPager) root.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) root.findViewById(R.id.tabLayout);

        setupViewPager();


        return root;
    }

    private ArrayList<Fragment> buildFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        SongsFragment songsFragment = new SongsFragment();
        TopArtistsFragment topArtistsFragment = new TopArtistsFragment();

        fragments.add(songsFragment);
        fragments.add(topArtistsFragment);


        return fragments;
    }

    private void setupViewPager() {
        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager(), buildFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_hyped_active);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_top_active);

    }

}

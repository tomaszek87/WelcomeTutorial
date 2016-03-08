package com.tomek.welcometutorial.tutorial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tomek.welcometutorial.tutorial.fragment.WelcomeFragment;

/**
 * Created by Tomek on 20.02.2016.
 */
public class WelcomeAdapter extends FragmentPagerAdapter {




    public WelcomeAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return WelcomeFragment.newInstance(position + 1);


    }


    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }


}

package com.example.tl.secondarymarket.tool;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.tl.secondarymarket.fragment.HomePage;
import com.example.tl.secondarymarket.MainActivity;
import com.example.tl.secondarymarket.fragment.Chat;
import com.example.tl.secondarymarket.fragment.Publish;
import com.example.tl.secondarymarket.fragment.Mine;

/**
 * Created by gesangdianzi on 2017/3/13.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private HomePage myFragment1 = null;
    private Publish myFragment2 = null;
    private Mine myFragment3 = null;
    private Chat myFragment4 = null;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new HomePage() ;
        myFragment2 = new Publish();
        myFragment3 = new Mine();
        myFragment4 = new Chat() ;
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

}
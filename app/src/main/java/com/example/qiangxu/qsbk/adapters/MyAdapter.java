package com.example.qiangxu.qsbk.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.qiangxu.qsbk.domain.LeftMenuTitle;
import com.example.qiangxu.qsbk.fragments.BlankFragment;

import java.util.List;

/**
 * Created by QiangXu on 2015/12/28.
 */
public class MyAdapter extends FragmentPagerAdapter {

    private List<LeftMenuTitle> list;


    public MyAdapter(FragmentManager fm, List<LeftMenuTitle> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Fragment getItem(int position) {
        return BlankFragment.newInstance(list.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }
}

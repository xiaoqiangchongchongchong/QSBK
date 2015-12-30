package com.example.qiangxu.qsbk.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.qiangxu.qsbk.domain.LeftMenuTitle;
import com.example.qiangxu.qsbk.fragments.BlankFragment;
import com.example.qiangxu.qsbk.fragments.DetailFragment;

import java.util.List;

/**
 * Created by QiangXu on 2015/12/30.
 */
public class DetailAdapter extends FragmentPagerAdapter {

    private List<LeftMenuTitle> list;
    private long suggestId;


    public DetailAdapter(FragmentManager fm, List<LeftMenuTitle> list, long suggestId ) {
        super(fm);
        this.list = list;
        this.suggestId = suggestId;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(list.get(position), suggestId);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }

}

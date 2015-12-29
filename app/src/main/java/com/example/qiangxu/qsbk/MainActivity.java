package com.example.qiangxu.qsbk;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.qiangxu.qsbk.adapters.MyAdapter;
import com.example.qiangxu.qsbk.fragments.BlankFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView menu;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ViewPager pager;
    private List<String> list;
    private MyAdapter myAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer);

        menu = (NavigationView) findViewById(R.id.expend_menu);

        menu.setNavigationItemSelectedListener(this);

        //menu.setOnClickListener(this);
        //显示Home
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, 0, 0);
        //三条横线的MEnu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle.syncState();

        //由DrawerLayout控制Toggle
        drawer.setDrawerListener(actionBarDrawerToggle);

        pager = (ViewPager) findViewById(R.id.viewpager);

        list = new ArrayList<String>();
        list.add("专享");
        list.add("视频");
        list.add("纯文");
        list.add("纯图");
        list.add("精华");

        myAdapter = new MyAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(myAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        tabLayout.setupWithViewPager(pager);



    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_1:

                break;
            case R.id.item_2:
                list.clear();
                list.add("糗事列表");
                myAdapter.notifyDataSetChanged();
                pager.setCurrentItem(5);
                pager.setAdapter(myAdapter);

                tabLayout.setupWithViewPager(pager);
                break;
            case R.id.group_1:

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

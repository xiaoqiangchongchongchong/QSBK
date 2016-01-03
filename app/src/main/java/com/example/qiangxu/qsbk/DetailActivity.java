package com.example.qiangxu.qsbk;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiangxu.qsbk.adapters.DetailAdapter;
import com.example.qiangxu.qsbk.domain.Common;
import com.example.qiangxu.qsbk.domain.LeftMenuTitle;
import com.example.qiangxu.qsbk.domain.Suggest;
import com.example.qiangxu.qsbk.fragments.DetailFragment;
import com.example.qiangxu.qsbk.interfaces.CommonService;
import com.example.qiangxu.qsbk.utils.getIcon;
import com.example.qiangxu.qsbk.utils.getImage;
import com.example.qiangxu.qsbk.views.CircleTransFormation;
import com.example.qiangxu.qsbk.views.VideoDraw;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class DetailActivity extends AppCompatActivity {

    private ViewPager detailViewPager;
    private TabLayout detailTabLayout;
    private List<LeftMenuTitle> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ScrollView scrollView = (ScrollView) findViewById(R.id.detail_layout);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Suggest.ItemsEntity item = (Suggest.ItemsEntity) bundle.getSerializable("item");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("糗事百科 " + item.getId());
        }
        final ViewHolder holder = new ViewHolder();
        if(item.getUser() != null){
            holder.user_name.setText(item.getUser().getLogin());
            if(item.getUser().getIcon() != "") {
                Picasso.with(this).load(getIcon.getIconURL(item.getUser().getId(),
                        item.getUser().getIcon()))
                        .transform(new CircleTransFormation())
                        .into(holder.user_icon);
            }else{
                Picasso.with(this).load(R.mipmap.ic_launcher)
                        .transform(new CircleTransFormation())
                        .into(holder.user_icon);
            }

        }else{
            holder.user_name.setText("匿名用户");
            Picasso.with(this).load(R.mipmap.ic_launcher)
                    .transform(new CircleTransFormation())
                    .into(holder.user_icon);
        }
        if(item.getType() != null) {
            if (item.getType().equals("hot")) {
                holder.jingxuan.setText("热门");
                holder.type_icon.setImageResource(R.mipmap.ic_rss_hot);
            }
        }
        holder.content.setText(item.getContent().trim());
        if(item.getImage() == null){
            if(item.getPic_url() == null){
                holder.image.setVisibility(View.GONE);
            }else {
                holder.image.setVisibility(View.VISIBLE);
                Picasso.with(this)
                        .load(item.getPic_url())
                        .transform(new VideoDraw(this))
                        .resize(this.getWindowManager().getDefaultDisplay().getWidth(), 0)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(holder.image);
            }
        }else{
            holder.image.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(getImage.getImageURL((String) item.getImage()))
                    .resize(this.getWindowManager().getDefaultDisplay().getWidth(), 0)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.image);
        }
        holder.pinglun.setText(item.getComments_count() + "");
        holder.fenxiang.setText(item.getShare_count() + "");

        list = new ArrayList<LeftMenuTitle>();
        list.add(new LeftMenuTitle("全部",1));
        list.add(new LeftMenuTitle("热门", 2));

        detailViewPager = (ViewPager) findViewById(R.id.detail_viewpager);
        detailTabLayout = (TabLayout) findViewById(R.id.detail_tablayout);

        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager(), list, item.getId());
        detailViewPager.setAdapter(detailAdapter);
        detailTabLayout.setupWithViewPager(detailViewPager);
        holder.btnhaoxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnhaoxiao.setImageResource(R.mipmap.operation_support_press);
                holder.btnbuhaoxiao.setImageResource(R.mipmap.operation_unsupport);
            }
        });

        holder.btnbuhaoxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnhaoxiao.setImageResource(R.mipmap.operation_support);
                holder.btnbuhaoxiao.setImageResource(R.mipmap.operation_unsupport_press);
            }
        });

        //final int page = 2;

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            private  int page = 2;
            private  DetailFragment detailFragment;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:
                        int scrollY=v.getScrollY();
                        int height=v.getHeight();
                        int scrollViewMeasuredHeight=scrollView.getChildAt(0).getMeasuredHeight();
                        if(scrollY==0){
                            System.out.println("滑动到了顶端 view.getScrollY()="+scrollY);
                        }
                        if((scrollY+height)==scrollViewMeasuredHeight){
                            long id = item.getId();

                            Log.d("page", page + "");
                            detailFragment = new DetailFragment();
                            detailFragment.initData(1,id,page);
                            //DetailFragment
                            page ++;

                        }
                        break;

                    default:
                        break;
                }
                return false;
            }
        });



    }

    private class ViewHolder {

        private ImageView user_icon;
        private TextView user_name;
        private TextView jingxuan;
        private TextView content;
        private ImageView image;
        private ImageView type_icon;
        private TextView haoxiao;
        private TextView pinglun;
        private TextView fenxiang;
        private ImageButton btnhaoxiao;
        private ImageButton btnbuhaoxiao;

        public ViewHolder() {
            user_icon = (ImageView) findViewById(R.id.user_icon);
            user_name = (TextView) findViewById(R.id.user_name);
            jingxuan = (TextView) findViewById(R.id.jingxuan);
            content = (TextView) findViewById(R.id.content);
            image = (ImageView) findViewById(R.id.image);
            type_icon = (ImageView) findViewById(R.id.type_icon);
            haoxiao = (TextView) findViewById(R.id.haoxiao);
            pinglun = (TextView) findViewById(R.id.pinglun);
            fenxiang = (TextView) findViewById(R.id.fenxiang);
            btnhaoxiao = (ImageButton) findViewById(R.id.btncommon_haoxiao);
            btnbuhaoxiao = (ImageButton) findViewById(R.id.btncommon_buhaoxiao);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}

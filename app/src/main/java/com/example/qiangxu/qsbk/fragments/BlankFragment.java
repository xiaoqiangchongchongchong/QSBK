package com.example.qiangxu.qsbk.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qiangxu.qsbk.R;
import com.example.qiangxu.qsbk.adapters.GongXiangAdapter;
import com.example.qiangxu.qsbk.domain.LeftMenuTitle;
import com.example.qiangxu.qsbk.domain.Suggest;
import com.example.qiangxu.qsbk.interfaces.QsbkService;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements Callback<Suggest>, PullToRefreshBase.OnRefreshListener2<ListView>{

    private Call<com.example.qiangxu.qsbk.domain.Suggest> call;
    private GongXiangAdapter adapter;
    private PullToRefreshListView listView;
    private QsbkService service;
    private int page;
    private PtrClassicFrameLayout swipe;
    private int pageCount;
    //private Call<com.example.qiangxu.qsbk.domain.Suggest> call;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(LeftMenuTitle leftMenuTitle){
        Bundle args = new Bundle();

        BlankFragment fragment = new BlankFragment();
        args.putString("text", leftMenuTitle.getTitle());
        args.putInt("flag", leftMenuTitle.getFlag());
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret =  inflater.inflate(R.layout.fragment_blank, container, false);
//        listView = (ListView) ret.findViewById(R.id.common_list);
        return ret;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (PullToRefreshListView) view.findViewById(R.id.common_list);
        int flag = getArguments().getInt("flag");
        if(flag == 1){
            adapter = new GongXiangAdapter(getActivity());
            listView.setAdapter(adapter);
            Retrofit build = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com").addConverterFactory(GsonConverterFactory.create()).build();
            service = build.create(QsbkService.class);

            page = 1;
//            swipe = (PtrClassicFrameLayout) view.findViewById(R.id.store_house_ptr_frame);
//            StoreHouseHeader header = new StoreHouseHeader(getContext());
//            header.setTextColor(Color.BLACK);
//            swipe.setKeepHeaderWhenRefresh(true);
//            swipe.setPtrHandler(this);
            call = service.getList("suggest", 1);
            call.enqueue(this);


            listView.setMode(PullToRefreshBase.Mode.BOTH);
            listView.setOnRefreshListener(this);


        }
    }

    @Override
    public void onResponse(retrofit.Response<com.example.qiangxu.qsbk.domain.Suggest> response, Retrofit retrofit) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(response.body().getItems());
        pageCount = response.body().getCount();
        listView.onRefreshComplete();
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
        listView.onRefreshComplete();
    }

    /**
     * 下拉刷新
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        call = service.getList("suggest", 1);
        call.enqueue(this);
    }

    /**
     * 上拉加载
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        if (page <= pageCount){
            page++;
        }else{
            Toast.makeText(getActivity(), "到头了" , Toast.LENGTH_SHORT).show();
        }

        call = service.getList("suggest", page);
        call.enqueue(this);
    }
}

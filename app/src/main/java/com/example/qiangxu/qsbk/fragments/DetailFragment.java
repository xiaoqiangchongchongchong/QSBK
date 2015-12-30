package com.example.qiangxu.qsbk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qiangxu.qsbk.R;
import com.example.qiangxu.qsbk.adapters.CommonAdapter;
import com.example.qiangxu.qsbk.domain.Common;
import com.example.qiangxu.qsbk.domain.LeftMenuTitle;
import com.example.qiangxu.qsbk.interfaces.CommonService;
import com.example.qiangxu.qsbk.views.MyListView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements Callback<Common> {

    private Call<com.example.qiangxu.qsbk.domain.Common> call;
    private CommonAdapter adapter;

    public DetailFragment() {
        // Required empty public constructor
    }


    public static DetailFragment newInstance(LeftMenuTitle leftMenuTitle, long suggestId){
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        args.putString("text", leftMenuTitle.getTitle());
        args.putInt("flag", leftMenuTitle.getFlag());
        args.putLong("suggestId", suggestId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret =  inflater.inflate(R.layout.fragment_detail, container, false);
        return ret;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyListView myListView = (MyListView) view.findViewById(R.id.list_pinglun);
        int flag = getArguments().getInt("flag");

        if(flag == 1){
            adapter = new CommonAdapter(getActivity());
            myListView.setAdapter(adapter);
            Retrofit build = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com").addConverterFactory(GsonConverterFactory.create()).build();
            CommonService service = build.create(CommonService.class);
            long suggesId = getArguments().getLong("suggestId");
            call = service.getList(suggesId, "comments", 1);
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Response<Common> response, Retrofit retrofit) {
        adapter.addAll(response.body().getItems());
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
    }
}

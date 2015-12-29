package com.example.qiangxu.qsbk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiangxu.qsbk.R;
import com.example.qiangxu.qsbk.adapters.GongXiangAdapter;
import com.example.qiangxu.qsbk.domain.LeftMenuTitle;
import com.example.qiangxu.qsbk.domain.Suggest;
import com.example.qiangxu.qsbk.interfaces.QsbkService;
import com.squareup.okhttp.Response;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements Callback<com.example.qiangxu.qsbk.domain.Suggest> {

    private Call<com.example.qiangxu.qsbk.domain.Suggest> call;
    private GongXiangAdapter adapter;
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
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = (ListView) view.findViewById(R.id.common_list);

        String str = getArguments().getString("text");
        int flag = getArguments().getInt("flag");
        if(flag == 1){
            adapter = new GongXiangAdapter(getActivity());
            listView.setAdapter(adapter);
            Retrofit build = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com").addConverterFactory(GsonConverterFactory.create()).build();
            QsbkService service = build.create(QsbkService.class);
            call = service.getList("suggest", 1);
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(retrofit.Response<com.example.qiangxu.qsbk.domain.Suggest> response, Retrofit retrofit) {
        adapter.addAll(response.body().getItems());
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
    }
}

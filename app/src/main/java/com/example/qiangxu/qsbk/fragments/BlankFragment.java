package com.example.qiangxu.qsbk.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiangxu.qsbk.R;
import com.example.qiangxu.qsbk.TextActivity;
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
public class BlankFragment extends Fragment implements Callback<Suggest> {

    private Call<com.example.qiangxu.qsbk.domain.Suggest> call;
    private GongXiangAdapter adapter;
    private ListView listView;
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

        listView = (ListView) ret.findViewById(R.id.common_list);

        //final FrameLayout blankFrame = (FrameLayout) ret.findViewById(R.id.blank_fragment);

//        blankFrame.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            //当layout加载完成后执行此方法
//            @Override
//            public void onGlobalLayout() {
//                //这个方法会重复执行，为了防止重复执行，当执行第一次结束后，删除监听
//                blankFrame.getViewTreeObserver().removeGlobalOnLayoutListener(this);

//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(getContext(), TextActivity.class);
//                        startActivity(intent);
//                    }
//                });
//            }
//        });

        //listView.setOnItemClickListener(this);



        return ret;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //listView = (ListView) view.findViewById(R.id.common_list);
        int flag = getArguments().getInt("flag");
        if(flag == 1){
            adapter = new GongXiangAdapter(getActivity());
            listView.setAdapter(adapter);
            Retrofit build = new Retrofit.Builder().baseUrl("http://m2.qiushibaike.com").addConverterFactory(GsonConverterFactory.create()).build();
            QsbkService service = build.create(QsbkService.class);
            call = service.getList("suggest", 1);
            call.enqueue(this);

            //listView.setOnItemClickListener(this);

        }
    }

    @Override
    public void onResponse(retrofit.Response<com.example.qiangxu.qsbk.domain.Suggest> response, Retrofit retrofit) {
        adapter.addAll(response.body().getItems());

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Intent intent = new Intent(getContext(), TextActivity.class);
//            startActivity(intent);
//        }
 //   });


    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(getContext(), TextActivity.class);
//        startActivity(intent);
//    }
}

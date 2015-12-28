package com.example.qiangxu.qsbk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qiangxu.qsbk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    private TextView text;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String text){
        Bundle args = new Bundle();

        BlankFragment fragment = new BlankFragment();
        args.putString("text", text);
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
        text = (TextView) view.findViewById(R.id.pager_text);

        String str = getArguments().getString("text");

        text.setText(str);


    }
}

package com.example.qiangxu.qsbk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        Intent intent = getIntent();
//       //Bundle bundle = intent.getBundleExtra("item");
//        Bundle bundle = intent.getExtras();
//        Suggest.ItemsEntity item = (Suggest.ItemsEntity) bundle.getSerializable("item");

        Toast.makeText(this, "跳转", Toast.LENGTH_SHORT).show();

    }
}

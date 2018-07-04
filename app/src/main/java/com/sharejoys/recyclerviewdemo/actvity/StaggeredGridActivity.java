package com.sharejoys.recyclerviewdemo.actvity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.sharejoys.recyclerviewdemo.R;
import com.sharejoys.recyclerviewdemo.adapter.MyStaggeredRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> list;
    private MyStaggeredRecyclerAdapter myStaggeredRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("item" + i);
        }

        //通过findViewById拿到RecyclerView实例
        mRecyclerView = findViewById(R.id.recyclerView);
        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        //初始化适配器
        myStaggeredRecyclerAdapter = new MyStaggeredRecyclerAdapter(list);
        //设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerView.setAdapter(myStaggeredRecyclerAdapter);
    }
}

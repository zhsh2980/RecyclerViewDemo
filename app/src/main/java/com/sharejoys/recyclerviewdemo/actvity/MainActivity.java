package com.sharejoys.recyclerviewdemo.actvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sharejoys.recyclerviewdemo.R;
import com.sharejoys.recyclerviewdemo.view.DividerItemDecoration;
import com.sharejoys.recyclerviewdemo.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewIn;
    private MyRecyclerViewAdapter mAdapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first init
        //第二次正确提交

        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("item" + i);
        }

        //通过findViewById拿到RecyclerView实例
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerViewIn = findViewById(R.id.recyclerView_in);
        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mRecyclerViewIn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewIn.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

        //初始化适配器
        mAdapter = new MyRecyclerViewAdapter(list);
        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                Toast.makeText(MainActivity.this, "您点击了：  " + data, Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.setOnItemLongClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position, String data) {
                Toast.makeText(MainActivity.this, "您长按点击了：  " + data, Toast.LENGTH_SHORT).show();
            }
        });
        //设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerView.setAdapter(mAdapter);
        //设置添加或删除item时的动画，这里使用默认动画
        mRecyclerViewIn.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerViewIn.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {//增加一条数据
            mAdapter.addItem(2, "我是增加的Item");
        } else if (item.getItemId() == R.id.action_delete) {//删除一条数据
            mAdapter.deleteItem(2);
        } else if (item.getItemId() == R.id.action_grid) {
            startActivity(new Intent(MainActivity.this, GridLayoutActivity.class));
        } else if (item.getItemId() == R.id.action_stagger) {
            startActivity(new Intent(MainActivity.this, StaggeredGridActivity.class));
        }
        return true;
    }

}

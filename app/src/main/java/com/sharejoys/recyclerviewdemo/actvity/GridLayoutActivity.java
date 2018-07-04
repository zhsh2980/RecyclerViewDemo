package com.sharejoys.recyclerviewdemo.actvity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sharejoys.recyclerviewdemo.R;
import com.sharejoys.recyclerviewdemo.view.DividerGridViewItemDecoration;
import com.sharejoys.recyclerviewdemo.adapter.MyGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyGridAdapter mAdapter;
    private List<String> list;

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
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerGridViewItemDecoration(this));
        //初始化适配器
        mAdapter = new MyGridAdapter(list);
        //设置点击长按监听
        mAdapter.setOnItemClickListener(new MyGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                Toast.makeText(GridLayoutActivity.this, "您点击了：  " + data, Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.setOnItemLongClickListener(new MyGridAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position, String data) {
                Toast.makeText(GridLayoutActivity.this, "您长按点击了：  " + data, Toast.LENGTH_SHORT).show();
            }
        });
        //设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grid, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {//增加一条数据
            mAdapter.addItem(2, "我是增加的Item");
        } else if (item.getItemId() == R.id.action_delete) {//删除一条数据
            mAdapter.deleteItem(2);
        }
        return true;
    }

}

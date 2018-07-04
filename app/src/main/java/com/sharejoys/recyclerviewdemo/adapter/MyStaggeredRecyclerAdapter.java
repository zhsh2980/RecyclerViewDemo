package com.sharejoys.recyclerviewdemo.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2018/1/10
 *
 * @author 青青-子衿
 * @since 1.0
 */

public class MyStaggeredRecyclerAdapter extends RecyclerView.Adapter<MyStaggeredRecyclerAdapter.MyStaggeredRecyclerHolder> {
    private List<String> list;
    private List<Integer> heights;

    public MyStaggeredRecyclerAdapter(List<String> list) {
        this.list = list;
        heights = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            heights.add((int) (200 + Math.random() * 50));
        }
    }

    @Override
    public MyStaggeredRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        MyStaggeredRecyclerHolder staggeredRecyclerHolder = new MyStaggeredRecyclerHolder(view);
        return staggeredRecyclerHolder;
    }

    @Override
    public void onBindViewHolder(MyStaggeredRecyclerHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.textView.getLayoutParams();
        layoutParams.height = heights.get(holder.getAdapterPosition());
        holder.textView.setBackgroundColor(Color.rgb(100, (int) (Math.random() * 255), (int) (Math.random() * 255)));
        holder.textView.setLayoutParams(layoutParams);
        holder.textView.setText(list.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyStaggeredRecyclerHolder extends RecyclerView.ViewHolder {
        @BindView(android.R.id.text1)
        TextView textView;

        public MyStaggeredRecyclerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

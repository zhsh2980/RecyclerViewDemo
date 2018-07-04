package com.sharejoys.recyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharejoys.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by 青青-子衿 on 2018/1/15.
 */


public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.ViewHolder> {
    private List<String> list;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    /**
     * 插入一条数据
     *
     * @param index 下标
     * @param s     数据
     */
    public void addItem(int index, String s) {
        list.add(index, s);
        notifyItemInserted(index);
    }

    /**
     * 删除一条数据
     *
     * @param index 下标
     */
    public void deleteItem(int index) {
        list.remove(index);
        notifyItemRemoved(index);
    }


    /**
     * 设置点击事件
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置长按点击事件
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public MyGridAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public MyGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        MyGridAdapter.ViewHolder viewHolder = new MyGridAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyGridAdapter.ViewHolder holder, int position) {
        holder.mText.setText(list.get(position));
        int adapterPosition = holder.getAdapterPosition();
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new MyOnClickListener(position, list.get(adapterPosition)));
        }
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new MyOnLongClickListener(position, list.get(adapterPosition)));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mText;

        ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.item_tx);
        }
    }

    private class MyOnLongClickListener implements View.OnLongClickListener {
        private int position;
        private String data;

        public MyOnLongClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClickListener.onItemLongClick(v, position, data);
            return true;
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int position;
        private String data;

        public MyOnClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, position, data);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, String data);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position, String data);
    }

}
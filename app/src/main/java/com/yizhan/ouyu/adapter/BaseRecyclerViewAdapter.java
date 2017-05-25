package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/18.
 */

public abstract  class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static  int TYPE_NORMAL=0;
    private final static  int TYPE_HEADER=1;
    private View mHeaderView;
    public List<T> data=new ArrayList<>();

    private OnItemClickListener onItemClickListener;
    public Context mContext;
    public int headViewNum=0;

    public BaseRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        headViewNum=1;
        notifyItemInserted(0);
    }

    public void addDataList(List<T> dataList){
        this.data.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clear(){
        this.data.clear();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView!=null && viewType==TYPE_HEADER){
            return new HeadViewHolder(mHeaderView);
        }
        final RecyclerView.ViewHolder holder=commonOnCreateViewHolder(parent,viewType);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition() - headViewNum);
                }
            }
        });
        return holder;
    }

    public  abstract RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType);
    public  abstract void  commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mHeaderView!=null &&position==0){
            return;
        }
        commonOnBindViewHolder(holder,position-headViewNum);
    }

    @Override
    public int getItemCount() {
       if(mHeaderView==null){
           return data.size();
       }else {
           return data.size()+headViewNum;
       }
    }

    public T getItem(int position){
        return data.get(position);
    }



    @Override
    public int getItemViewType(int position) {
        if(mHeaderView!=null&&position==0){
            return TYPE_HEADER;
        }else {
            return TYPE_NORMAL;
        }
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder{

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }
}

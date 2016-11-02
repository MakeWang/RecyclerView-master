package com.wy.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * User : wy
 * Date : 2016/10/31
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolderView>{

    protected Context mContext;
    protected List<T> mList;
    protected OnItemOnClickListener<T> itemOnClickListener;

    public BaseRecyclerViewAdapter(Context mContext, List<T> mList){
        this.mContext = mContext;
        if(mList != null){
            this.mList = mList;
        }
    }

    @Override
    public BaseRecyclerHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        return showOnCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolderView holder, int position) {
        showOnBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 添加一个集合
     * @param data
     */
    public void additemList(List<T> data){
        mList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加一个对象
     * @param data
     */
    public void addItemObject(T data){
        mList.add(data);
        notifyItemInserted(mList.size()-1);
    }

    /**
     * 添加一个对象，插入位置
     * @param postion
     * @param data
     */
    public void addItemObject(int postion,T data){
        mList.add(postion,data);
        notifyItemInserted(postion);
    }

    /**
     * 根据下标删除
     * @param postion
     */
    public void removeItem(int postion){
        mList.remove(postion);
        notifyItemRemoved(postion);
    }

    /**
     * 删除一个对象
     * @param data
     */
    public void removeItem(T data){
        if(data != null){
            mList.remove(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除所有数据
     */
    public void remoceAll(){
        mList.clear();
        notifyDataSetChanged();
    }

    /**
     * 修改数据中某一个条数据
     * @param postion
     * @param data
     */
    public void updateItem(int postion,T data){
        mList.set(postion,data);
        notifyItemChanged(postion);
    }

    public void setOnItemClickListener(OnItemOnClickListener<T> itemListener){
        this.itemOnClickListener = itemListener;
    }

    public interface OnItemOnClickListener<T>{
        //点击item
        void onItemClick(View view, int postion, T data);
        //长按点击事件
        void onLongItemClick(View view,int postion,T data);
    }

    protected abstract void showOnBindViewHolder(BaseRecyclerHolderView holder, int position);

    protected abstract BaseRecyclerHolderView showOnCreateViewHolder(ViewGroup parent, int viewType);
}

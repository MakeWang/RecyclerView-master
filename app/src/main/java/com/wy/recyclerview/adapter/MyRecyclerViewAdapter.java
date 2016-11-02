package com.wy.recyclerview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wy.recyclerview.R;
import com.wy.recyclerview.entity.UserInfo;
import com.wy.recyclerview.lintener.ItemTouchMoveListener;
import com.wy.recyclerview.lintener.StartDragListener;

import java.util.Collections;
import java.util.List;

/**
 * User : wy
 * Date : 2016/10/31
 */
public class MyRecyclerViewAdapter extends BaseRecyclerViewAdapter<UserInfo>{

    private StartDragListener startDragListener;

    public MyRecyclerViewAdapter(Context mContext, List<UserInfo> mList) {
        super(mContext, mList);
        this.startDragListener = (StartDragListener) mContext;
    }

    @Override
    protected void showOnBindViewHolder(final BaseRecyclerHolderView holder, final int position) {
        HolderView holderView = (HolderView) holder;
        holderView.txtName.setText(mList.get(position).getName());
        holderView.iv_img.setImageResource(mList.get(position).getImg());
        /*if(itemOnClickListener != null){
            holderView.txtName.setOnClickListener(new OnClicListener(position));
        }
        if(itemOnClickListener != null){
            holderView.txtName.setOnLongClickListener(new OnLongClicListener(position));
        }*/
        holderView.iv_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    //传递触摸情况给谁
                    startDragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    protected BaseRecyclerHolderView showOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.recyclerview_item,null);
        BaseRecyclerHolderView holderView = new HolderView(view);
        return holderView;
    }

    public boolean onItemMove(int fromPosition, int toPosition) {
        if(toPosition < mList.size()){
            Collections.swap(mList,fromPosition,toPosition);
        }

        super.notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    public boolean onItemRemove(int postion) {
        mList.remove(postion);
        super.notifyItemRemoved(postion);
        return true;
    }

    public class HolderView extends BaseRecyclerHolderView{

        private ImageView iv_img;
        private TextView txtName;
        public HolderView(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_logo);
            txtName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    /**
     * 为了解决条码错乱的问题
     */
    class OnClicListener implements View.OnClickListener{
        int posion;
        public OnClicListener(int pos){
            this.posion = pos;
        }

        @Override
        public void onClick(View v) {
            itemOnClickListener.onItemClick(v,posion,mList.get(posion));
        }
    }


    class OnLongClicListener implements View.OnLongClickListener{

        int posion;
        public OnLongClicListener(int pos){
            this.posion = pos;
        }

        @Override
        public boolean onLongClick(View v) {
            itemOnClickListener.onLongItemClick(v,posion,mList.get(posion));
            return false;
        }
    }

}

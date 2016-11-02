package com.wy.recyclerview.view;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.wy.recyclerview.R;
import com.wy.recyclerview.lintener.ItemTouchMoveListener;

/**
 * User : wy
 * Date : 2016/10/31
 * 动画处理回调类
 */
public class MyItemToucHeplerCallback extends ItemTouchHelper.Callback {

    private ItemTouchMoveListener itemTouchMoveListener;

    public MyItemToucHeplerCallback(ItemTouchMoveListener itemTouchMoveListener){
        this.itemTouchMoveListener = itemTouchMoveListener;
    }

    //回调监听先调用，用来判断方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //方向
        int up = ItemTouchHelper.UP;
        int down = ItemTouchHelper.DOWN;
        int left = ItemTouchHelper.LEFT;
        int right = ItemTouchHelper.RIGHT;

        //我要监听那个方向
        // actionState：拖拽
        // directions：左右滑动
        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;//我要监听上下拖拽事件
        int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;//我要监听左右滑动事件，如果不监听，可设置为0
        int flag = makeMovementFlags(dragFlags,swipeFlags);
        return flag;
    }

    //允许长按拖动效果
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    //当拖拽时回调此方法
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(viewHolder.getItemViewType() != target.getItemViewType()){
            return false;
        }
        boolean fla = itemTouchMoveListener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return fla;
    }


    //当左右滑动时回调此方法
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchMoveListener.onItemRemove(viewHolder.getAdapterPosition());



    }

    //当我触摸时调用（用来设置背景颜色）
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //判断被选择的状态
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder.itemView.setBackgroundResource(R.color.gray);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    //还原
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundResource(R.color.wiler);

        viewHolder.itemView.setAlpha(1);//透明度变化
        viewHolder.itemView.setScaleX(1);//缩放动画X
        viewHolder.itemView.setScaleY(1);//缩放动画Y
        super.clearView(recyclerView, viewHolder);
    }

    //动画
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        float alpha = 1 - Math.abs(dX)/viewHolder.itemView.getWidth();
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            //dx:水平方向的增量（负：往左，正：往右）
            viewHolder.itemView.setAlpha(alpha);//透明度变化
            viewHolder.itemView.setScaleX(alpha);//缩放动画X
            viewHolder.itemView.setScaleY(alpha);//缩放动画Y
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}

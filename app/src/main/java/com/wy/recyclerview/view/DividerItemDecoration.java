package com.wy.recyclerview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * User : wy
 * Date : 2016/10/31
 * 添加RecyclerView下划线
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    //样式图片
    private Drawable mDrawable;

    //系统样式
    private int[] arrt = new int[]{
            android.R.attr.listDivider
    };
    //类型（水平或垂直  0是水平，1是垂直）
    private int mOrientation;


    public DividerItemDecoration(Context mContext, int orientation) {
        //类型集合
        TypedArray typedArray = mContext.obtainStyledAttributes(arrt);
        mDrawable = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("参数必须是水平或垂直！");
        }
        this.mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        //水平
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            setHorizontal(c, parent);
        } else {//垂直
            setVsertical(c, parent);
        }
    }

    public void setVsertical(Canvas c, RecyclerView parent) {

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        //获取儿子的个数
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            //获取每一个儿子
            View child = parent.getChildAt(i);
            //获取线性布局管理器
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);

        }

    }

    public void setHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + mDrawable.getMinimumWidth();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //水平
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.set(0, 0, mDrawable.getIntrinsicHeight(), 0);
        } else {//垂直
            outRect.set(0, 0, 0, mDrawable.getIntrinsicWidth());
        }

    }
}
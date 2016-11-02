package com.wy.recyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


/**
 * User : wy
 * Date : 2016/10/31
 */
public class WrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViewInfos = new ArrayList<>();
    private ArrayList<View> mFooterViewInfos = new ArrayList<>();
    private Adapter mAdapter;


    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void addHeaderView(View view) {
        mHeaderViewInfos.add(view);

        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderRecyclerAdapter)) {
                mAdapter = new HeaderRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }

    }

    public void addFooterView(View view) {
        mFooterViewInfos.add(view);

        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderRecyclerAdapter)) {
                mAdapter = new HeaderRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }

    }

    @Override
    public void setAdapter(Adapter adapter) {

        if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0) {
            mAdapter = new HeaderRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }

        super.setAdapter(mAdapter);
    }

    public Adapter getAdapter(){
        return mAdapter;
    }

}

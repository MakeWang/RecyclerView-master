package com.wy.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.wy.recyclerview.adapter.MyRecyclerViewAdapter;
import com.wy.recyclerview.entity.DataUtils;
import com.wy.recyclerview.entity.UserInfo;
import com.wy.recyclerview.lintener.ItemTouchMoveListener;
import com.wy.recyclerview.lintener.StartDragListener;
import com.wy.recyclerview.view.DividerItemDecoration;
import com.wy.recyclerview.view.MyItemToucHeplerCallback;
import com.wy.recyclerview.view.WrapRecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StartDragListener,ItemTouchMoveListener {

    private WrapRecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<UserInfo> mList = DataUtils.init();

        mRecyclerView = (WrapRecyclerView)findViewById(R.id.recyclerView);

        /*****为RecyclerView添加header和footer******/
        View header = View.inflate(this,R.layout.header_view,null);
        //mRecyclerView.addHeaderView(header);
        View footer = View.inflate(this,R.layout.header_view,null);
        //mRecyclerView.addFooterView(footer);

        mAdapter = new MyRecyclerViewAdapter(this,mList);
        //布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        //添加item执行动画(默认系统自带的动画)
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义线条
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        //添加条目动画交互
        //条目触摸帮助类
        ItemTouchHelper.Callback callback = new MyItemToucHeplerCallback(this);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        itemTouchHelper.startDrag(holder);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        mAdapter.onItemMove(fromPosition,toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int postion) {
        mAdapter.onItemRemove(postion);
        return true;
    }
}

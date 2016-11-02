package com.wy.recyclerview.lintener;

/**
 * User : wy
 * Date : 2016/10/31
 *
 */
public interface ItemTouchMoveListener {

    /**
     * 拖拽时回调
     * @param fromPosition 从什么位置拖
     * @param toPosition 到拖到什么位置
     * @return
     */
    boolean onItemMove(int fromPosition, int toPosition);

    /**
     *条目左右滑动移除
     * @param postion 移动条目
     * @return
     */
    boolean onItemRemove(int postion);

}

package com.wy.recyclerview.entity;

import com.wy.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * User : wy
 * Date : 2016/10/31
 * 数据初始化类
 */
public class DataUtils {
    public static List<UserInfo> init(){
        List<UserInfo> list = new ArrayList<UserInfo>();

        for (int i = 1; i <= 30;i++){
            list.add(new UserInfo(R.mipmap.logo, "这是第"+i+"行！"));
        }
        return list;
    }
}

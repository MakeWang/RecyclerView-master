package com.wy.recyclerview.entity;

/**
 * User : wy
 * Date : 2016/10/31
 */
public class UserInfo {
    private int img;
    private String name;

    public UserInfo(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }
}

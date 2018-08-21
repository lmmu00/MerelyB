package com.merelyb.bean.face;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-21 15:40
 * @Description: 矩形
 */
public class Rectangle {
    //左上
    private Coordinate left_top;
    //左下
    private Coordinate left_bottom;
    //右上
    private Coordinate right_top;
    //右下
    private Coordinate right_bottom;

    public Coordinate getLeft_top() {
        return left_top;
    }

    public void setLeft_top(Coordinate left_top) {
        this.left_top = left_top;
    }

    public Coordinate getLeft_bottom() {
        return left_bottom;
    }

    public void setLeft_bottom(Coordinate left_bottom) {
        this.left_bottom = left_bottom;
    }

    public Coordinate getRight_top() {
        return right_top;
    }

    public void setRight_top(Coordinate right_top) {
        this.right_top = right_top;
    }

    public Coordinate getRight_bottom() {
        return right_bottom;
    }

    public void setRight_bottom(Coordinate right_bottom) {
        this.right_bottom = right_bottom;
    }
}

package edu.gatech.spacetraders.entity;
import android.support.annotation.NonNull;

import java.io.Serializable;
public class Point2 implements Serializable{
    final int x;
    final int y;
    public Point2(int x, int y){
        this.x = x;
        this.y = y;
    }

    @NonNull
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

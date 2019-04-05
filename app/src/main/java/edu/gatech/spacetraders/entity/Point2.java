package edu.gatech.spacetraders.entity;
import java.io.Serializable;
public class Point2 implements Serializable{
    int x;
    int y;
    public Point2(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

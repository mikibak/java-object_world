package com.company.World;

public class Point {
    public Point() {
        x = 1;
        y = 1;
    }

    private int x;
    private int y;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Boolean equals(Point other) {
        if(this.x == other.getX() && this.y == other.getY()) {
            return true;
        } else return false;
    }


}

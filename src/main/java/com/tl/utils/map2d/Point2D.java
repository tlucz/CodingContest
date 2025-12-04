package com.tl.utils.map2d;

public record Point2D(int row, int column) {

    public static Point2D RIGHT = new Point2D(0,1);
    public static Point2D LEFT = new Point2D(0,-1);
    public static Point2D UP = new Point2D(-1,0);
    public static Point2D DOWN = new Point2D(1,0);

    public  Point2D add(int rows, int cols) {
        return new Point2D(this.row + rows, this.column + cols);
    }

    public  Point2D add(Point2D point) {
        return add(point.row(), point.column());
    }
}

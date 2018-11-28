package model;

public class Point {
    private double x;
    private double y;

    /**
     * Point constructor takes x and y coords
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * gets the x coord as a double value
     */
    double getX() {
        return x;
    }

    /**
     * gets the y coord as a double value
     */
     double getY() {
        return y;
    }
}
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line implements IShape {

    private Color color;
    private Point point1;
    private Point point2;

    public Line(Color color, Point point1, Point point2) {
        this.color = color;
        this.point1 = point1;
        this.point2 = point2;
    }

    @Override
    public Color getColor(Color color) {
        return null;
    }

    @Override
    public Point getPointP1(Point point1) {
        return null;
    }

    @Override
    public Point getPointP2(Point point2) {
        return null;
    }

    @Override
    public void viewShape(GraphicsContext graphics) {

    }
}
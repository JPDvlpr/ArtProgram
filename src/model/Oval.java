package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval implements IShape {
    private Color color;
    private Point point1;
    private Point point2;

    public Oval(Color color, Point point1, Point point2) {
        this.color = color;
        this.point1 = point1;
        this.point2 = point2;
    }

    @Override
    public Color getColor(Color color) {
        return color;
    }

    @Override
    public Point getPointP1(Point point1) {
        return point1;
    }

    @Override
    public Point getPointP2(Point point2) {
        return point2;
    }

    @Override
    public void viewShape(GraphicsContext graphics) {
        double x = point1.getX();
        double y = point1.getY();
        double width = point2.getX() - x;
        double height = point2.getY() - y;

        graphics.setStroke(color);

        graphics.strokeOval(x,y,width,height);
    }
}
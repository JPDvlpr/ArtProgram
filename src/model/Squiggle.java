package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Squiggle implements IShape {

    private Color color;
    private Point point1;
    private Point point2;

    public Squiggle(Color color, Point point1, Point point2) {
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
        double x2 = point2.getX();
        double y2 = point2.getY();

        graphics.setStroke(color);

        graphics.strokeLine(x,y,x2,y2);
    }
}
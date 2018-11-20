package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class Oval implements IShape {
    private Point point1;
    private Point point2;
    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;
    private boolean filled;

    public Oval(Point point1, Point point2, Color fillColor,
                Color strokeColor, double strokeWidth, boolean filled) {
        this.point1 = point1;
        this.point2 = point2;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.filled = filled;
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

        graphics.setFill(fillColor);
        graphics.setStroke(strokeColor);
        graphics.setLineWidth(strokeWidth);
        graphics.strokeOval(x, y, width, height);
        if (filled){
            graphics.fillOval(x, y, width, height);
        }
    }
}
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements IShape {
    private Point point1;
    private Point point2;
//    private double width;
//    private double height;
    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;
    private boolean filled;

    public Rectangle(Point point1, Point point2, Color fillColor, Color strokeColor,
                     double strokeWidth, boolean filled) {
        this.point1 = point1;
        this.point2 = point2;
//        this.width = width;
//        this.height = height;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.filled = filled;
    }

//    public double getWidth() {
//        return width;
//    }
//
//    public double getHeight() {
//        return height;
//    }

    public void viewShape(GraphicsContext graphics) {
        double x = point1.getX();
        double y = point1.getY();
        double width = point2.getX() - x;
        double height = point2.getY() - y;

        graphics.setFill(fillColor);
        graphics.setStroke(strokeColor);
        graphics.setLineWidth(strokeWidth);
        if (filled) {
            graphics.fillRect(x, y, width, height);
        }
        graphics.strokeRect(x, y, width, height);
    }
}
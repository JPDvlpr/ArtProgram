package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Rectangle implements IShape {
    private Color color;
    private double height;
    private double width;

    public Rectangle(Color color, double height, double width) {
        this.color = color;
        this.height = height;
        this.width = width;
    }

    @Override
    public Color getColor(Color color) {
        return color;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void viewShape(GraphicsContext graphics) {
//        double x = point1.getX();
//        double y = point1.getY();
//        double width = point2.getX() - x;
//        double height = point2.getY() - y;
//
//        graphics.setStroke(color);
//
//        graphics.strokeRect(x,y,width,height);
    }
}
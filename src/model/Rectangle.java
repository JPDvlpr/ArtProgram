package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Rectangle is one of four shapes used in this app
 */
public class Rectangle implements IShapeFacade {
    private Point point1;
    private Point point2;
    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;
    private boolean filled;

    /**
     * creating the Rectangle constructor and setting the
     * global vars to be the constructor vars
     */
    public Rectangle(Point point1, Point point2, Color fillColor, Color strokeColor,
                     double strokeWidth, boolean filled) {
        this.point1 = point1;
        this.point2 = point2;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.filled = filled;
    }

    /**
     * views the Rectangle shape using a width and height
     */
    public void viewShape(GraphicsContext graphics) {
        double x = point1.getX();
        double y = point1.getY();
        double width = point2.getX() - x;
        double height = point2.getY() - y;

        graphics.setFill(fillColor);
        graphics.setStroke(strokeColor);
        graphics.setLineWidth(strokeWidth);
        graphics.strokeRect(x, y, width, height);

        if (filled) {
            graphics.fillRect(x, y, width, height);
        }
    }
}
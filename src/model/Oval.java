package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Oval is one of four shapes used in this app
 */
public class Oval implements IShapeFacade {
    private Point point1;
    private Point point2;
    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;
    private boolean filled;

    /**
     * creating the Oval constructor and setting the
     * global vars to be the constructor vars
     */
    public Oval(Point point1, Point point2, Color fillColor,
                Color strokeColor, double strokeWidth, boolean filled) {
        this.point1 = point1;
        this.point2 = point2;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.filled = filled;
    }

    /**
     * views the Oval shape using a set of points,
     * a width, and a height
     */
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

        if (filled) {
            graphics.fillOval(x, y, width, height);
        }
    }
}
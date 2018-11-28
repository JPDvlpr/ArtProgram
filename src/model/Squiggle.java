package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Squiggle is one of four shapes used in this app
 */
public class Squiggle implements IShape {

    private Color fillColor;
    private Color strokeColor;
    private Point[] point;
    private double strokeWidth;
    private boolean filled;

    /**
     * creating the Squiggle constructor and setting the
     * global vars to be the constructor vars
     */
    public Squiggle(Point[] point, Color fillColor,
                    Color strokeColor, double strokeWidth, boolean filled) {
        this.point = point;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.filled = filled;
    }

    /**
     * views the Squiggle shape using an array of coordinates
     */
    @Override
    public void viewShape(GraphicsContext graphics) {
        double[] xCoords = new double[point.length];
        double[] yCoords = new double[point.length];

        graphics.setFill(fillColor);
        graphics.setStroke(strokeColor);
        graphics.setLineWidth(strokeWidth);

        for (int i = 0; i < point.length; i++) {
            xCoords[i] += point[i].getX();
            yCoords[i] += point[i].getY();
        }

        graphics.strokePolyline(xCoords, yCoords, point.length);

        if (filled) {
            graphics.fillPolygon(xCoords, yCoords, point.length);
        }
    }
}
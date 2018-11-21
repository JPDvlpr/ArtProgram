package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Squiggle implements IShape {

    private Color fillColor;
    private Color strokeColor;
    private Point[] point;
    private double strokeWidth;
    private boolean filled;

    public Squiggle(Point[] point, Color fillColor,
                    Color strokeColor, double strokeWidth, boolean filled) {
        this.point = point;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.filled = filled;
    }

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
            System.out.println("filled");
            graphics.fillPolygon(xCoords, yCoords, point.length);
        }
    }
}
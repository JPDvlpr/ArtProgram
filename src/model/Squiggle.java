package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Squiggle implements IShape {

    private Color fillColor;
    private Color strokeColor;
    private Point point1;
    private Point point2;
    private double strokeWidth;
    private boolean filled;

    public Squiggle(Point point1, Point point2, Color fillColor,
                    Color strokeColor, double strokeWidth, boolean filled) {
        this.point1 = point1;
        this.point2 = point2;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.filled = filled;
    }

    @Override
    public void viewShape(GraphicsContext graphics) {
        double x = point1.getX();
        double y = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();

        double xCoords[] = {};
        double yCoords[] = {};
        for (int i = 0; i < xCoords.length; i++) {
            xCoords[i] += x;
            yCoords[i] += y;
        }

        graphics.setFill(fillColor);
        graphics.setStroke(strokeColor);
        graphics.setLineWidth(strokeWidth);

        graphics.strokePolyline(xCoords, yCoords, 40);
        if (filled) {
            System.out.println("filled");
//            graphics.fillPolygon(x, y, width, height);
        }
    }
}
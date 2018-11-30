package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * facade design class to add shapes
 */
class GraphicsFacade {

    /**
     * adds a new shape that takes in params which is then
     * passed into it's shape class within the model layer
     */
    static void viewLine(GraphicsContext graphics, Point point1, Point point2,
                         Color strokeColor, double strokeWidth) {
        double x = point1.getX();
        double y = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();

        graphics.setStroke(strokeColor);
        graphics.setLineWidth(strokeWidth);
        graphics.strokeLine(x, y, x2, y2);
    }

    /**
     * adds a new shape that takes in params which is then
     * passed into it's shape class within the model layer
     */
    static void viewOval(GraphicsContext graphics, Point point1, Point point2, Color fillColor,
                         Color strokeColor, double strokeWidth, boolean filled) {
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

    /**
     * adds a new shape that takes in params which is then
     * passed into it's shape class within the model layer
     */
    static void viewRect(GraphicsContext graphics, Point point1, Point point2, Color fillColor,
                         Color strokeColor, double strokeWidth, boolean filled) {
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

    /**
     * adds a new shape that takes in params which is then
     * passed into it's shape class within the model layer
     */
    static void viewSquiggle(GraphicsContext graphics, Point[] point, Color fillColor,
                             Color strokeColor, double strokeWidth, boolean filled) {
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
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Line is one of four shapes used in this app
 */
public class Line implements IShape {
    private Color strokeColor;
    private Point point1;
    private Point point2;
    private double strokeWidth;

    /**
     * creating the Line constructor and setting the
     * global vars to be the constructor vars
     */
    Line(Point point1, Point point2, Color strokeColor, double strokeWidth) {
        this.point1 = point1;
        this.point2 = point2;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    /**
     * The line is created by 4 points
     * start x, start y, end x, end y
     */
    @Override
    public void viewShape(GraphicsContext graphics) {
        GraphicsFacade.viewLine(graphics, point1, point2, strokeColor, strokeWidth);

    }
}
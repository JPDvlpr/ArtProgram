package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Squiggle is one of four shapes used in this app
 */
public class Squiggle implements IShapeFacade {

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
        GraphicsFacade.viewSquiggle(graphics, point, fillColor, strokeColor, strokeWidth, filled);

    }
}
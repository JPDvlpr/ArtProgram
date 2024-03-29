package model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Factory design pattern implemented to retrieve the shapes
 */
public class ShapeFactory {
    /**
     * getsShape returns an IShape and is static
     * so it can be called directly in other classes
     */
    public static IShape getShape(String text, ArrayList<Point> pointList, Color fillColor,
                                  Color strokeColor, double strokeWidth, boolean filled){

        Point[] point = new Point[pointList.size()];
        IShape shape = null;

        for (int i = 0; i < pointList.size(); i++) {
            point[i] = pointList.get(i);
        }

        switch (text) {
            case "Oval":
                shape = new Oval(point[0], point[point.length - 1], fillColor, strokeColor, strokeWidth, filled);
                break;
            case "Line":
                shape = new Line(point[0], point[point.length - 1], strokeColor, strokeWidth);
                break;
            case "Rectangle":
                shape = new Rectangle(point[0], point[point.length - 1], fillColor, strokeColor, strokeWidth, filled);
                break;
            case "Squiggle":
                shape = new Squiggle(point, fillColor, strokeColor, strokeWidth, filled);
                break;
            default:
                break;
        }
        return shape;
    }
}
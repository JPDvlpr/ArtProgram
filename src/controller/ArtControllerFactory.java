package controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.*;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Controller that does the work from the view layer
 * to get passed to the model layer
 */
public class ArtControllerFactory {

    private Stack<IShapeFacade> shapeList = new Stack<>();
    private Stack<IShapeFacade> tempShapeList = new Stack<>();

    /**
     * Adds a new shape to a stack of data type IShapeFacade
     */
    public void handleAddShape(String text, ArrayList<Point> pointList, Color fillColor,
                               Color strokeColor, double strokeWidth, boolean filled) {
        tempShapeList.clear();
        Point[] point = new Point[pointList.size()];

        for (int i = 0; i < pointList.size(); i++) {
            point[i] = pointList.get(i);
        }
        switch (text) {
            case "Oval":
                IShapeFacade oval = new Oval(point[0], point[point.length - 1], fillColor, strokeColor, strokeWidth, filled);
                shapeList.add(oval);
                break;
            case "Line":
                IShapeFacade line = new Line(point[0], point[point.length - 1], strokeColor, strokeWidth);
                shapeList.add(line);
                break;
            case "Rectangle":
                IShapeFacade rectangle = new Rectangle(point[0], point[point.length - 1], fillColor, strokeColor, strokeWidth, filled);
                shapeList.add(rectangle);
                break;
            case "Squiggle":
                IShapeFacade squiggle = new Squiggle(point, fillColor, strokeColor, strokeWidth, filled);
                shapeList.add(squiggle);
                break;
            default:
                break;
        }
    }

    /**
     * displays all shapes to the graphics
     */
    public void viewShapes(GraphicsContext graphics) {

        for (IShapeFacade shape : shapeList) {
            shape.viewShape(graphics);
        }
    }

    /**
     * removes the last shape from the stack
     */
    public void removeLastShape() {
        shapeList.remove(shapeList.size() - 1);
    }

    /**
     * undos the last shape that was added to the stack
     */
    public void undo(GraphicsContext graphics) {
        if (shapeList.isEmpty()) {
            System.out.println("Stack is empty.");

        } else {
            tempShapeList.push(shapeList.pop());
            viewShapes(graphics);
        }
    }

    /**
     * adds the undone shape that was temporarily removed
     */
    public void redoLastShape(GraphicsContext graphics) {
        if (tempShapeList.isEmpty()) {
            System.out.println("You cannot redo anymore.");
        } else {
            shapeList.push(tempShapeList.pop());
            viewShapes(graphics);
        }
    }

    /**
     * removes all shapes from the stack
     */
    public void clearShapeList() {
        shapeList.clear();
        tempShapeList.clear();
    }
}
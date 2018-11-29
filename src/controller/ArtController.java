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
public class ArtController {

    private Stack<IShapeFacade> shapeList = new Stack<>();
    private Stack<IShapeFacade> tempShapeList = new Stack<>();

    /**
     * Adds a new shape to a stack of data type IShapeFacade
     */
    public void handleAddShape(String text, ArrayList<Point> pointList, Color fillColor,
                               Color strokeColor, double strokeWidth, boolean filled) {
        tempShapeList.clear();
        shapeList.add(ShapeFactory.getShape(text, pointList, fillColor, strokeColor, strokeWidth, filled));
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
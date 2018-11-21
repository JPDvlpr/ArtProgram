package controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.*;

import java.util.ArrayList;
import java.util.Stack;

public class ArtController {

    private Stack<IShape> shapeList = new Stack<>();
    private Stack<IShape> tempShapeList = new Stack<>();

    public void handleAddShape(String text, ArrayList<Point> pointList, Color fillColor,
                               Color strokeColor, double strokeWidth, boolean filled) {
        Point[] point = new Point[pointList.size()];

        for (int i = 0; i < pointList.size(); i++) {
            point[i] = pointList.get(i);
        }
        switch (text) {
            case "Oval":
                IShape oval = new Oval(point[0], point[point.length - 1], fillColor, strokeColor, strokeWidth, filled);
                shapeList.add(oval);
                break;
            case "Line":
                IShape line = new Line(point[0], point[point.length - 1], strokeColor, strokeWidth);
                shapeList.add(line);
                break;
            case "Rectangle":
                IShape rectangle = new Rectangle(point[0], point[point.length - 1], fillColor, strokeColor, strokeWidth, filled);
                shapeList.add(rectangle);
                break;
            case "Squiggle":
                IShape squiggle = new Squiggle(point, fillColor, strokeColor, strokeWidth, filled);
                shapeList.add(squiggle);
                break;
            default:
                break;
        }
    }

    public void viewShapes(GraphicsContext graphics) {

        for (IShape shape : shapeList) {
            shape.viewShape(graphics);
        }
    }

    public void removeLastShape() {
        shapeList.remove(shapeList.size() - 1);
    }

    public void undo(GraphicsContext graphics) {
        if (shapeList.isEmpty()) {
            System.out.println("Stack is empty.");

        } else {
            tempShapeList.push(shapeList.pop());
            viewShapes(graphics);
        }
    }

    public void redoLastShape(GraphicsContext graphics) {
        if (tempShapeList.isEmpty()) {
            System.out.println("You cannot redo anymore.");
        } else {
            shapeList.push(tempShapeList.pop());
            viewShapes(graphics);
        }
    }

    public void clearShapeList() {
        shapeList.clear();
    }
}
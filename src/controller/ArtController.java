package controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.*;

import java.util.ArrayList;

public class ArtController {

    private ArrayList<IShape> shapeList = new ArrayList<>();
    private ArrayList<IShape> shapeListCopy = shapeList;

    public void handleAddShape(String text, Point point1, Point point2, Color fillColor,
                               Color strokeColor, double strokeWidth, boolean filled) {

        switch (text) {
            case "Oval":
                IShape oval = new Oval(point1, point2, fillColor, strokeColor, strokeWidth, filled);
                shapeList.add(oval);
                break;
            case "Line":
                IShape line = new Line(point1, point2, strokeColor, strokeWidth);
                shapeList.add(line);
                break;
            case "Rectangle":
                IShape rectangle = new Rectangle(point1, point2, fillColor, strokeColor, strokeWidth, filled);
                shapeList.add(rectangle);
                break;
            case "Squiggle":
                IShape squiggle = new Squiggle(point1, point2, fillColor, strokeColor, strokeWidth, filled);
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

    public void redoLastShape(){
        for (int i = 0; i < shapeList.size(); i++) {
            shapeListCopy.addAll(shapeList);
        }
    }

    public void clearShapeList(){
        shapeList.clear();

    }
}
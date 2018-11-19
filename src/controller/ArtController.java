package controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.*;

import java.util.ArrayList;

public class ArtController {

    ArrayList<IShape> shapeList = new ArrayList<>();

    public void handleAddShape(String text, Color color, Point point1, Point point2) {

        switch (text) {
            case "Oval":
                IShape oval = new Oval(color, point1, point2);
                shapeList.add(oval);
                break;
            case "Line":
                IShape line = new Line(color, point1, point2);
                shapeList.add(line);
                break;
            case "Rectangle":
                break;
            case "Squiggle":
                IShape squiggle = new Squiggle(color, point1, point2);
                shapeList.add(squiggle);
                break;
            default:
                break;
        }
    }

    public void handleAddRect(Color color, double height, double width) {
        //rect
        IShape rectangle = new Rectangle(color, height, width) {

            @Override
            public Point getPointP1(Point point1) {
                return null;
            }

            @Override
            public Point getPointP2(Point point2) {
                return null;
            }
        };
        shapeList.add(rectangle);
    }

    public void viewShapes(GraphicsContext graphics) {

        for (IShape shape : shapeList) {
            shape.viewShape(graphics);
        }
    }

    public void removeLastShape() {
        shapeList.remove(shapeList.size() - 1);
    }

    public void addColor(){

    }
}


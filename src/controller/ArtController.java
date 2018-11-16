package controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.IShape;
import model.Oval;
import model.Point;

import java.util.ArrayList;

public class ArtController {

    ArrayList<IShape> shapeList = new ArrayList<>();

    public void handleAddShape(Color color, Point point1, Point point2) {
        IShape shape = new Oval(color, point1, point2);
        shapeList.add(shape);
    }

    public void viewShapes(GraphicsContext graphics){

        for (IShape shape: shapeList) {
            shape.viewShape(graphics);
        }
    }

    public void removeLastShape() {
        shapeList.remove(shapeList.size() - 1);
    }
}


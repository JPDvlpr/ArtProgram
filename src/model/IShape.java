package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface IShape {
    Color getColor(Color color);

    Point getPointP1(Point point1);

    Point getPointP2(Point point2);

    public void viewShape(GraphicsContext graphics);
}

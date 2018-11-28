package model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface that gets implemented by all shapes
 */
public interface IShape {
    void viewShape(GraphicsContext graphics);
}

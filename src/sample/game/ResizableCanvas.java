package sample.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ResizableCanvas extends Canvas {

    public ResizableCanvas(Pane pane) {
        widthProperty().bind(pane.widthProperty());
        heightProperty().bind(pane.heightProperty());

        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    private void draw() {

    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double v) {
        return Double.MAX_VALUE;
    }

    @Override
    public double prefHeight(double v) {
        return Double.MAX_VALUE;
    }
}

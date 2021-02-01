package sample;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;
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
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        gc.setStroke(Color.RED);
        gc.strokeLine(0, 0, width, height);
        gc.strokeLine(0, height, width, 0);
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

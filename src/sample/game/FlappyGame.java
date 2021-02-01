package sample.game;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.ResizableCanvas;

public class FlappyGame {
    private final Pane root;
    private final GraphicsContext graphics;

    public FlappyGame () {
        this.root = new Pane();
        Canvas canvas = new ResizableCanvas(root);
        this.root.getChildren().add(canvas);
        this.graphics = canvas.getGraphicsContext2D();
        this.root.widthProperty().addListener(evt -> draw());
        this.root.heightProperty().addListener(evt -> draw());

        draw();
    }

    public Parent getRoot() {
        return this.root;
    }

    private void update() {

    }

    private void draw() {
        graphics.setFill(Color.RED);
        graphics.fillRect(100, 100, 100, 100);
    }
}

package sample.game;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.game.drawable.CanvasSprite;
import sample.game.drawable.ResizableCanvasElement;

import java.util.ArrayList;
import java.util.List;

public class FlappyGame {
    private final Pane root;
    private final GraphicsContext graphics;

    private List<ResizableCanvasElement> resizables;

    public FlappyGame () {
        this.root = new Pane();
        Canvas canvas = new ResizableCanvas(root);
        this.root.getChildren().add(canvas);
        this.graphics = canvas.getGraphicsContext2D();
        this.root.widthProperty().addListener(evt -> resized());
        this.root.heightProperty().addListener(evt -> resized());

        this.resizables = new ArrayList<>();
        this.resizables.add(new CanvasSprite(50, 50, 100, 100));

        draw();
    }

    public Parent getRoot() {
        return this.root;
    }

    private void resized() {
        for (ResizableCanvasElement resizeable : this.resizables) {
            resizeable.resize(this.root.getWidth(), this.root.getHeight());
        }
        draw();
    }

    private void update() {

    }

    private void draw() {
        for (ResizableCanvasElement resizeable : this.resizables) {
            resizeable.draw(this.graphics);
        }
    }
}

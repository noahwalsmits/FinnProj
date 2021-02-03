package sample.game;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import sample.game.drawable.DrawableImage;
import sample.game.drawable.Drawable;
import sample.game.drawable.ScreenConfig;

import java.util.ArrayList;
import java.util.List;

public class FlappyGame {
    private final Pane root;
    private final GraphicsContext graphics;

    private List<Drawable> resizables;

    public FlappyGame () {
        this.root = new Pane();
        Canvas canvas = new ResizableCanvas(root);
        this.root.getChildren().add(canvas);
        this.graphics = canvas.getGraphicsContext2D();
        this.root.widthProperty().addListener(evt -> resized());
        this.root.heightProperty().addListener(evt -> resized());

        this.resizables = new ArrayList<>();
        this.resizables.add(new DrawableImage(50, 50, 100, 100));

        resized();
        draw();
    }

    public Parent getRoot() {
        return this.root;
    }

    private void resized() {
        ScreenConfig.CURRENT_SCREEN_WIDTH = this.root.getWidth();
        ScreenConfig.CURRENT_SCREEN_HEIGHT = this.root.getHeight();
        for (Drawable resizeable : this.resizables) {
            resizeable.resize();
        }
        draw();
    }

    private void update() {

    }

    private void draw() {
        for (Drawable resizeable : this.resizables) {
            resizeable.draw(this.graphics);
        }
    }
}

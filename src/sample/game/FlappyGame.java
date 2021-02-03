package sample.game;

import javafx.animation.AnimationTimer;
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
    public static final double MS_PER_UPDATE = 200.0;

    private final Pane root;
    private final GraphicsContext graphics;

    private boolean running;
    private List<Drawable> drawables;
    private Drawable bird;

    public FlappyGame() {
        this.root = new Pane();
        Canvas canvas = new ResizableCanvas(root);
        this.root.getChildren().add(canvas);
        this.graphics = canvas.getGraphicsContext2D();
        this.root.widthProperty().addListener(evt -> resized());
        this.root.heightProperty().addListener(evt -> resized());

        this.running = true;
        this.drawables = new ArrayList<>();
        this.bird = new DrawableImage(50, 50, 300, 100, "jermasus.png");
        this.drawables.add(this.bird);
        resized();

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw();
            }
        }.start();
    }

    public Parent getRoot() {
        return this.root;
    }

    private void resized() {
        ScreenConfig.CURRENT_SCREEN_WIDTH = this.root.getWidth();
        ScreenConfig.CURRENT_SCREEN_HEIGHT = this.root.getHeight();

        //TODO resize drawables on seperate thread
        for (Drawable resizeable : this.drawables) {
            resizeable.resize();
        }
        draw();
    }

    private void update(double elapsedTime) {
        this.bird.setBaseX(this.bird.getBaseX() + (100.0 * elapsedTime));
        if (this.bird.getBaseX() > 1000) {
            this.running = false;
        }
    }

    private void draw() {
        this.graphics.clearRect(0, 0, this.root.getWidth(), this.root.getHeight());
        for (Drawable drawable : this.drawables) {
            drawable.draw(this.graphics);
        }
    }
}

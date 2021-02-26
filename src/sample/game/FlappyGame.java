package sample.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.ContentScreen;
import sample.game.drawable.Drawable;
import sample.game.drawable.ScreenConfig;

import java.util.ArrayList;
import java.util.List;

public class FlappyGame implements ContentScreen {
    private final Pane root;
    private final GraphicsContext graphics;
    private final AnimationTimer animationTimer;

    private List<Drawable> drawables;
    private FlappyCharacter character;
    private ScrollingBackground scrollingBackground;
    private Obstacle obstacle; //TODO obstacle array

    public FlappyGame() {
        //setup root and resizing
        this.root = new Pane();
        Canvas canvas = new ResizableCanvas(root);
        this.root.getChildren().add(canvas);
        this.graphics = canvas.getGraphicsContext2D();
        this.root.widthProperty().addListener(evt -> resized());
        this.root.heightProperty().addListener(evt -> resized());
        this.root.setMinWidth(10.0);
        this.root.setMinHeight(10.0);
        this.root.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));//

        //setup drawables
        this.drawables = new ArrayList<>();

        this.scrollingBackground = new ScrollingBackground("wall.jpg");
        for (Drawable drawable : this.scrollingBackground.getDrawables()) {
            this.drawables.add(drawable);
        }

        this.obstacle = new Obstacle(500.0);
        for (Drawable drawable : this.obstacle.getDrawables()) {
            this.drawables.add(drawable);
        }

        this.character = new FlappyCharacter();
        this.root.setOnMouseClicked(mouseEvent -> this.character.jump());
        this.drawables.add(this.character.getDrawable());

        resized();

        //setup animation timer
        this.animationTimer = new AnimationTimer() {
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
        };
        this.animationTimer.start();
    }

    @Override
    public Parent getRoot() {
        return this.root;
    }

    @Override
    public void exit() {
        this.animationTimer.stop();
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
        this.character.update(elapsedTime);
        this.obstacle.update(elapsedTime, this.character);
        this.scrollingBackground.update(elapsedTime);
    }

    private void draw() {
        this.graphics.clearRect(0, 0, this.root.getWidth(), this.root.getHeight());
        for (Drawable drawable : this.drawables) {
            drawable.draw(this.graphics);
        }
    }
}

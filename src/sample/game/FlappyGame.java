package sample.game;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.ContentScreen;
import sample.game.drawable.Drawable;
import sample.game.drawable.ScreenConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlappyGame implements ContentScreen {
    private final Pane root;
    private final GraphicsContext graphics;
    private final AnimationTimer animationTimer;

    private List<Drawable> drawables;
    private FlappyCharacter character;
    private ScrollingBackground scrollingBackground;
    private Obstacle[] obstacles;
    private int pointsGained;

    public FlappyGame() {
        //setup root and resizing
        this.root = new StackPane();
        Canvas canvas = new ResizableCanvas(root);
        this.root.getChildren().add(canvas);
        this.graphics = canvas.getGraphicsContext2D();
        this.root.widthProperty().addListener(evt -> resized());
        this.root.heightProperty().addListener(evt -> resized());
        this.root.setMinWidth(10.0);
        this.root.setMinHeight(10.0);
        this.root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));//

        //setup drawables
        this.drawables = new ArrayList<>();

        this.scrollingBackground = new ScrollingBackground("background.png");
        this.drawables.addAll(Arrays.asList(this.scrollingBackground.getDrawables()));

        this.obstacles = new Obstacle[]{
                new Obstacle(ScreenConfig.BASE_SCREEN_WIDTH),
                new Obstacle(ScreenConfig.BASE_SCREEN_WIDTH * 1.5)};
        for (Obstacle obstacle : this.obstacles) {
            Collections.addAll(this.drawables, obstacle.getDrawables());
        }

        this.character = new FlappyCharacter(this);
        this.root.setOnMouseClicked(mouseEvent -> this.start());
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
        this.pointsGained = 0;
    }

    @Override
    public Parent getRoot() {
        return this.root;
    }

    @Override
    public void exit() {
        this.animationTimer.stop();
        this.root.setOnMouseClicked(null);
    }

    public void start() {
        this.animationTimer.start();
        this.root.setOnMouseClicked(mouseEvent -> this.character.jump());
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
        for (Obstacle obstacle : this.obstacles) {
            obstacle.update(elapsedTime, this.character);
        }
        this.scrollingBackground.update(elapsedTime);
    }

    private void draw() {
        this.graphics.clearRect(0, 0, this.root.getWidth(), this.root.getHeight());
        for (Drawable drawable : this.drawables) {
            drawable.draw(this.graphics);
        }
    }

    public void gameOver() {
        this.exit();
        this.drawables.clear();
        this.root.getChildren().clear();
        Label label = new Label("GAME OVER! you've scored " + this.pointsGained + " point(s)!");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font(null, FontWeight.BOLD, 50));
        label.setAlignment(Pos.CENTER);
        this.root.getChildren().add(label);
    }

    public void scoredPoint() {
        this.pointsGained++;
    }
}

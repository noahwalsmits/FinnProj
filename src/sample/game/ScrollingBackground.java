package sample.game;

import javafx.scene.canvas.GraphicsContext;
import sample.game.drawable.Drawable;
import sample.game.drawable.DrawableImage;
import sample.game.drawable.ScreenConfig;

public class ScrollingBackground {
    private DrawableImage[] backgroundDrawables;

    private static double SCROLLING_SPEED = 10.0;

    public ScrollingBackground(String... backgroundResources) {
        //TODO use strings instead of hardcoding drawables
        DrawableImage drawable1 = new DrawableImage(0, 0,
                1000.0, 1000.0,
                "penguin.png");
        DrawableImage drawable2 = new DrawableImage(ScreenConfig.BASE_SCREEN_WIDTH, 0.0,
                ScreenConfig.BASE_SCREEN_WIDTH, ScreenConfig.BASE_SCREEN_HEIGHT,
                "background1.jpg");
        this.backgroundDrawables = new DrawableImage[]{drawable1};
    }

    public void draw(GraphicsContext graphics) {
        for (Drawable drawable : this.backgroundDrawables) {
            drawable.draw(graphics);
        }
    }

    public void update(double elapsedTime) {
        for (Drawable drawable : this.backgroundDrawables) {
            drawable.setBaseX(drawable.getBaseX() + SCROLLING_SPEED * elapsedTime);
        }
    }

    public Drawable[] getDrawables() {
        return this.backgroundDrawables;
    }
}

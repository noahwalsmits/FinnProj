package sample.game;

import javafx.stage.Screen;
import sample.game.drawable.Drawable;
import sample.game.drawable.DrawableImage;
import sample.game.drawable.ScreenConfig;

public class ScrollingBackground {
    private DrawableImage[] backgroundDrawables;

    private static double SCROLLING_SPEED = 150.0;

    public ScrollingBackground(String... backgroundResources) {
        //TODO use strings instead of hardcoding drawables
        DrawableImage drawable1 = new DrawableImage(0.0, 0.0,
                ScreenConfig.BASE_SCREEN_WIDTH, ScreenConfig.BASE_SCREEN_HEIGHT,
                "jermasus.png");
        DrawableImage drawable2 = new DrawableImage(ScreenConfig.BASE_SCREEN_WIDTH, 0.0,
                ScreenConfig.BASE_SCREEN_WIDTH, ScreenConfig.BASE_SCREEN_HEIGHT,
                "jermasus.png");
        this.backgroundDrawables = new DrawableImage[]{drawable1, drawable2};
    }

    public void update(double elapsedTime) {
        for (Drawable drawable : this.backgroundDrawables) { //scroll every drawable
            drawable.setBaseX(drawable.getBaseX() - SCROLLING_SPEED * elapsedTime);

            if (drawable.getBaseX() + drawable.getBaseWidth() <= 0.0) { //if a drawable has scrolled off the screen
                Drawable furthest = drawable;
                for (Drawable compared : this.backgroundDrawables) { //find the furthest drawable
                    if (compared.getBaseX() > furthest.getBaseX()) {
                        furthest = compared;
                    }
                }
                drawable.setBaseX(furthest.getBaseX() + furthest.getBaseWidth()); //place the drawable after the furthest
            }
        }
    }

    public Drawable[] getDrawables() {
        return this.backgroundDrawables;
    }
}

package sample.game;

import javafx.stage.Screen;
import sample.game.drawable.Drawable;
import sample.game.drawable.DrawableImage;
import sample.game.drawable.ScreenConfig;

import java.util.ArrayList;
import java.util.List;

public class ScrollingBackground {
    private DrawableImage[] backgroundDrawables;

    private static double SCROLLING_SPEED = 150.0;

    public ScrollingBackground(String... backgroundResources) {
        List<DrawableImage> drawables = new ArrayList<>();
        for (int i = 0; i < backgroundResources.length; i++) {
            drawables.add(new DrawableImage(ScreenConfig.BASE_SCREEN_WIDTH * i, 0.0,
                    ScreenConfig.BASE_SCREEN_WIDTH, ScreenConfig.BASE_SCREEN_HEIGHT,
                    backgroundResources[i]));
        }
        if (drawables.size() < 2) {
            drawables.add(new DrawableImage(ScreenConfig.BASE_SCREEN_WIDTH * drawables.size(), 0.0,
                    ScreenConfig.BASE_SCREEN_WIDTH, ScreenConfig.BASE_SCREEN_HEIGHT,
                    backgroundResources[drawables.size() - 1]));
        }
        this.backgroundDrawables = new DrawableImage[drawables.size()];
        this.backgroundDrawables = drawables.toArray(this.backgroundDrawables);
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

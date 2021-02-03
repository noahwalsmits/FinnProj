package sample.game.drawable;

import javafx.scene.canvas.GraphicsContext;

public abstract class Drawable {
    private double baseX;
    private double baseY;
    private double baseWidth;
    private double baseHeight;

    public Drawable(double baseX, double baseY, double baseWidth, double baseHeight) {
        this.baseX = baseX;
        this.baseY = baseY;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    public abstract void draw(GraphicsContext graphics);

    public abstract void resize();

    public double getX() {
        return this.baseX * ((double) ScreenConfig.CURRENT_SCREEN_WIDTH / ScreenConfig.BASE_SCREEN_WIDTH);
    }

    public double getY() {
        return this.baseY * ((double) ScreenConfig.CURRENT_SCREEN_HEIGHT / ScreenConfig.BASE_SCREEN_HEIGHT);
    }

    public double getWidth() {
        return this.baseWidth * ((double) ScreenConfig.CURRENT_SCREEN_WIDTH / ScreenConfig.BASE_SCREEN_WIDTH);
    }

    public double getHeight() {
        return this.baseHeight * ((double) ScreenConfig.CURRENT_SCREEN_HEIGHT / ScreenConfig.BASE_SCREEN_HEIGHT);
    }

}

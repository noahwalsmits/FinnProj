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

    public double getBaseX() {
        return baseX;
    }

    public void setBaseX(double baseX) {
        this.baseX = baseX;
    }

    public double getBaseY() {
        return baseY;
    }

    public void setBaseY(double baseY) {
        this.baseY = baseY;
    }

    public double getBaseWidth() {
        return baseWidth;
    }

    public void setBaseWidth(double baseWidth) {
        this.baseWidth = baseWidth;
    }

    public double getBaseHeight() {
        return baseHeight;
    }

    public void setBaseHeight(double baseHeight) {
        this.baseHeight = baseHeight;
    }
}

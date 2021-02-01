package sample.game.drawable;

import javafx.scene.canvas.GraphicsContext;

public abstract class ResizableCanvasElement {
    private double screenWidth;
    private double screenHeight;

    private double baseX;
    private double baseY;
    private double baseWidth;
    private double baseHeight;

    public ResizableCanvasElement(double baseX, double baseY, double baseWidth, double baseHeight) {
        this.baseX = baseX;
        this.baseY = baseY;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    public abstract void draw(GraphicsContext graphics);

    public void resize(double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public double getX() {
        return this.baseX * ((double) this.screenWidth / ScreenConfig.BASE_SCREEN_WIDTH);
    }

    public double getY() {
        return this.baseY * ((double) this.screenHeight / ScreenConfig.BASE_SCREEN_HEIGHT);
    }

    public double getWidth() {
        return this.baseWidth * ((double) this.screenWidth / ScreenConfig.BASE_SCREEN_WIDTH);
    }

    public double getHeight() {
        return this.baseHeight * ((double) this.screenHeight / ScreenConfig.BASE_SCREEN_HEIGHT);
    }

}

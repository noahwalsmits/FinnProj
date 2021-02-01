package sample.game.drawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasSprite extends ResizableCanvasElement {
    public CanvasSprite(double baseX, double baseY, double baseWidth, double baseHeight) {
        super(baseX, baseY, baseWidth, baseHeight);
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.setFill(Color.BLUE);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}

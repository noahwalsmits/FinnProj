package sample.game.drawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableImage extends Drawable {
    public DrawableImage(double baseX, double baseY, double baseWidth, double baseHeight) {
        super(baseX, baseY, baseWidth, baseHeight);
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.setFill(Color.BLUE);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void resize() {

    }
}

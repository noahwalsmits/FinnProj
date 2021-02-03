package sample.game.drawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DrawableImage extends Drawable {
    private Image image;
    private String imageFileName;

    public DrawableImage(double baseX, double baseY, double baseWidth, double baseHeight, String imageFileName) {
        super(baseX, baseY, baseWidth, baseHeight);
        this.imageFileName = imageFileName;
        this.resize();
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.drawImage(this.image, this.getX(), this.getY());
    }

    @Override
    public void resize() {
        this.image = this.generateImage();
    }

    private Image generateImage() {
        Image generatedImage;
        try {
            generatedImage = new Image(getClass().getResource("/images/" + imageFileName).toString(),
                    this.getWidth(),
                    this.getHeight(),
                    false,
                    true); //true = better quality resize || false = faster resize
        } catch (NullPointerException e) {
            System.out.println("Drawable: Could not find image");
            e.printStackTrace();
            return null;
        }
        return generatedImage;
    }
}

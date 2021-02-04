package sample.game.drawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class DrawableImage extends Drawable {
    private Image image;
    private String imageFileName;
    private double rotation;

    public DrawableImage(double baseX, double baseY, double baseWidth, double baseHeight, String imageFileName) {
        super(baseX, baseY, baseWidth, baseHeight);
        this.imageFileName = imageFileName;
        this.rotation = 45.0;
        this.resize();
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.save();
        Rotate r = new Rotate(this.rotation,
                this.getX() + (this.getWidth()) / 2,
                this.getY() + this.getHeight() / 2);
        graphics.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        graphics.drawImage(this.image, this.getX(), this.getY());
        graphics.restore();
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

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}

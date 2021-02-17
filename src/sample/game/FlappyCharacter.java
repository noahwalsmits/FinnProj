package sample.game;

import javafx.scene.media.AudioClip;
import sample.game.drawable.DrawableImage;
import sample.game.drawable.ScreenConfig;

public class FlappyCharacter {
    private DrawableImage drawable;
    private AudioClip jumpSound;
    private double height;
    private double yVelocity;

    public FlappyCharacter() {
        this.drawable = new DrawableImage(50, 50, 300, 300, "penguin.png");
        this.jumpSound = new AudioClip(getClass().getResource("/sounds/nsmbwiiJump1.wav").toString());
        this.height = 0.0;
        this.yVelocity = 0.0;
    }

    public void update(double elapsedTime) {
//        this.drawable.setBaseY(ScreenConfig.BASE_SCREEN_HEIGHT - this.height);
        if (this.drawable.getBaseY() < ScreenConfig.BASE_SCREEN_HEIGHT) {
            this.yVelocity += 300.0 * elapsedTime;
        }
        this.drawable.setBaseY(this.drawable.getBaseY() + this.yVelocity * elapsedTime);

        if (this.drawable.getRotation() < 0.0) {
            this.drawable.setRotation(this.drawable.getRotation() + 100.0 * elapsedTime);
        }
    }

    public void jump() {
        if (this.yVelocity > -100.0) {
            this.yVelocity = -100.0;
        }
        this.yVelocity += -150.0;
        this.jumpSound.play();
        this.drawable.setRotation(this.drawable.getRotation() - 30.0);
    }

    public DrawableImage getDrawable() {
        return drawable;
    }
}

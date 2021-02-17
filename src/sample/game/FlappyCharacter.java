package sample.game;

import javafx.scene.media.AudioClip;
import sample.game.drawable.DrawableImage;
import sample.game.drawable.ScreenConfig;

public class FlappyCharacter {
    private DrawableImage drawable;
    private final AudioClip jumpSound;
    private final AudioClip bumpSound;
    private double height; //height based on base screen height
    private double yVelocity; //the height is changed by the velocity every second

    private static final double GRAVITY = 300.0;
    private static final double JUMP_BASE_VELOCITY = 100.0;
    private static final double JUMP_ADDED_VELOCITY = 150.0;
    private static final double SPRITE_HEIGHT = 150.0;

    public FlappyCharacter() {
        this.drawable = new DrawableImage(50, 50, SPRITE_HEIGHT, SPRITE_HEIGHT, "penguin.png");
        this.jumpSound = new AudioClip(getClass().getResource("/sounds/nsmbwiiJump1.wav").toString());
        this.bumpSound = new AudioClip(getClass().getResource("/sounds/nsmbwiiBump.wav").toString());
        this.height = 500.0;
        this.yVelocity = 0.0;
    }

    public void update(double elapsedTime) {
        if (this.height > SPRITE_HEIGHT) { //when character not touching ground
            this.yVelocity -= GRAVITY * elapsedTime; //apply gravity to velocity
            if (this.height > ScreenConfig.BASE_SCREEN_HEIGHT) { //when character touches roof
                this.height = ScreenConfig.BASE_SCREEN_HEIGHT;
                this.yVelocity *= -1.0;
                this.bumpSound.play();
            }
        } else { //when character is touching ground
            this.gameOver();
        }
        this.height += this.yVelocity * elapsedTime; //change height by velocity

        if (this.drawable.getRotation() < 0.0) { //when rotation not reset
            this.drawable.setRotation(this.drawable.getRotation() + ((-this.drawable.getRotation() * 0.5 + 100.0) * elapsedTime)); //gradually reset rotation
        }
        this.drawable.setBaseY(ScreenConfig.BASE_SCREEN_HEIGHT - this.height); //change sprite height
    }

    public void jump() {
        if (this.yVelocity < JUMP_BASE_VELOCITY) {
            this.yVelocity = JUMP_BASE_VELOCITY;
        }
        this.yVelocity += JUMP_ADDED_VELOCITY;
        this.jumpSound.play();
        this.drawable.setRotation(this.drawable.getRotation() - 30.0);
    }

    public void gameOver() {
        //todo
    }

    public DrawableImage getDrawable() {
        return drawable;
    }
}

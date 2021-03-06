package sample.game;

import javafx.scene.media.AudioClip;
import sample.game.drawable.DrawableImage;
import sample.game.drawable.ScreenConfig;

public class FlappyCharacter {
    private DrawableImage drawable;
    private AudioClip jumpSound;
    private AudioClip bumpSound;
    private AudioClip scoreSound;
    private AudioClip gameOverSound;
    private double height; //height based on base screen height
    private double yVelocity; //the height is changed by the velocity every second
    private boolean gameOver;
    private final FlappyGame game;

    public static final double SPRITE_SIZE = 150.0;
    public static final double SPRITE_X = 50.0;
    private static final double GRAVITY = 300.0;
    private static final double JUMP_BASE_VELOCITY = 100.0;
    private static final double JUMP_ADDED_VELOCITY = 150.0;

    public FlappyCharacter(FlappyGame game) {
        //TODO equip custom sprites
        this.drawable = new DrawableImage(SPRITE_X, 50, SPRITE_SIZE, SPRITE_SIZE, "penguin.png");
        this.jumpSound = new AudioClip(getClass().getResource("/sounds/nsmbwiiJump1.wav").toString());
        this.bumpSound = new AudioClip(getClass().getResource("/sounds/nsmbwiiBump.wav").toString());
        this.scoreSound = new AudioClip(getClass().getResource("/sounds/nsmbwiiCoin.wav").toString());
        this.gameOverSound = new AudioClip(getClass().getResource("/sounds/nsmbwiiDeath.wav").toString());
        this.height = 900.0;
        this.yVelocity = 0.0;
        this.gameOver = false;
        this.game = game;
    }

    public FlappyCharacter(FlappyGame game, String cosmetic) {
        this(game);
        if (cosmetic.equals("jerma")) {
            this.drawable = new DrawableImage(SPRITE_X, 50, SPRITE_SIZE, SPRITE_SIZE, "jermafunny.png");
            this.jumpSound = new AudioClip(getClass().getResource("/sounds/aa.mp3").toString());
            this.bumpSound = new AudioClip(getClass().getResource("/sounds/oo.mp3").toString());
            this.scoreSound = new AudioClip(getClass().getResource("/sounds/ee.mp3").toString());
            this.gameOverSound = new AudioClip(getClass().getResource("/sounds/aHHHHhwaheha.mp3").toString());
        }
    }

    public void update(double elapsedTime) {
        if (this.height > SPRITE_SIZE) { //when character not touching ground
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
        if (!this.gameOver) {
            this.gameOver = true;
            this.gameOverSound.play();
            this.game.gameOver();
        }
    }

    public void scoredPoint() {
        this.scoreSound.play();
        this.game.scoredPoint();
    }

    public DrawableImage getDrawable() {
        return drawable;
    }

    public double getHeight() {
        return height;
    }
}

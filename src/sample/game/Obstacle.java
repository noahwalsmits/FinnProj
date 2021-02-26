package sample.game;

import sample.game.drawable.Drawable;
import sample.game.drawable.DrawableImage;

public class Obstacle {
    private double xPosition;
    private double gapSize;
    private double gapHeight;
    private DrawableImage topSprite;
    private DrawableImage bottomSprite;

    private static double SCROLLING_SPEED = 150.0;
    private static double SPRITE_HEIGHT = 1080.0;
    private static double SPRITE_WIDTH = 150.0;

    public Obstacle(double startX) {
        this.xPosition = startX;
        this.gapSize = 0.0;
        this.gapHeight = 0.0;
        this.topSprite = new DrawableImage(this.xPosition, this.gapHeight + this.gapSize + SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, "wall.jpg");
        this.bottomSprite = new DrawableImage(this.xPosition, this.gapHeight, SPRITE_WIDTH, SPRITE_HEIGHT, "wall.jpg");
    }

    public void update(double elapsedTime, FlappyCharacter character) {
        //move obstacle and sprites
        this.xPosition += SCROLLING_SPEED;

        //move out of bounds obstacles

        //check if character is hit
    }

    public Drawable[] getDrawables() {
        return new Drawable[]{this.topSprite, this.bottomSprite};
    }

    private double getGapSize() {
        return 0.0;
    }

    private double getGapHeight() {
        return 0.0;
    }
}

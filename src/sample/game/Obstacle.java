package sample.game;

import sample.game.drawable.Drawable;
import sample.game.drawable.DrawableImage;
import sample.game.drawable.ScreenConfig;

public class Obstacle {
    private double xPosition;
    private double gapSize;
    private double gapHeight;
    private DrawableImage topSprite;
    private DrawableImage bottomSprite;

    private static double SCROLLING_SPEED = 450.0;
    private static double SPRITE_HEIGHT = 1080.0;
    private static double SPRITE_WIDTH = 150.0;

    public Obstacle(double startX) {
        this.xPosition = startX;
        this.gapSize = this.getRandomGapSize();
        this.gapHeight = this.getRandomGapHeight();
        this.topSprite = new DrawableImage(this.xPosition, ScreenConfig.BASE_SCREEN_HEIGHT - this.gapHeight - this.gapSize - SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, "wall.jpg");
        this.bottomSprite = new DrawableImage(this.xPosition, ScreenConfig.BASE_SCREEN_HEIGHT - this.gapHeight, SPRITE_WIDTH, SPRITE_HEIGHT, "wall.jpg");
    }

    public void update(double elapsedTime, FlappyCharacter character) {
        //move obstacle and sprites
        this.xPosition -= SCROLLING_SPEED * elapsedTime;
        this.topSprite.setBaseX(this.xPosition);
        this.bottomSprite.setBaseX(this.xPosition);

        //move out of bounds obstacles
        if (this.xPosition + SPRITE_WIDTH < 0.0) { //obstacle has left screen on the left side
            this.xPosition = ScreenConfig.BASE_SCREEN_WIDTH + SPRITE_WIDTH; //move it offscreen to the right side
            this.gapSize = this.getRandomGapSize(); //generate new gap size
            this.gapHeight = this.getRandomGapHeight(); //generate new gap height
            this.topSprite.setBaseY(ScreenConfig.BASE_SCREEN_HEIGHT - this.gapHeight - this.gapSize - SPRITE_HEIGHT);
            this.bottomSprite.setBaseY(ScreenConfig.BASE_SCREEN_HEIGHT - this.gapHeight);
        }

        //check if character is hit
        if (this.xPosition <= FlappyCharacter.SPRITE_X + FlappyCharacter.SPRITE_SIZE
        && this.xPosition + SPRITE_WIDTH >= FlappyCharacter.SPRITE_X) { //when character and obstacle overlap in width
            if (character.getHeight() - FlappyCharacter.SPRITE_SIZE <= this.gapHeight
                    || character.getHeight() >= this.gapHeight + this.gapSize) { //if touching bottom OR if touching top
                character.gameOver();
            }
        }
    }

    public Drawable[] getDrawables() {
        return new Drawable[]{this.topSprite, this.bottomSprite};
    }

    private double getRandomGapSize() {
        double max = FlappyCharacter.SPRITE_SIZE * 2.6;
        double min = FlappyCharacter.SPRITE_SIZE * 1.4;
        return (Math.random() * (max - min)) + min;
    }

    private double getRandomGapHeight() {
        double max = ScreenConfig.BASE_SCREEN_HEIGHT - this.gapSize;
        double min = 0.1 * ScreenConfig.BASE_SCREEN_HEIGHT;
        return (Math.random() * (max - min)) + min;
    }
}

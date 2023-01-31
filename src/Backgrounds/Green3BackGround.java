package Backgrounds;
import Animations.GameLevel;
import ShapedObjects.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;


/**
 * class provides third level's background animation.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public class Green3BackGround implements Sprite {
    private int leftWhealCenterX = 15;
    private int leftWhealCenterY = 535;
    private int rightWhealCenterX = 68;
    private int rightWhealCenterY = 535;
    private int carUpperLeftX = 0;
    private int carUpperLeftY = 495;
    /**
     * Draw the sprite to the screen.
     *
     * @param d the canvas on the screen to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.green.brighter());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.orange);
        d.fillCircle(200, 120, 65);
        d.setColor(Color.orange.brighter());
        d.fillCircle(200, 120, 50);
        d.setColor(Color.gray);
        d.fillRectangle(0, 400, 800, 150);
        d.setColor(Color.white);
        d.fillRectangle(0, 470, 800, 20);
        d.drawText(80, 460, "HIT THE ROAD, JACK!", 50);
        d.setColor(Color.red);
        d.fillRectangle(carUpperLeftX, carUpperLeftY, 80, 50);
        d.setColor(Color.black);
        d.fillCircle(leftWhealCenterX, leftWhealCenterY, 10);
        d.fillCircle(rightWhealCenterX, rightWhealCenterY, 10);
    }

    /**
     * Notify the sprite that time has passed.
     * the object will change some of its data accordingly if needed.
     */
    @Override
    public void timePassed() {
            carUpperLeftX++;
            rightWhealCenterX++;
            leftWhealCenterX++;
        }

    /**
     * Add sprite to a game.
     *
     * @param game the game to be added to
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);

    }
}

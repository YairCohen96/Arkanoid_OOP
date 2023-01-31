package Backgrounds;

import Animations.GameLevel;
import ShapedObjects.Ball;
import ShapedObjects.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * class provides second level's background animation.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public class WideEsayBackGround implements Sprite {
    private Ball hideMoon = new Ball(330, 120, 65, Color.blue);
    /**
     * Draw the sprite to the screen.
     *
     * @param d the canvas on the screen to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.fillCircle(200, 120, 65);
        d.drawText(80, 400, "OOP ALL-NIGHT!", 40);
        d.setColor(hideMoon.getColor());
        d.fillCircle(hideMoon.getX(), hideMoon.getY(), hideMoon.getSize());
        /*d.drawCircle(400, 110, 50);
        d.drawCircle(400, 110, 35);
        d.drawCircle(400, 110, 20);*/
    }

    /**
     * Notify the sprite that time has passed.
     * the object will change some of its data accordingly if needed.
     */
    @Override
    public void timePassed() {
        if (hideMoon.getX() > 230) {
            hideMoon.setCenter(hideMoon.getX() - 1, hideMoon.getY());
        }
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

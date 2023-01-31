package Backgrounds;

import Animations.GameLevel;
import ShapedObjects.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * class provides first level's background animation.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public class DirectrHitBackGround implements Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d the canvas on the screen to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.blue);
        d.drawCircle(400, 110, 65);
        d.drawCircle(400, 110, 50);
        d.drawCircle(400, 110, 35);
        d.drawCircle(400, 110, 20);
    }

    /**
     * Notify the sprite that time has passed.
     * the object will change some of its data accordingly if needed.
     */
    @Override
    public void timePassed() {

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

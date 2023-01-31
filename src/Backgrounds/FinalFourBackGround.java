package Backgrounds;

import Animations.GameLevel;
import ShapedObjects.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * class provides forth level's background animation.
 * author: Yair Cohen
 * version date: 30/05/22
 */

public class FinalFourBackGround implements Sprite {
    private int eyeR = 15;

    /**
     * Draw the sprite to the screen.
     *
     * @param d the canvas on the screen to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue.darker());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.orange);
        d.fillCircle(200, 120, 65);
        d.setColor(Color.orange.brighter());
        d.fillCircle(200, 120, 50);
        /*d.setColor(Color.black);
        d.drawCircle(205, 125, 42);*/
        d.setColor(Color.white);
        d.fillCircle(208, 128, 34);
        d.setColor(Color.blue.brighter().brighter());
        d.fillCircle(212, 132, 30);
        d.setColor(Color.black);
        d.fillCircle(220, 140, eyeR);
        d.drawText(380, 130, "EYE IN THE SKY", 45);
    }

    /**
     * Notify the sprite that time has passed.
     * the object will change some of its data accordingly if needed.
     */
    @Override
    public void timePassed() {
        if (eyeR == 1) {
            eyeR = 15;
        } else {
            eyeR--;
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

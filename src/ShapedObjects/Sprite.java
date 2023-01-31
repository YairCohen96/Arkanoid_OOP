package ShapedObjects;// Yair Cohen 313355786

import Animations.GameLevel;
import biuoop.DrawSurface;
/**
 * GameInterfaces.ShapedObjects.Sprite interface defines a type of objects in a game, that can be drawn on a canvas of a game,
 * and change data (such as location) every step of the game if needed.
 * author: Yair Cohen
 * version date: 12/04/22
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * @param d the canvas on the screen to be drawn on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     * the object will change some of its data accordingly if needed.
     */
    void timePassed();
    /**
     * Add sprite to a game.
     * @param game the game to be added to
     */
    void addToGame(GameLevel game);
} // end of interface

package DataTrack;// Yair Cohen 313355786

import Animations.GameLevel;
import ShapedObjects.Sprite;
import biuoop.DrawSurface;
/**
 * DataTrack.ScoreIndicator class prints the score to the screen.
 * author: Yair Cohen
 * version date: 17/05/22
 */

public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor for the class.
     * @param score the current score of player.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * Draw the sprite to the screen.
     *
     * @param d the canvas on the screen to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(350, 17, "Score: " + Integer.toString(score.getValue()), 20);
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

package Animations;


import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * class creates the end screen for a game in the Arkanoid.
 * The class will print for player his result - win/game over and his final score.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public class EndScreen implements Animation {
    private final Boolean result;
    private final boolean stop;
    private final int score;
    private final GUI gui;
    private final KeyboardSensor k;
    /**
     * Consructor of class, creates a new end screen.
     * @param res - indicates win/lose of the game.
     * @param gui - the screen to be printed on.
     * @param finalScore - the final score achieved, to be printed.
     * @param key - keyboard - for pressing space key and close the end scrren - terminate the game.
     */

    public EndScreen(boolean res, int finalScore, GUI gui, KeyboardSensor key) {
        this.result = res;
        this.score = finalScore;
        this.gui = gui;
        this.k = key;
        stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (result) {
            d.setColor(Color.blue);
            d.drawText(80, d.getHeight() / 2, "you win! your score is: " + score, 50);
        } else {
            d.setColor(Color.red);
            d.drawText(80, d.getHeight() / 2, "game over! your score is: " + score, 50);
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}

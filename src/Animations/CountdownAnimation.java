package Animations;

import ShapedObjects.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The Animations.CountdownAnimation class will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection screen;
    /**
     * Constructor for the class.
     * @param numOfSeconds - amount of seconds to count down.
     * @param countFrom - top number to count from.
     * @param gameScreen - the first frame of the animation that will run after countdown - to be
     *                      displayed in the back.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        screen = gameScreen;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        screen.drawAllOn(d);
        d.setColor(Color.yellow);
        d.drawText(d.getWidth() / 2 - 50, d.getHeight() / 2, Integer.toString((int) numOfSeconds), 200);
        Sleeper sleeper = new Sleeper();
        sleeper.sleepFor(670); // 3, 2 , 1 in 2 seconds as requested
        numOfSeconds--;
    }
    @Override
    public boolean shouldStop() {
        return numOfSeconds == 0;
    }
}
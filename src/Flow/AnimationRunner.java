package Flow;

import Animations.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * class runs an animation on a given gui window.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    /**
     * Constructor for the class.
     * @param gui the window to run the animation on.
     */
    public AnimationRunner(GUI gui) {
        framesPerSecond = 60;
        this.gui = gui;
        //gui = new GUI("Arkanoid", 800, 600);
    }

    /**
     * @return the gui window of the runner.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Method runs the given animation on the screen.
     * @param animation the given animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        //sleeper.sleepFor(500);
        //gui.close();
    }
}
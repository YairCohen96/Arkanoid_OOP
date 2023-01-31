package Animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * Class is responsible for running animation of pause screen when called and pauses the game.
 * author: Yair Cohen
 * version date: 30/05/22
 */

public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * Constructor for class.
     * @param k the keyboard sensor to indicate a pause is needed.
     */

    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        /*if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }*/
        //spaceStop.doOneFrame(d);
        /*if (spaceStop.shouldStop()) {
            this.stop = true;
        }*/
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
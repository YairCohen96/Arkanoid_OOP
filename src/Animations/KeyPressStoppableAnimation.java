package Animations;// Yair Cohen 313355786

import Animations.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Class is responsible for running animations that can stop and repeat by pressing a pause button.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor for class.
     * @param sensor the keyboard sensor.
     * @param key the key that needs to get the animation back to running.
     * @param animation the animation to manage.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
            this.sensor = sensor;
            this.key = key;
            this.animation = animation;
            this.stop = false;
            this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (sensor.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (sensor.isPressed(" ") && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!sensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
        animation.doOneFrame(d);
    }
    @Override
    public boolean shouldStop() {
        return stop;
    }
}

package Animations;

import biuoop.DrawSurface;

/**
 * Interface for an animation to be drawn on a given canvas (drawSurface)
 * supports moving frames and stopping the animation.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public interface Animation {
    /**
     * Display on frame or one step of the animation.
     * @param d the canvas to be drawn on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Indicate if the animation dispaly should be terminated.
     * @return true if needs to be terminated, false otherwise.
     */
    boolean shouldStop();
}
package DataTrack;//Yair Cohen 313355786

import ShapedObjects.Ball;
import ShapedObjects.Block;

/**
 * DataTrack.HitListener interface objects are being informed when a hit is happening in the game.
 * Then, they take some action accordingly
 * author: Yair Cohen
 * version date: 17/05/22
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the ShapedObjects.Ball that's doing the hitting.
     * @param beingHit
     * @param hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}

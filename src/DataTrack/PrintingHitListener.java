package DataTrack;//Yair Cohen 313355786

import ShapedObjects.Ball;
import ShapedObjects.Block;

/**
 * DataTrack.PrintingHitListener class prints a message when a DataTrack.HitListener is being hit.
 * author: Yair Cohen
 * version date: 17/05/22
 */
public class PrintingHitListener implements HitListener {
    /**
     * prints a message when a DataTrack.HitListener is being hit.
     * @param beingHit block that was hit.
     * @param hitter the ball that hits.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A ShapedObjects.Block was hit.");
    }
}
package DataTrack;

import ShapedObjects.Ball;
import ShapedObjects.Block;

/**
 * DataTrack.ScoreTrackingListener class updates the score when player hits a block.
 * author: Yair Cohen
 * version date: 17/05/22
 */

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor for class.
     * @param scoreCounter the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }

    /**
     * @return current score
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}
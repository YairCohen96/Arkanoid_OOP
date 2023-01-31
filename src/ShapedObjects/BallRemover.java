package ShapedObjects;

import Animations.GameLevel;
import DataTrack.Counter;
import DataTrack.HitListener;


/**
 * ShapedObjects.BallRemover class is responsible for removing a ball from a game,  as well as keeping count
 * of the number of blocks that remain.
 * author: Yair Cohen
 * version date: 17/05/22
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     *Construct a new ballRemover.
     *defines a default frame to be printed within, and default velocity.
     * @param game - game of the ball.
     * @param remainingBalls counts how many balls left after removing this one
     */

    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(game);
        //death block will remain a listener
    }

    /**
     * @return amount of remaining balls.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }
}
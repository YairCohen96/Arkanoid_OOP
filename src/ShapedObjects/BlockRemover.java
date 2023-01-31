package ShapedObjects;

import Animations.GameLevel;
import DataTrack.Counter;
import DataTrack.HitListener;

/**
 * a ShapedObjects.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * author: Yair Cohen
 * version date: 17/05/22
 */

public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     *Construct a new blockRemover.
     *defines a default frame to be printed within, and default velocity.
     * @param game - game of the block.
     * @param remainingBlocks counts how many blocks left after removing this one
     */

    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBlocks.decrease(1);
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
    }
    /**
     * @return amount of remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
}
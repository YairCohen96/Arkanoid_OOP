package levels;
import Physics.Velocity;
import ShapedObjects.Block;
import ShapedObjects.Sprite;

import java.util.List;

/**
 * Level information defines the info needed for building a new level for a game.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public interface LevelInformation {
    /**
     * @return wished number of balls in the level.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return wished paddle speed in the level.
     */
    int paddleSpeed();
    /**
     * @return wished paddle width in the level.
     */
    int paddleWidth();
    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed
     *     before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();

    /**
     * @return how many rows of blocks needed in this level.
     */
    int numberOfBlockLines();
}
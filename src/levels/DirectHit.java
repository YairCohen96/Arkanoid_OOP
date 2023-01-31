package levels;

import Backgrounds.DirectrHitBackGround;
import Physics.Point;
import Physics.Velocity;
import ShapedObjects.Block;
import ShapedObjects.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * phases.DirectHit class represents the first level of the game in the arkanoid
 * and provide its information.
 * author: Yair Cohen
 * version date: 30/05/22
 */

public class DirectHit implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
         ArrayList velocityList = new ArrayList<Velocity>();
         velocityList.add(Velocity.fromAngleAndSpeed(0, 6));
         return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Level Name: Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectrHitBackGround();
    }

    @Override
    public List<Block> blocks() {
        Block b = new Block(new Point(390, 100), 20, 20, Color.red);
        ArrayList levelBlocks = new ArrayList<Block>();
        levelBlocks.add(b);
        return levelBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
    @Override
    public int numberOfBlockLines() {
        return 1;
    }
}

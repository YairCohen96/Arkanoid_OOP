package levels;

import Backgrounds.WideEsayBackGround;
import Physics.Point;
import Physics.Velocity;
import ShapedObjects.Block;
import ShapedObjects.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Second level of the arkanoid game.
 * author: Yair Cohen
 * version date: 17/05/22
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            int angle = 30;
            velocityList.add(Velocity.fromAngleAndSpeed(angle, 5));
            angle += 20;
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Level Name: Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEsayBackGround();
    }

    @Override
    public List<Block> blocks() {
        ArrayList levelBlocks = new ArrayList<Block>();
        Point left = new Point(20, 300);
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Block b = new Block(left, 50, 20, Color.red);
            levelBlocks.add(b);
            left.setX(left.getX() + 50);
        }
        return levelBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
    @Override
    public int numberOfBlockLines() {
        return 1;
    }
}

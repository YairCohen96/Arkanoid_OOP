package levels;



import Animations.GameLevel;
import Backgrounds.FinalFourBackGround;
import Physics.Point;
import Physics.Velocity;
import ShapedObjects.Block;
import ShapedObjects.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * levels.FinalFour class represents the forth level of the game in the arkanoid
 * and provide its information.
 * author: Yair Cohen
 * version date: 30/05/22
 */

public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 7;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            int angle = 60;
            velocityList.add(Velocity.fromAngleAndSpeed(angle, 5));
        }
        return velocityList;    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Level Name: Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackGround();
    }

    @Override
    public List<Block> blocks() {
        ArrayList levelBlocks = new ArrayList<Block>();
        int blocksInLine = 15;
        Point left = new Point(20, 170);
        for (int i = 0; i < numberOfBlockLines(); i++) {
            for (int j = 0; j < blocksInLine; j++) {
                if (i == 0) {
                    Block b = new Block(left, 50, 20, Color.gray);
                    levelBlocks.add(b);
                    levelBlocks.add(b);
                    left.setX(left.getX() + 50);
                } else {
                    Block b = new Block(left, 50, 20, GameLevel.generateRandomColor());
                    levelBlocks.add(b);
                    left.setX(left.getX() + 50);
                }
            }
            left.setX(20);
            left.setY(left.getY() + 20);
            //blocksInLine--;
        }
        return levelBlocks;    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    @Override
    public int numberOfBlockLines() {
        return 7;
    }
}

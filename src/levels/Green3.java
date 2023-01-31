package levels;



import Animations.GameLevel;
import Backgrounds.Green3BackGround;
import Physics.Point;
import Physics.Velocity;
import ShapedObjects.Block;
import ShapedObjects.Sprite;

import java.util.ArrayList;
import java.util.List;
/**
 * levels.Green3 class represents the third level of the game in the arkanoid
 * and provide its information.
 * author: Yair Cohen
 * version date: 30/05/22
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            int angle = 60;
            velocityList.add(Velocity.fromAngleAndSpeed(angle, 6));
            angle += 90;
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Level Name: Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3BackGround();
    }

    @Override
    public List<Block> blocks() {
        ArrayList levelBlocks = new ArrayList<Block>();
        int blocksInLine = 10;
        Point left = new Point(20, 200);
        for (int i = 0; i < numberOfBlockLines(); i++) {
            for (int j = 0; j < blocksInLine; j++) {
                Block b = new Block(left, 60, 20, GameLevel.generateRandomColor());
                levelBlocks.add(b);
                left.setX(left.getX() + 60);
            }
            left.setX(20);
            left.setY(left.getY() + 20);
            blocksInLine--;
        }
        return levelBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    @Override
    public int numberOfBlockLines() {
        return 5;
    }
}

package Animations;

import DataTrack.Counter;
import DataTrack.ScoreIndicator;
import DataTrack.ScoreTrackingListener;
import Flow.AnimationRunner;
import Flow.GameEnvironment;
import Physics.Point;
import ShapedObjects.Ball;
import ShapedObjects.BallRemover;
import ShapedObjects.Block;
import ShapedObjects.BlockRemover;
import ShapedObjects.Collidable;
import ShapedObjects.Paddle;
import ShapedObjects.Sprite;
import ShapedObjects.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import levels.LevelIndicator;
import levels.LevelInformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/**
 * Game class creates a new game in the Arkanoid.
 * The class will initialize and hold all data related to the game, and run it.
 * author: Yair Cohen
 * version date: 17/05/22
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
    private ArrayList<Ball> balls;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrack;
    private ScoreIndicator indicator;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private LevelIndicator levelName;
    private static final int BLOCKS_NUMBER = 57;
    private static final int BLOCK_WIDTH = 50;
    private static final double BLOCK_HEIGHT = 20.3;
    /**
     * Consructor of class, creates a new game.
     * initializes the data-lists for all objects in the game.
     * @param info - give us the details about the level to build currently.
     * @param gui - the screen to be played on
     */
    public GameLevel(LevelInformation info, GUI gui) {
        level = info;
        this.gui = gui;
        sprites = new SpriteCollection(new ArrayList<>());
        environment = new GameEnvironment(new ArrayList<>());
        balls = new ArrayList<>();
        remainingBlocks = new Counter(level.numberOfBlocksToRemove());
        remainingBalls = new Counter(level.numberOfBalls());
        score = new Counter();
        blockRemover = new BlockRemover(this, remainingBlocks);
        ballRemover = new BallRemover(this, remainingBalls);
        scoreTrack = new ScoreTrackingListener(score);
        indicator = new ScoreIndicator(score);
        runner = new AnimationRunner(this.gui);
        keyboard = gui.getKeyboardSensor();
        levelName = new LevelIndicator(info.levelName());
        //running = blockRemover.getRemainingBlocks().getValue() > 0 && ballRemover.getRemainingBalls().getValue() > 0;
    }
    /**
     * @return the amount of remaining balls in the game.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }
    /**
     * @return the current level of the game that being run.
     */
    public LevelInformation getLevel() {
        return level;
    }
    /**
     * Return the GUI window of the game.
     * @return the GUI window of the game.
     */
    public GUI getGui() {
        return gui;
    }
    /**
     * @return the amount of remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * Return the game environment - the list of collidable objects
     * in the game.
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }
    /**
     * @return the current score of the player.
     */
    public ScoreTrackingListener getScoreTrack() {
        return scoreTrack;
    }
    /**
     * Adds a new object for potential collisions in a given game.
     * @param c the new object to be added.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a new object that can be drawn to the screen, to a game.
     * @param s the new object to be added
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * Removes an object that can be drawn to the screen, to a game.
     * @param s the new object to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
    /**
     * Removes an object for potential collisions in a given game.
     * @param c the new object to be added.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
        for (Ball b: balls) {
            b.setEnvironment(environment);
        }
    }
    /**
     * @return the sprites of the current game.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Adds a play ball to the game.
     * @param b the ball to be added.
     */
    public void addBall(Ball b) {
        this.balls.add(b);
        sprites.addSprite(b);
    }
    /**
     * Removes a play ball to the game.
     * @param b the ball to be added.
     */
    public void removeBall(Ball b) {
        this.balls.remove(b);
        sprites.removeSprite(b);
        //remainingBalls.decrease(1);
    }

    /**
     * Adds the paddle for the user to play with.
     * @param p - the paddle.
     */
    public void addPaddle(Paddle p) {
        this.addSprite(p);
        this.addCollidable(p);
    }

    /**
     * Initializes a new game - creates the Blocks and ShapedObjects.Ball (and GameObjects.ShapedObjects.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        setEnvironment();
        Point start = new Point(400, 400);
        for (int i = 0; i < this.remainingBalls.getValue(); i++) {
            Ball b = new Ball(start, 5, generateRandomColor(), this.environment.getCollidables());
            b.setVelocity(level.initialBallVelocities().get(i).getDx(), level.initialBallVelocities().get(i).getDy());
            b.addToGame(this);
            start.setX(start.getX() + 25);
            start.setY(start.getY() + 15);
        }
        //remainingBalls = new DataTrack.Counter(balls.size());
        ballRemover = new BallRemover(this, remainingBalls);
        indicator.addToGame(this);
        levelName.addToGame(this);
    }

    /**
     * Sets the environment of a game to be ready to be played.
     * Setts the lines of blocks and a new paddle for player.
     */
    public void setEnvironment() {
        this.addSprite(level.getBackground());
        setFrameBlocks();
        Paddle paddle = new Paddle(this);
        paddle.setWidth(level.paddleWidth());
        this.addPaddle(paddle);
        double yLocation = 200;
        Color lineColor;
        int lineLength = 15;
        for (int i = 0; i < level.blocks().size(); i++) {
            Block b = level.blocks().get(i);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrack);
            b.addToGame(this);
        }
    }

    private void setLineOfBlocks(int lineLength, double yLocation, Color lineColor) {
        //Physics.Point upperLeft = new Physics.Point(BLOCK_HEIGHT, yLocation);
        //DataTrack.PrintingHitListener print  = new DataTrack.PrintingHitListener();
        for (int i = 0; i < lineLength && i < level.numberOfBlocksToRemove(); i++) {
            /*ShapedObjects.Block b = new ShapedObjects.Block(upperLeft, BLOCK_WIDTH, BLOCK_HEIGHT, lineColor);
            upperLeft.setX(upperLeft.getX() + BLOCK_WIDTH);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrack);
            //b.addHitListener(print);
            b.addToGame(this);*/
            Block b = level.blocks().get(i);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrack);
            b.addToGame(this);
        }
    }

    private void setFrameBlocks() {

        Block leftWall = new Block(new Point(0, 0), 20, 600, Color.gray);
        leftWall.addToGame(this);
        Block rightWall = new Block(new Point(780, 0), 20, 600, Color.gray);
        rightWall.addToGame(this);
        /*ShapedObjects.Block bottom = new ShapedObjects.Block(new Physics.Point(20, 580), 780, 20, Color.gray);
        bottom.addToGame(this);*/
        Block deathBlock = new Block(new Point(20, 600), 780, 20, Color.red);
        deathBlock.addHitListener(ballRemover);
        deathBlock.addToGame(this);
        Block scoreBlock = new Block(new Point(20, 0), 760, 20, Color.white);
        scoreBlock.addToGame(this);
        Block top = new Block(new Point(20, 20), 760, 20, Color.gray);
        top.addToGame(this);
    }

    /**
     * Run the game level -- start the animation loop.
     */
    public void run() {
        //this.runner.run(new Animations.CountdownAnimation(3, 3, this.sprites));
        Sleeper sleeper = new Sleeper();
        sleeper.sleepFor(670);
        this.running = true;
        this.runner.run(this);
        /*int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (blockRemover.getRemainingBlocks().getValue() > 0 && ballRemover.getRemainingBalls().getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            /*this.sprites.notifyAllTimePassed();
            if (blockRemover.getRemainingBlocks().getValue() == 0) {
                score.increase(100);
            }
            this.sprites.drawAllOn(d);

            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }*/
        sleeper.sleepFor(500);
        //gui.close();
    }
    /**
     * Method generates a random color, for blocks, balls, etc.
     * @return the random color generated.
     */
    public static Color generateRandomColor() {
        Random rand = new Random();
        int c1 = rand.nextInt(255) + 1;  // get integer for RGB COLOR
        int c2 = rand.nextInt(255) + 1;  // get integer for RGB COLOR
        int c3 = rand.nextInt(255) + 1;  // get integer for RGB COLOR
        return new Color(c1, c2, c3);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")
            || this.keyboard.isPressed("פ")) { // small/capital hebrew letter of same button
            PauseScreen pauseScreen = new PauseScreen(this.keyboard);
            KeyPressStoppableAnimation spaceStop;
            spaceStop = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, pauseScreen);
            this.runner.run(spaceStop);
        }
        if (remainingBlocks.getValue() == 0) {
            score.increase(100);
            remainingBlocks.decrease(1);
        }
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
    }

    @Override
    public boolean shouldStop() {
        return !(remainingBlocks.getValue() >= 0 && remainingBalls.getValue() > 0);
    }
} // end of class
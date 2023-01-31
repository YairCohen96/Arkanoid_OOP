package ShapedObjects;

import Animations.GameLevel;
import Physics.Line;
import Physics.Point;
import Physics.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * GameObjects.ShapedObjects.Paddle class creates a new paddle for a game in the Arkanoid.
 * author: Yair Cohen
 * version date: 12/04/22
 */

public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Block paddleBlock;
    private double width;
    private final double speed = 5;
    private static final int HEIGHT = 15;
    private static final Color COLOR = Color.pink;

    /**
     * Constructor for a new paddle.
     * @param game - the game that the new paddle will be part of.
     */
    public Paddle(GameLevel game) {
        width = game.getLevel().paddleWidth();
        paddleBlock = new Block(new Point(360, 565), width, HEIGHT, COLOR);
        keyboard = game.getGui().getKeyboardSensor();
    }

    /**
     * Moves the paddle left if player presses left key on keyboard
     * and the paddle has not reached the left edge of the araknoid.
     */
    public void moveLeft() {
        if (this.getCollisionRectangle().getUpperLeft().getX() <= 20 + speed) {
            Point left = new Point(20, 565);
            paddleBlock = new Block(left, width, HEIGHT, COLOR); // reached the frame
        } else { // move x pixels left - x is the selected speed
            double newX = getCollisionRectangle().getUpperLeft().getX() - speed;
            Point left = new Point(newX, getCollisionRectangle().getUpperLeft().getY());
            paddleBlock = new Block(left, width, HEIGHT, COLOR);
        }
    }
    /**
     * Moves the paddle right if player presses left key on keyboard
     * and the paddle has not reached the right edge of the araknoid.
     */
    public void moveRight() {
        if (getCollisionRectangle().getUpperRight().getX() >= 780 - speed) {
            Point left = new Point(780 - width, 565);
            paddleBlock = new Block(left, width, HEIGHT, COLOR); // reached the frame
        } else { // move x pixels right - x is the selected speed
            double newX = getCollisionRectangle().getUpperLeft().getX() + speed;
            Point left = new Point(newX, getCollisionRectangle().getUpperLeft().getY());
            paddleBlock = new Block(left, width, HEIGHT, COLOR);
        }
    }

    /**
     * Draws a paddle on a given canvas.
     * @param d - the canvas.
     */
    @Override
    public void drawOn(DrawSurface d) {
        paddleBlock.drawOn(d);
    }

    /**
     * Lets the paddle know (as a sprite) that a step has ended in the game,
     * move left or right according to player choice.
     */
    // GameInterfaces.ShapedObjects.Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Returns the paddles shape of rectangle.
     * @return the paddles block's rectangle.
     */
    // GameInterfaces.ShapedObjects.Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return paddleBlock.getCollisionRectangle();
    }

    /**
     * Lets the paddle know that an object hit it, and change this object according
     * to exact location of hitting the paddle.
     * @param collisionPoint - the location of hit.
     * @param currentVelocity - the object's velocity before impact of hitting the paddle.
     * @return new velocity for object - after hit
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        //vertex check
        if (collisionPoint.equals(getCollisionRectangle().getUpperLeft())
            || collisionPoint.equals(getCollisionRectangle().getUpperRight())
            || collisionPoint.equals(getCollisionRectangle().getLowerLeft())
            || collisionPoint.equals(getCollisionRectangle().getLowerRight())) {
            return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if (paddleBlock.getCollisionRectangle().getLeftHeight().hitLine(collisionPoint)) {
            return  paddleBlock.hit(collisionPoint, currentVelocity, hitter);
        } else if (paddleBlock.getCollisionRectangle().getRightHeight().hitLine(collisionPoint)) {
            return paddleBlock.hit(collisionPoint, currentVelocity, hitter);
        } else {
            double updateX, updateY;
            Velocity nextPoint = new Velocity(width / 5, 0);
            double angle;
            Point region1End = new Point(nextPoint.applyToPoint(getCollisionRectangle().getUpperLeft()));
            Line region1 = new Line(getCollisionRectangle().getUpperLeft(), region1End);
            Line region2 = new Line(region1.end(), nextPoint.applyToPoint(region1.end()));
            Line region3 = new Line(region2.end(), nextPoint.applyToPoint(region2.end()));
            Line region4 = new Line(region3.end(), nextPoint.applyToPoint(region3.end()));
            Line region5 = new Line(region4.end(), getCollisionRectangle().getUpperRight());
            if (region1.hitLine(collisionPoint)) {
                angle = 300;
                updateX = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDx();
                updateY = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDy();
                currentVelocity = new Velocity(updateX, updateY);
            } else if (region2.hitLine(collisionPoint)) {
                angle = 330;
                updateX = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDx();
                updateY = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDy();
                currentVelocity = new Velocity(updateX, updateY);
            } else if (region3.hitLine(collisionPoint)) {
                angle = 360;
                updateX = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDx();
                updateY = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDy();
                currentVelocity = new Velocity(updateX, updateY);
            } else if (region4.hitLine(collisionPoint)) {
                angle = 30;
                updateX = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDx();
                updateY = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDy();
                currentVelocity = new Velocity(updateX, updateY);
            } else if (region5.hitLine(collisionPoint)) {
                angle = 60;
                updateX = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDx();
                updateY = Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed()).getDy();
                currentVelocity = new Velocity(updateX, updateY);
            }
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     * @param game the game to be added to.
     */
    public void addToGame(GameLevel game) {
        game.addPaddle(this);
    }
    /**
     * Update this paddle width.
     * @param width the updated width
     */
    public void setWidth(double width) {
        this.paddleBlock.setCollisionRectangleWidth(width);
        this.width = width;
    }
} //end of class
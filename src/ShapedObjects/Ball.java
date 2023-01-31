package ShapedObjects;
import Animations.GameLevel;
import Flow.GameEnvironment;
import Physics.CollisionInfo;
import Physics.Line;
import Physics.Point;
import Physics.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/**
 * ShapedObjects.Ball class represents a ball to be printed on a canvas.
 * The class controls the balls location, color and size within a given frame.
 * author: Yair Cohen
 * version date: 12/04/22
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Velocity v;
    private java.awt.Color color;
    //private Physics.Point frame; // x - frame origin, y - frame bound
    private GameEnvironment environment;
    private GameLevel game;
    //private GameChangers.Physics.CollisionInfo lastCollision;

    // constructor
    /**
     *Construct a new ball.
     *defines a default frame to be printed within, and default velocity.
     *@param center - center point of the new ball
     *@param r - size-radius of the ball
     * @param color - color of the ball when printed
     * @param collidables list of collidables objects in the ball's game environment
     */
    public Ball(Point center, int r, java.awt.Color color, ArrayList collidables) {
        this.center = new Point(center.getX(), center.getY());
        radius = r;
        this.color = color;
        v = new Velocity(3.33, -3.33);
      //  frame = new Physics.Point(200, 200);
       this.environment = new GameEnvironment(collidables);
    }
    /**
     *Construct a new ball.
     *defines a default frame to be printed within, and default velocity.
     *@param centerX - x value of center point of the new ball
     *@param centerY - y value of center point of the new ball
     *@param r - size-radius of the ball
     * @param color - color of the ball when printed
     */
    public Ball(int centerX, int centerY, int r, java.awt.Color color) {
        center = new Point(centerX, centerY);
        radius = r;
        this.color = color;
        v = new Velocity(5, 5);
        //frame = new Physics.Point(0, 200); // default frame of 200x200
    }

    /**
     *Copy constructor for ShapedObjects.Ball class.
     *copies all values of another ball.
     *@param other - other ball to be copied
     */
    public Ball(Ball other) {
        this.center = other.getCenter();
        this.radius = other.getSize();
        this.color = other.getColor();
        v = new Velocity(5, 5);
        this.environment = new GameEnvironment(other.getEnvironment().getCollidables());
       // this.frame = new Physics.Point(other.getFrame());
    }

    // getters
    /**
     * provides the value of x coordinate of the center point.
     *@return the x value of the center point
     */
    public int getX() {
        return (int) center.getX();
    }
    /**
     * provides the value of y coordinate of the center point.
     *@return the y value of the center point
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * provides the radius of the ball.
     *@return the radius
     */
    public int getSize() {
        return radius;
    }
    /**
     * provides the values of the center point.
     *@return the center point
     */
    public Point getCenter() {
        return center;
    }
    /**
     * provides the ball's environment.
     *@return the ball's environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }
    /**
     * provides the color of a ball to be printed.
     *@return the color
     */
    public java.awt.Color getColor() {
        return color;
    }
    /**
     * provides the velocity of a ball - rhythm of movement on screen.
     *@return the velocity
     */
    public Velocity getVelocity() {
        return v;
    }
    /*public Physics.Point getFrame() {
        return frame;
    }*/
    // setters
    /**
     * sets the environment of a ball.
     *@param update the new environment
     */
    public void setEnvironment(GameEnvironment update) {
        environment = update;
    }
    /**
     * sets the velocity of a ball - rhythm of movement on screen.
     *@param dx the new velocity on x-axis
     *@param dy the new velocity on y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.v.setDx(dx);
        this.v.setDy(dy);
    }

    /**
     * sets ball's color.
     * @param color the new color
     */
    public void  setColor(Color color) {
        this.color = color;
    }
    /**
     * sets the velocity of a ball - rhythm of movement on screen.
     *@param v the new velocity to be copied
     */
    public void setVelocity(Velocity v) {
        this.v = new Velocity(v.getDx(), v.getDy());
    }
    /*public void setFrame(double origin, double bound) {
        frame.setX(origin);
        frame.setY(bound);
    }*/
    /**
     *Sets the radius of the ball.
     *@param size - the new radius
     */
    public void setSize(int size) {
        radius = size;
    }
    /**
     * Sets the values of the center point.
     *@param x the new x value for center point
     *@param y the new y value for center point
     */
    public void setCenter(double x, double y) {
        center.setX(x);
        center.setY(y);
    }
    /**
     * print the ball to v certain canvas.
     *@param surface the canvas for the ball to be drawn on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }
    /**
     * Let the ball know that "one step" in the game is complete.
     * The ball will move one step ahead
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Method will change the balls location on canvas according to its velocity,
     * and potential collitions on its way.
     */
    public void moveOneStep() {
        CollisionInfo info = new CollisionInfo(environment.getClosestCollision(trajectory()).collisionPoint(),
                environment.getClosestCollision(trajectory()).collisionObject());
        if (info != null) {
            Point collision = new Point(info.collisionPoint());
            Rectangle collisionRect = new Rectangle(info.collisionObject().getCollisionRectangle());
            setVelocity(environment.getClosestCollision(trajectory()).collisionObject().
                    hit(collision, getVelocity(), this));
            center = moveAfterCollision(collisionRect, collision);
        }
        // update location according to right direction in any case
        this.center = this.getVelocity().applyToPoint(this.getCenter());
        // if ball somehow got inside a block - get it back out to the field
        getOutOfHere(getEnvironment());
    }
    // get to "almost" the collision point
    private Point moveAfterCollision(Rectangle collisionRect, Point collisionP) {
        if (collisionRect.getUpperWidth().hitLine(collisionP)) {
            return new Point(getX(), (int) collisionP.getY() - 1);
        } else if (collisionRect.getLowerWidth().hitLine(collisionP)) {
            return new Point(getX(), (int) collisionP.getY() + 1);
        }
        if (collisionRect.getLeftHeight().hitLine(collisionP)) {
            return new Point((int) collisionP.getX() - 1, getY());
        } else if (collisionRect.getRightHeight().hitLine(collisionP)) {
            return new Point((int) collisionP.getX() + 1, getY());
        } else {
            return getCenter();
        }
    }
    // if ball somehow got inside a block - the method gets it out
    private void getOutOfHere(GameEnvironment environment) {
        //check if ball is inside the rectangle
        for (int i = 0; i < environment.getCollidables().size(); i++) {
            Rectangle rect = environment.getRectangle(i);
            if (getCenter().isLeft(rect.getUpperRight()) && getCenter().isRight(rect.getUpperLeft())
                    && getCenter().isUnder(rect.getLowerLeft()) && getCenter().isAbove(rect.getUpperLeft())) {
                if (i == 0) { // left frame block
                    setCenter(getCenter().getX() + 7, getCenter().getY());
                    setVelocity(getVelocity().getDx() * -1, -1 * getVelocity().getDy());
                } else if (i == 1) { // right frame block
                    setCenter(getCenter().getX() - 7, getCenter().getY());
                    setVelocity(getVelocity().getDx() * -1, -1 * getVelocity().getDy());
                } else if (i == 2) { // bottom
                    setCenter(getCenter().getX(), getCenter().getY() - 7);
                    setVelocity(-1 * getVelocity().getDx(), -1 * getVelocity().getDy());
                } else if (i == 4) { //top
                    setCenter(getCenter().getX(), getCenter().getY() + 7);
                    setVelocity(-1 * getVelocity().getDx(), -1 * getVelocity().getDy());
                } else if (i == environment.getCollidables().size() - 1) { //paddle
                    setVelocity(getVelocity().getDx() * -1, -1 * getVelocity().getDy());
                    setCenter(getCenter().getX(), getCenter().getY() - 4);
                /*else if (i >= 5 && i <= 14) { //first line
                    setCenter(rect.getUpperWidth().end().getX() + 1, rect.getUpperWidth().end().getY() + 2);
                    setVelocity(getVelocity().getDx(), getVelocity().getDy());
                }*/ } else { // lines of blocks
                    setCenter(rect.getLowerWidth().end().getX() + 1, rect.getLowerWidth().end().getY() + 2);
                    setVelocity(-1 * getVelocity().getDx(), -1 * getVelocity().getDy());
                }
            }
        }
    }
    /**
     * Method will change the balls velocity according to its size, so that for a group of balls,
     * a bigger ball will be slower (preserving proportions between all balls).
     * @param modifier - a constant number responsible for proportion between all balls size and speed
     * @return the modified velocity
     */
    public Velocity speedBySize(int modifier) { // update the speed with proportion to modifier
        double vUpdate = (double) modifier / this.getSize();
        return new Velocity(vUpdate, vUpdate);
    }
    /**
     *Generates a new ball with random size, location and color, within a given frame.
     * @param origin start range of the frame
     * @param bound end range of the frame
     *@return the new ball
     */
    public static Ball generateRandomBall(int origin, int bound) { //bounds for frame for the ball to be in
        Random rand = new Random();
        int r = rand.nextInt((bound - origin) / 2); //limit ball size to middle value within the frame
        int centerX = rand.nextInt(bound - r) + origin + r; //get integer in range 50-500
        int centerY = rand.nextInt(bound - r) + origin + r; // get integer in range 50-500
        int c1 = rand.nextInt(255) + 1;  // get integer for RGB COLOR
        int c2 = rand.nextInt(255) + 1;  // get integer for RGB COLOR
        int c3 = rand.nextInt(255) + 1;  // get integer for RGB COLOR
        Color color = new Color(c1, c2, c3);
        if (r > centerX - origin || r > bound - centerX) { // x coordinate makes the ball's edges start out of frame
            centerX = origin + r; // move centerX so that ball will be in frame
        }
        if (r > centerY - origin || r > bound - centerY) { // y coordinate makes the ball's edges start out of frame
            centerX = origin + r; // move centerY so that ball will be in frame
        }
        Ball randBall = new Ball(centerX, centerY, r, color);
        //randBall.setFrame(origin, bound);
        return randBall;
    }
    /**
     *Method checks that a size given by the user for a ball is indeed legal within a given frame.
     * if a random starting point in to close to frame for the selected radius, the point will change
     * so that ball will be in frame as needed.
     * @param selectedSize - radius selected by user
     * @param origin start range of the frame
     * @param bound end range of the frame
     */
    public void checkRadius(int selectedSize, int origin, int bound) {
        if (selectedSize > this.getX() - origin || selectedSize > bound - this.getX()) {
            // x coordinate makes the ball's edges start out of frame
            this.getCenter().setX(origin + selectedSize); // move centerX so that ball will be in frame
        }
        if (selectedSize > this.getY() - origin || selectedSize > bound - this.getY()) {
            // Y coordinate makes the ball's edges start out of frame
            this.getCenter().setY(origin + selectedSize); // move centerY so that ball will be in frame
        }
    }
    /**
     *Method will modify location of ball on a canvas and its size if needed,
     * when a selected radius, with a random starting location is going out of frame.
     * if radius is bigger than frame - ball will go to center of frame with radius
     * filling the frame. (using also the checkRadius method)
     * @param selectedSize - radius selected by user
     * @param origin start range of the frame
     * @param bound end range of the frame
     */
    public void modifyBall(int selectedSize, int origin, int bound) {
        if (selectedSize > (bound - origin) / 2) { // radius is out of range
            this.setCenter((double) (bound - origin) / 2, (double) (bound - origin) / 2);
            // modify the ball to be maximal size within frame
            selectedSize = (bound - origin) / 2 - 2;
        }
        /* make sure updated radius is legal with the point center generated
           even if not bigger than the frame range itself.*/
        this.checkRadius(selectedSize, origin, bound);
        this.setSize(selectedSize);
    }
    /**
     *Method will generate a line that represents the ball's movement lane for next step.
     * @return the ball's predicted movement for next step
     */
    public Line trajectory() {
        Velocity hitV = new Velocity(getVelocity().getDx(), getVelocity().getDy());
        // consider trajectory as longer in order to change direction "almost" at collision point
        return new Line(getCenter(), hitV.applyToPoint(getCenter()));
    }
    /**
     *Method will add the ball to database of a given game as the play ball.
     * @param game the game to be added to.
     */
    public void addToGame(GameLevel game) {
        game.addBall(this);
        this.game = game;
    }
    /**
     *Method will remove the ball to database of a given game as the play ball.
     * @param game the game to be added to.
     */
    public void removeFromGame(GameLevel game) {
        game.removeBall(this);
        this.game = null;
    }
} // end of class ShapedObjects.Ball



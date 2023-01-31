package ShapedObjects;

import Animations.GameLevel;
import DataTrack.HitListener;
import DataTrack.HitNotifier;
import Physics.Point;
import Physics.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * ShapedObjects.Block class creates a new block for a game in the Arkanoid.
 * author: Yair Cohen
 * version date: 12/04/22
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle collisionRectangle;
    private java.awt.Color color;
    private ArrayList<HitListener> hitListeners;

    /**
     * Constructor of class.
     * creates a new rectangle for the block with the following data:
     * @param upperLeft - upper left vertex of the block's rectangle
     * @param width - width of rectangle
     * @param height - height of rectangle
     * @param color - color of the block when drawn on canvas
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        collisionRectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     * Copies a rectangle to the new block, with a new color.
     * @param rect - the rectangle to be copied.
     * @param color - color of new block.
     */
    public Block(Rectangle rect, Color color) {
        this.collisionRectangle = new Rectangle(rect);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    //getters
    @Override
    /**
      Return the rectangle of the block when an object collided with it.
      @return the rectangle of the block.
     */
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }
    /**
     * Return the color of the block.
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Notify the block that we collided with it at collision Physics.Point with
     * a given velocity.
     * @param collisionPoint - location of collision
     * @param currentVelocity the object's velocity before hitting block
     * @return the new velocity expected after the hit (based on
     *     the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
       // if (collisionPoint.distance(hitter.getCenter()) <= hitter.getSize() + 1) {
            //check vertex collision separately
            if (collisionPoint.equals(getCollisionRectangle().getUpperLeft())
                    || collisionPoint.equals(getCollisionRectangle().getUpperRight())
                    || collisionPoint.equals(getCollisionRectangle().getLowerLeft())
                    || collisionPoint.equals(getCollisionRectangle().getLowerRight())) {
                notifyHit(hitter);
                return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
            }
            if (this.getCollisionRectangle().getLeftHeight().hitLine(collisionPoint)) { //check horizontal direction
                notifyHit(hitter);
                currentVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            } else if (this.getCollisionRectangle().getRightHeight().hitLine(collisionPoint)) {
                notifyHit(hitter);
                currentVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            } else if (getCollisionRectangle().getUpperWidth().hitLine(collisionPoint)) { //check vertical direction
                notifyHit(hitter);
                currentVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            } else if (this.getCollisionRectangle().getLowerWidth().hitLine(collisionPoint)) {
                notifyHit(hitter);
                currentVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            }
        //}
        return currentVelocity;
    }
    /**
     * Draw the block to the screen.
     * @param surface the canvas on the screen to be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        int x = (int) collisionRectangle.getUpperLeft().getX();
        int y = (int) collisionRectangle.getUpperLeft().getY();
        int width = (int) collisionRectangle.getWidth();
        int height = (int) collisionRectangle.getHeight();
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, height);
    }

    @Override
    /**
     * Notify the block that time has passed.
     * the block will change some of its data accordingly if needed.
     * (currently - do nothing)
     */
    public void timePassed() {
            }

    @Override
    /**
     * Add block to a game.
     * @param game the game to be added to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
    /**
     * Remove block from a game.
     * @param game the game to be added to
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Updates the width of a block's rectangle.
     * @param width the updated width
     */
    public void setCollisionRectangleWidth(double width) {
        this.collisionRectangle.setWidth(width);
    }
}

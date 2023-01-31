package ShapedObjects;// Yair Cohen 313355786

import Physics.Point;
import Physics.Velocity;
import ShapedObjects.Ball;

/**
 * GameInterfaces.ShapedObjects.Collidable interface defines a type of objects in a game, that can be collided with by the game ball,
 * or in general.
 * author: Yair Cohen
 * version date: 12/04/22
 */

public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return the rectangle of the collidable object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collision Physics.Point with
     * a given velocity.
     * @param collisionPoint - location of collision
     * @param currentVelocity the objects velocity before hitting collidable object
     * @param hitter the object that hit the collidable object
     * @return the new velocity expected after the hit (based on
     *     the force the object inflicted on us).
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
} //end of interface
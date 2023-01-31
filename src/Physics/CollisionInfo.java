package Physics;// Yair Cohen 313355786

import Physics.Point;
import ShapedObjects.Collidable;

/**
 * GameChangers.Physics.CollisionInfo class provides information for any collision of
 * an object with other collidables on canvas - point of collision and
 * the object it collided with.
 * author: Yair Cohen
 * version date: 12/04/22
 */
public class CollisionInfo {
    private Collidable collisionObject;
    private Point collisionPoint;

    /**
     * Constructor for class objects.
     * @param collisionPoint - point of collision to be stored
     * @param collisionObject - object collided with
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = new Point(collisionPoint);
    }

    //getters
    /**
     * Provides the point at which the collision occurs.
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * Provides the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
} // end of class
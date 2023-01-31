package Flow;

import Physics.CollisionInfo;
import Physics.Line;
import Physics.Point;
import ShapedObjects.Collidable;
import ShapedObjects.Rectangle;

import java.util.ArrayList;
/**
 * GameChangers.Flow.GameEnvironment class creates a new environment for a game in the Arkanoid.
 * The environment made of list of collidable objects, for the ball to potentially
 * collied with.
 * author: Yair Cohen
 * version date: 12/04/22
 */

public class GameEnvironment {
    private ArrayList<Collidable> collidables;

    /**
     * Constructor for the class.
     * @param collidables - list of objects to be held as a list
     * of collidables objects in the environment.
     */
    public GameEnvironment(ArrayList collidables) {
        this.collidables = new ArrayList<>(collidables);
    }

    /**
     * Add the given collidable to the environment.
     * @param c the collidable object to be added
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * Remove the given collidable to the environment.
     * @param c the collidable object to be added
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Returns a list of all collidable objects in the environment.
     * @return the list of the collidables
     */
    public ArrayList getCollidables() {
        return collidables;
    }
    /**
     * Assume an object moving from line start to end.
     * If this object will not collide with any of the collidables
     * in this list, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line of movement of the object
     * @return the info of collision - point of collision and object collided with
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int closetsCollidableIndex = 0;
        Point closest = new Point(trajectory.end());
        for (int i = 0; i < collidables.size(); i++) {
            Point curr;
            curr = new Point(trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle()));
            if (trajectory.start().distance(curr) < trajectory.start().distance(closest)) {
                closest = new Point(curr);
                closetsCollidableIndex = i;
            }
        }
        return new CollisionInfo(closest, collidables.get(closetsCollidableIndex));
    }

    /**
     * Return the rectangle of a collidable object in the list
     * in the game environment.
     * @param i index of the object in the list
     * @return its rectangle
     */
    public Rectangle getRectangle(int i) {
        return collidables.get(i).getCollisionRectangle();
    }
} //end of class
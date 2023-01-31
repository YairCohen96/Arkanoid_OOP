package Physics;//Yair Cohen 313355786

/**
 * Physics.Velocity specifies the change in position on the `x` and the `y` axes.
 * author: Yair Cohen
 * version date: 12/04/22
 */
public class Velocity {
    private  double dx, dy;
    // constructor
    /**
     *Construct a new line between to given points.
     *@param dx - change in position on the `x`-axis
     *@param dy - change in position on the `y`-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    /**
     *Changes position of the object according to its velocity.
     * @param p - the old position to be updated
     * @return the new location
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.getDx();
        double newY = p.getY() + this.getDy();
        return (new Point(newX, newY));
    }
    //getters
    /**
     *provides the velocity on the x-axis.
     * @return dx - the position changing factor.
     */
    public double getDx() {
        return dx;
    }
    /**
     *provides the velocity on the y-axis.
     * @return dy - the position changing factor.
     */
    public double getDy() {
        return dy;
    }
    //setters
    /**
     * sets new velocity on the x-axis.
     * @param newDx - the new position changing factor.
     */
    public void setDx(double newDx) {
        dx = newDx;
    }
    /**
     * sets new velocity on the y-axis.
     * @param newDy - the new position changing factor.
     */
    public void setDy(double newDy) {
        dy = newDy;
    }
    /**
     * sets velocity according to direction given by angle and wanted speed.
     * @param angle - the direction for the object to head
     * @param speed - the wanted speed to be applied
     * @return velocity on each axis modified according to the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -1 * speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
    /**
     * Gets an object general speed from its velocity.
     * @return the speed
     */
    public double getSpeed() {
        double xFactor = this.getDx() * this.getDx();
        double yFactor = this.getDy() * this.getDy();
        return Math.sqrt(xFactor + yFactor);
    }
} //end of class

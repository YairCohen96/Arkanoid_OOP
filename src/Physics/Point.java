package Physics;

/**
 * Physics.Point class presents a point in a cartesian system.
 * The class is also capable of changing the given point's value, and present
 * the new (x,y) values in this form.
 * author: Yair Cohen
 * version date: 24/03/22
 */

public class Point {
    private double x, y; //variables declaration for a point object
    private static final double EPSILON = Math.pow(10, -10); //constant number - to determine equality of real numbers
    // constructors

    /**
     *Construct a new point.
     *@param x - the x coordinate of the point
     *@param y - the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     *Copy constructor for a new point.
     *@param other - the point to be copied
     */
    public Point(Point other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    /**
     *measures the distance of this point to the other point according to the formula:
     *square root of: ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)).
     *@param other - other point to measure the distance from
     * @return the distance between the points
     */
    public double distance(Point other) {
        double xDistance = Math.pow((x - other.getX()), 2);
        double yDistance = Math.pow((y - other.getY()), 2);
        return Math.sqrt(xDistance + yDistance);
    }

    /**
     *checks if point coordinates are equal to other point coordinates.
     *@param other - other point to measure the distance from
     *@return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        boolean equalX = equalDoubles(x, other.getX());
        boolean equalY = equalDoubles(y, other.getY());
        return (equalX && equalY);
    }
    // Getters
    /**
     * provides the value of x coordinate of a point.
     *@return the x value of the given point
     */
    public double getX() {
        return x;
    }
    /**
     *provides the value of y coordinate of a point.
     *@return the y value of the given point
     */
    public double getY() {
        return y;
    }
    /**
     *sets the value of x coordinate of a point.
     * @param x the new x value to be inserted
     */
    //setters
    public void setX(double x) {
        this.x = x;
    }
    /**
     *sets the value of y coordinate of a point.
     * @param y the new x value to be inserted
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     *Check if the point is above other point on the cartesian system.
     *@param other - the other point to be compared with the point.
     *@return true if this point's y coordinate is smaller than other point's y coordinate.
     */
    public boolean isAbove(Point other) {
    return (other.getY() < this.getY());
    }
    /**
     *Check if the point is under other point on the cartesian system.
     *@param other - the other point to be compared with the point.
     *@return true if this point's y coordinate is smaller than other point's y coordinate.
     */
    public boolean isUnder(Point other) {
        return other.isAbove(this);
    }
    /**
     *Check if the point is to the left other point on the cartesian system.
     *@param other - the other point to be compared with the point.
     *@return true if this point's x coordinate is bigger than other point's x coordinate.
     */
    public boolean isLeft(Point other) {
        return (other.getX() > this.getX());
    }
    /**
     *Check if the point is to the right other point on the cartesian system.
     *@param other - the other point to be compared with the point.
     *@return true if this point's x coordinate is smaller than other point's x coordinate.
     */
    public boolean isRight(Point other) {
        return other.isLeft(this);
    }
    /**
     *determines if two real numbers' difference is less 10^(-10).
     * if so - the program will consider them as equals
     *@param a - first number
     *@param b - second number
     *@return true if the difference is less 10^(-10), false otherwise
     */
    private boolean equalDoubles(double a, double b) { // allowing access for other classes in the package as well
        return (Math.abs(a - b) <= EPSILON); // if coordinates are "close enough" - consider them equal
    }

} //end of class Physics.Point
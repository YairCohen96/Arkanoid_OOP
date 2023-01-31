package Physics;
import ShapedObjects.Rectangle;
import biuoop.DrawSurface;
import java.util.Random;
/**
 * Physics.Line class represents a line in a cartesian system - a final segment of a line.
 * The class can also compare locations of lines to each other,
 * and determine if they are equal or intersecting within a given frame.
 * author: Yair Cohen
 * version date: 12/04/22
 */
public class Line {
    //variables declaration for a line object
    private final Point start;
    private final Point end;
    private static final double EPSILON = Math.pow(10, -10); //constant number - to determine equality of real numbers

    // constructors
    /**
     *Construct a new line between to given points.
     *@param start - location  of left point of the line in the frame
     *@param end - location  of right point of the line in the frame
     */
    public Line(Point start, Point end) { // defines start as the point with lower x value
       if (end.getX() < start.getX()) {
            this.start = new Point(end.getX(), end.getY());
            this.end = new Point(start.getX(), start.getY());
        } else {
            this.start = new Point(start);
            this.end = new Point(end);
        }

    }
    /**
     *Construct a new line between to given points - according to their coordinates.
     *@param startX - x-axis coordinate of left point of the line in the frame
     *@param startY - y-axis coordinate of left point of the line in the frame
     *@param endX - x-axis coordinate of right point of the line in the frame
     *@param endY - y-axis coordinate of right point of the line in the frame
     */
    public Line(double startX, double startY, double endX, double endY) {
        if (endX < startX) {
            start = new Point(endX, endY);
            end = new Point(startX, startY);
        } else {
            start = new Point(startX, startY);
            end = new Point(endX, endY);
        }
    }

    /**
     *Construct a new line identical to a given line.
     *@param other - line to be copied
     */
    public Line(Line other) {
        this.start = new Point(other.start().getX(), other.start().getY());
        this.end = new Point(other.end().getX(), other.end().getY());
    }
    //getters
    /**
     *provides location of left point of the line on the cartesian system.
     *@return start point of the line
     */
    public Point start() {
        return this.start;
    }
    /**
     *provides location of right point of the line on the cartesian system.
     *@return end point of the line
     */
    public Point end() {
        return this.end;
    }
    /**
     *measures the length of a line.
     *@return the distance between start and end of the line - its length
     */
    public double length() {
        return start.distance(end);
    }
    /**
     *measures the middle point of a line.
     *@return the middle point of the line
     */
    public Point middle() { // returns the coordinates ((x2-x1)/2 , (y2-y1)/2)
        double middleX = (this.end().getX() - this.start().getX()) / 2 + this.start().getX();
        double middleY = (this.end().getY() - this.start().getY()) / 2 + this.start().getY();
        return (new Point(middleX, middleY));
    }
    /**
     *The method determines if two line meet - in one or many points.
     * @param other - the other line to check intersection with
     *@return true if the lines intersect in one point or more, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            //lines might unify
            if (equalDoubles(this.getIncline(), other.getIncline()) && equalDoubles(this.getB(), other.getB())) {
                // does not intersect
                // Then - null more than one intersection point - lines unify
                return !other.start().isRight(this.end()) && !other.end().isLeft(this.start());
            }
        } else { // check if intersection point is indeed in the range between the line's two edges
            //including edges as intersection points
            boolean startEdge = !this.intersectionWith(other).isLeft(this.start());
            boolean endEdge = !this.intersectionWith(other).isRight(this.end());
            return startEdge && endEdge;
        }
        return false; //default for compiling
    }
    /**
     *The method provides a single intersection point between line, if exists.
     * if there are more than one point (line unify or equal) - will return null.
     * @param other - the other line to check point of intersection with
     *@return point of intersection if there is only one, null otherwise
     */
    public Point intersectionWith(Line other) {
        double intersectionX;
        double intersectionY;
        Point intersection;
        if (this.isVertical() && !other.isVertical()) { // this line is vertical, other is not
            intersectionX = this.start().getX();
            intersectionY = intersectionX * other.getIncline() + other.getB();
            return new Point(intersectionX, intersectionY);
        } else if (other.isVertical() && !this.isVertical()) { //other line is vertical, this line is not
            intersectionX = other.start().getX();
            intersectionY = intersectionX * this.getIncline() + this.getB();
            return new Point(intersectionX, intersectionY);
        } else if (this.isVertical() && other.isVertical()) { // both vertical
            if (!equalDoubles(this.start().getX(), other.start().getX())) { // both vertical but parallel
                return null;
            } else { // might unify or meet at edges
                if (this.start().equals(other.end())) { // intersect on edges
                    return other.end();
                } else if (this.end().equals(other.start())) { // intersect on edges
                    return other.start();
                } else { // does not intersect or intersect in many points - including equal lines
                    return null;
                }
            }
        } else if (equalDoubles(this.getIncline(), other.getIncline())) { // not vertical - m1=m2 (exists)
            if (this.getB() != other.getB()) { // lines are parallel
                return null;
            } else { //b1=b2 --> lines unify (at least part of them)
                if (this.start().equals(other.end())) { // intersect on edges
                    return other.end();
                } else if (this.end().equals(other.start())) {
                    return other.start();
                } else { //does not intersect or intersect in many points - including equal lines
                    return null;
                }
            }
        } else { // y=mx+b --> m1x + B1 =? m2x + B2 --> x = (B2-B1)/(m1-m2)
            intersectionX = (other.getB() - this.getB()) / (this.getIncline() - other.getIncline());
            intersectionY = (intersectionX * this.getIncline()) + this.getB();
            intersection = new Point(intersectionX, intersectionY);
        }
        return intersection;
    }
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.

    /**
     * Provides the closest intersection point of a rectangle to the
     * start of the line.
     * @param rect - the rectangle te be checked
     * @return return the closest intersection point, if does not intersect, return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int intersectionsListLength = rect.intersectionPoints(this).size();
        /*if (intersectionsListLength == 0) {
            return null;
        }*/
        Point closest = new Point(this.end());
        for (int i = 0; i < intersectionsListLength; i++) {
            if (rect.intersectionPoints(this).get(i) != null) {
                if (start().distance(rect.intersectionPoints(this).get(i)) < start().distance(closest)) {
                    closest = new Point(rect.intersectionPoints(this).get(i));
                }
            }
        }
        return closest;
    }

    /**
     *The method determines if final segments of lines equal.
     * @param other - the other line to check equality with
     *@return true if equal, false otherwise
     */
    private boolean equals(Line other) {
        return (this.start().equals(other.start()) && this.end().equals(other.end()));
    }
    /**
     *The method provides with a line's incline if exists.
     * A vertical line will never be sent to the method
     *@return the incline
     */
    private double getIncline() { // y2-y1 / x2-x1
        double incline = (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX());
        return incline;
    }
    /**
     *The method provides with a line's point of intersection with the y-axis.
     *@return the intersection point if exists, the Y coordinate of the start point if the line is vertical
     */
    private double getB() { //B = y-mx
        return (this.start().getY() - (this.getIncline() * this.start().getX()));
    }
    /**
     *determines if two real numbers' difference is less 10^(-10).
     * if so - the program will consider them as equals
     *@param a - first number
     *@param b - second number
     *@return true if the difference is less 10^(-10), false otherwise
     */
    public boolean equalDoubles(double a, double b) {
        return (Math.abs(a - b) <= EPSILON); // if coordinates are "close enough" - consider them equal
    }
    /**
     *determines if a line is vertical.
     *@return true if vertical, false otherwise
     */
    public boolean isVertical() { // check if line is vertical
        return  equalDoubles(this.start().getX(), this.end().getX());
    }
    /**
     *Generates a random line on a plane.
     * @param origin start range of the plane
     * @param bound end range of the plane
     *@return the line values
     */
    public static Line generateRandomLine(int origin, int bound) {
        Random rand = new Random();
        int startX = rand.nextInt(bound - origin) + origin;
        int startY = rand.nextInt(bound - origin) + origin;
        int endX = rand.nextInt(bound - origin) + origin;
        int endY = rand.nextInt(bound - origin) + origin;
        Point start = new Point(startX, startY);
        Point end = new Point(endX, endY);
        //check if line is valid - does not start and end in same location
        while (start.equals(end)) {
            endX = rand.nextInt(bound - origin) + origin;
            endY = rand.nextInt(bound - origin) + origin;
            end = new Point(endX, endY);
        }
        return new Line(start, end);
    }

    /**
     * Checks if a certain point is on a segment of a line.
     * @param point the point to be checked
     * @return true if it is, false otherwise
     */
    public boolean hitLine(Point point) {
        return  (equalDoubles(start().distance(point) + end().distance(point), length()));
        //return ((int) (start().distance(collisionPoint) + end().distance(collisionPoint)) == (int) (length()));
    }

    /**
     * Draws a line on a given canvas.
     * @param surface - the canvas.
     */
    public void drawOn(DrawSurface surface) {
        surface.drawLine((int) start().getX(), (int) start().getY(), (int) end().getX(), (int) end().getY());
    }
} //end of class Physics.Line
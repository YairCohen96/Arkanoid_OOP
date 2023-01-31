package ShapedObjects;
import Physics.Line;
import Physics.Point;

import java.util.ArrayList;

/**
 * ShapedObjects.Rectangle class creates rectangle objects and holds its data,
 * such as its vertexes and edges.
 * author: Yair Cohen
 * version date: 12/04/22
 */

public class Rectangle {
    //variables declaration for a rectangle object
    private double height, width;
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private Line upperWidth;
    private Line lowerWidth;
    private Line leftHeight;
    private Line rightHeight;

    /**
     * Constructor of class.
     * create a new rectangle with location and width/height.
     * @param upperLeft - upper left vertex of the new rectangle.
     * @param width - width of rectangle
     * @param height - height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        lowerRight = new Point(upperRight.getX(), upperRight.getY() + height);
        upperWidth = new Line(upperLeft, upperRight);
        lowerWidth = new Line(lowerLeft, lowerRight);
        leftHeight = new Line(upperLeft, lowerLeft);
        rightHeight = new Line(upperRight, lowerRight);
        this.upperLeft = new Point(upperLeft);
    }

    /**
     * Copy constructor.
     * @param other - other rectangle to be copied.
     */
    public Rectangle(Rectangle other) {
        this.height = other.getHeight();
        this.width = other.getWidth();
        this.upperLeft = new Point(other.getUpperLeft());
        this.upperRight = new Point(other.getUpperRight());
        this.lowerLeft = new Point(other.getLowerLeft());
        this.lowerRight = new Point(other.getLowerRight());
        this.upperWidth = new Line(other.getUpperWidth());
        this.lowerWidth = new Line(other.getLowerWidth());
        this.leftHeight = new Line(other.getLeftHeight());
        this.rightHeight = new Line(other.getRightHeight());
    }

    /**
     *  Return a (possibly empty) List of intersection points
     *  with the specified line.
     * @param trajectory - the line to be checked.
     * @return list of intersection points with the line.
     */
    public java.util.List<Point> intersectionPoints(Line trajectory) {
        ArrayList<Point> intersectionsList = new ArrayList<>();
        if (getUpperWidth().isIntersecting(trajectory) && getUpperWidth().intersectionWith(trajectory) != null) {
            intersectionsList.add(getUpperWidth().intersectionWith(trajectory));
        }
        if (getLowerWidth().isIntersecting(trajectory) && getLowerWidth().intersectionWith(trajectory) != null) {
            intersectionsList.add(getLowerWidth().intersectionWith(trajectory));
        }
        if (getLeftHeight().isIntersecting(trajectory) && getLeftHeight().intersectionWith(trajectory) != null) {
            intersectionsList.add(getLeftHeight().intersectionWith(trajectory));
        }
        if (getRightHeight().isIntersecting(trajectory) && getRightHeight().intersectionWith(trajectory) != null) {
            intersectionsList.add(getRightHeight().intersectionWith(trajectory));
        }
        return intersectionsList;
    }
    //getters
    /**
     * Return the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns height of the rectangle.
     * @return height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns upper right vertex of the rectangle.
     * @return upper right vertex of the rectangle.
     */
    public Point getUpperRight() {
        return upperRight;
    }
    /**
     * Returns upper left vertex of the rectangle.
     * @return upper left vertex of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * Returns lower left vertex of the rectangle.
     * @return lower left vertex of the rectangle.
     */
    public Point getLowerLeft() {
        return lowerLeft;
    }
    /**
     * Returns lower right vertex of the rectangle.
     * @return lower right vertex of the rectangle.
     */
    public Point getLowerRight() {
        return lowerRight;
    }
    /**
     * Returns upper edge (width) of the rectangle.
     * @return upper edge vertex of the rectangle.
     */
    public Line getUpperWidth() {
        return upperWidth;
    }
    /**
     * Returns right edge (height) of the rectangle.
     * @return right edge (height) of the rectangle.
     */
    public Line getRightHeight() {
        return rightHeight;
    }
    /**
     * Returns lower edge (width) of the rectangle.
     * @return lower edge of the rectangle.
     */
    public Line getLowerWidth() {
        return lowerWidth;
    }
    /**
     * Returns left edge (height) of the rectangle.
     * @return left edge of the rectangle.
     */
    public Line getLeftHeight() {
        return leftHeight;
    }

    /**
     * Update the width of a rectangle.
     * @param width the updated width
     */
    public void setWidth(double width) {
        this.width = width;
    }
} //end of class
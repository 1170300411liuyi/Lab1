/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
	public static final double TOLERANCE = 1e-5;
    public static void drawSquare(Turtle turtle, int sideLength) {
       
    	turtle.forward(sideLength);
        turtle.turn(90.00);
        turtle.forward(sideLength);
        turtle.turn(90.00);
        turtle.forward(sideLength);
        turtle.turn(90.00);
        turtle.forward(sideLength);
        turtle.turn(90.00);
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	return 180 * (1 - (2.0 / sides));    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	return (int)Math.ceil((2 / (1 - angle / 180)));
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	double angle = calculateRegularPolygonAngle(sides);

    	for(int i = 0; i < sides; i++) {

	    	turtle.forward(sideLength);

	    	turtle.turn(angle);

    	}
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	// Finds the target heading in relation to the current point. 

    	double targetBearing = Math.toDegrees(Math.atan2( targetX - currentX, targetY - currentY));

    	if(targetX - currentX < 0) targetBearing += 360;

    	

    	// Finds the amount the heading needs to be adjusted.

    	double adjustAngle = targetBearing - currentBearing; 	// CW is +, CCW is -   	

    	if(adjustAngle < 0 ) adjustAngle = 360 + adjustAngle;	// converts any CCW angle to its CW angle

    	

    	return adjustAngle;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
    	double currentBearing = 0.0;

        List<Double> resultingBearings = new ArrayList<>();

    	for( int i = 0; i < xCoords.size() - 1; i++) {

    		currentBearing = calculateBearingToPoint( currentBearing, xCoords.get(i), yCoords.get(i),

    				xCoords.get(i+1), yCoords.get(i+1));

    		resultingBearings.add(currentBearing);

    	}

    	

    	return resultingBearings;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return 
     */
    public static Point[] convexHull(Set<Point> points) {
    	
    	throw new RuntimeException("implement me!");

    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	int sum = 1;

    	for( int i = 0; i < 200; i++) {

        	sum += i;

        	turtle.forward(sum/1000);

	    	turtle.turn(-10);

        }

    	

    	sum = 1;

    	for( int i = 0; i < 200; i++) {

        	sum += i;

        	turtle.forward(sum/1000);

	    	turtle.turn(10);

        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        drawSquare(turtle, 40);

        // draw the window
        turtle.draw();
    }

}
/*
 * PresentationExamples.java
 *
 * Philippe Nadon
 * April 1, 2018
 *
 * A collection of methods designed to demonstrate
 * the nature and capabilities of Monte Carlo methods.
 */

import java.util.Random;

public class PresentationExamples {

  /**
   * Simulates Buffon's Needle to find pi.
   * The position and orientation of the needle is simulated,
   * and a counter then keeps track of the number of needles
   * that intersect evenly distanced parallel lines.
   *
   * Buffon's equation: pi = 2L / (p * d)
   * d is the spacing between the parallel lines ( we will use 4)
   * L is the length of needle ( we will use d / 2)
   * p = n/N, for n intersecting needles out of N total needles
   *
   * Thus, this equation simplifies to:
   * pi = 1 / p = N / n
   *
   * @param needlesThrown the total number of trials
   */
  public static double getBuffonPi(long needlesThrown) {

    Random rand = new Random();
    double DISTANCE = 2;
    double LENGTH = 1;
    long counter = 0;

    for (long trial = 0; trial < needlesThrown; trial++) {

      double orientation = Math.PI / 2.0 * rand.nextDouble();
      double horizProjection = LENGTH / 2.0 * Math.cos(orientation);

      double position = DISTANCE / 2.0 * rand.nextDouble();

      // Can replace horizProjection with getHorizProjection for testing alternative method, may be incorrect!
      if (position <= horizProjection)
        counter++;
    }
    // If the counter equals 0, the return value is technically infinite.
    if ( counter == 0 ) return 999999999;
    return (double) needlesThrown / (double) counter;
  }

  /**
   *
   * Using the alternative method of getting the horizontal projection of a needle
   *
   * @return The calculated horizontal projection
   */
  private static double getHorizOrientation() {

    Random rand = new Random();

    // Initial points
    double xPosHead = 2 * rand.nextDouble();
    double yPosHead = 2 * rand.nextDouble();
    double xPosTemp = 2 * rand.nextDouble();
    double yPosTemp = 2 * rand.nextDouble();

    // Slope calculations
    double xLength = xPosHead - xPosTemp;
    double yLength = yPosHead - yPosTemp;
    double slope = yLength / xLength;

    // Resized x position of tail
    double xPosTail = 1.0 / Math.sqrt( 1.0 + slope * slope );

    return Math.abs( ( xPosHead - xPosTail ) ) / 2;
  }

  /**
   * Similar to getBuffonPi,
   * uses a different method of calculating orientation
   *
   * Randomly chooses 2 points, one being a temporary point.
   * The temporary point is used to calulate the given slope
   * and is then "resized" so that the length from the head to tail is 1.
   *
   * The position of this line is then randomly chosen,
   * and a counter keeps track of intersections
   *
   * MAY BE INCORRECT!
   *
   * @param needlesThrown the total number of trials
   */
  public static double getBuffonPiAlt(long needlesThrown) {

    Random rand = new Random();
    long counter = 0;

    for (long trial = 0; trial < needlesThrown; trial++) {

      // Initial points
      double xPosHead = 2 * rand.nextDouble();
      double yPosHead = 2 * rand.nextDouble();
      double xPosTemp = 2 * rand.nextDouble();
      double yPosTemp = 2 * rand.nextDouble();

      // Slope calculations
      double xLength = xPosHead - xPosTemp;
      double yLength = yPosHead - yPosTemp;
      double slope = yLength / xLength;

      // Resized x position of tail
      double xPosTail = 1.0 / Math.sqrt( 1.0 + slope * slope );

      // The needle's position versus the line is randomly chosen
      double linePos = 2 * rand.nextDouble();

      // Checks for intersections
      if ( xPosHead <= xPosTail)
        if( linePos >= xPosHead && linePos <= xPosTail)
          counter++;
      if( xPosHead >= xPosTail)
        if( linePos >= xPosTail && linePos <= xPosHead)
          counter++;
    }
    if ( counter == 0 ) return 999999999;
    return (double) needlesThrown / (double) counter;
  }

  /**
   * Simulates dropping random points into the first quadrant of an x-y plane.
   *
   * If the point's distance from the origin is less than 1,
   * it is considered inside of the circle.
   * A counter keeps track of the number of points within the circle.
   *
   * @param pointsPlaced the total number of trials.
   *
   * Keep in mind a radius of 1 is used,
   * thus if the sqrt of xPos^2 + yPos^2 ( = to the distance)
   * is less than 1, so is xPos^2 + yPos^2, thus no square rooting is required.
   */
  public static double integrateCircle(long pointsPlaced) {

    Random rand = new Random();
    long counter = 0;

    for (long trial = 0; trial < pointsPlaced; trial++) {

      double yPos = rand.nextDouble();
      double xPos = rand.nextDouble();

      double distanceSquared = yPos * yPos + xPos * xPos;
      if (distanceSquared <= 1)
        counter++;
    }

    return 4.0 * ((double) counter / (double) pointsPlaced);
  }
}

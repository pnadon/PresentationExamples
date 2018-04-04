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
   * @param totalTrials the total number of trials
   */
  public static double getBuffonPi(long totalTrials) {

    Random rand = new Random();
    double DISTANCE = 2;
    double LENGTH = 1;
    long counter = 0;

    for (long trial = 0; trial < totalTrials; trial++) {

      double orientation = Math.PI / 2.0 * rand.nextDouble();
      double horizProjection = LENGTH / 2.0 * Math.cos(orientation);

      double position = DISTANCE / 2.0 * rand.nextDouble();

      if (position <= horizProjection)
        counter++;
    }
    if ( counter == 0 ) return 999999999;
    return (double) totalTrials / (double) counter;
  }

  /**
   * Simulates dropping random points into the first quadrant of an x-y plane.
   *
   * If the point's distance from the origin is less than 1,
   * it is considered inside of the circle.
   * A counter keeps track of the number of points within the circle.
   *
   * @param totalTrials the total number of trials.
   *
   * Keep in mind a radius of 1 is used,
   * thus if the sqrt of xPos^2 + yPos^2 ( = to the distance)
   * is less than 1, so is xPos^2 + yPos^2, thus no square rooting is required.
   */
  public static double integrateCircle(long totalTrials) {

    Random rand = new Random();
    long counter = 0;

    for (long trial = 0; trial < totalTrials; trial++) {

      double yPos = rand.nextDouble();
      double xPos = rand.nextDouble();

      double distanceSquared = yPos * yPos + xPos * xPos;
      if (distanceSquared <= 1)
        counter++;
    }

    return 4.0 * ((double) counter / (double) totalTrials);
  }
}

/**
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

    double buffonPi = (double) totalTrials / (double) counter;
    return buffonPi;

    /*
   Using 10 trials,         pi = 3.3333333333333335
   Using 100 trials,        pi = 3.225806451612903
   Using 1000 trials,       pi = 2.985074626865672
   Using 10000 trials,      pi = 3.08546744831842
   Using 100000 trials,     pi = 3.141196795979268
   Using 1000000 trials,    pi = 3.142519546471579
   Using 10000000 trials,   pi = 3.140230116062905
   Using 100000000 trials,  pi = 3.141795646803355
   Using 1000000000 trials, pi = 3.14140434477017

                         Actual: 3.14159265359
   */
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

    double area = 4.0 * ((double) counter / (double) totalTrials);
    return area;

    /*
    Using 10 trials,          area of circle = 3.6
    Using 100 trials,         area of circle = 3.08
    Using 1000 trials,        area of circle = 3.164
    Using 10000 trials,       area of circle = 3.126
    Using 100000 trials,      area of circle = 3.1384
    Using 1000000 trials,     area of circle = 3.14156
    Using 10000000 trials,    area of circle = 3.1418548
    Using 100000000 trials,   area of circle = 3.14168044
    Using 1000000000 trials,  area of circle = 3.141603956

                                       Actual: 3.14159265359
     */
  }
}

/*
 * Main.java
 *
 * Philippe Nadon
 * April 1, 2018
 *
 * modified April 10, 2018 - added alternative Buffon's needle method
 *
 * Uses Monte Carlo methods to obtain calculated results
 * See PresentationExamples.java for the methods themselves
 */


import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    DecimalFormat sciNot = new DecimalFormat("0.0E0");
    Scanner choice = new Scanner(System.in);

    long[] nums = {
        100,
        1000,
        10000,
        100000,
        1000000,
        10000000,
        100000000,
        1000000000,
    };

    System.out.print( "Choose a method to run: \n" +
        "1: Buffon's needle experiment\n" +
        "2: Buffon's needle experiment, no cos ver. ( May be incorrect!)\n" +
        "3: Finding pi via integrating a circle\n" +
        "4: Both 1 & 3\n\n" +
        "Answer: ");
    int example = choice.nextInt();

    System.out.print( "\nHow many sample points do you want? ( suggested is 20)\n" +
        "Answer: ");
    int numSamples = choice.nextInt();

    switch ( example) {

      case 1:
        System.out.println("\n\n______________________________________________________\n" +
            "BUFFON'S NEEDLE SIMULATION RESULTS:\n");
        for( long num : nums)
          simulateBuffonsNeedle( numSamples, num);
        break;

      case 2:
        System.out.println("\n\n______________________________________________________\n" +
            "BUFFON'S NEEDLE ALTERNATIVE SIMULATION RESULTS:\n");
        for( long num : nums)
          simulateBuffonsNeedleAlt( numSamples, num);
        break;

      case 3:
        System.out.println("\n\n______________________________________________________\n" +
            "COMPUTATION OF PI VIA MONTE CARLO INTEGRATION RESULTS:\n");
        for (long num : nums)
          findPiViaIntegral( numSamples, num);
        break;

      case 4:
        System.out.println("\n\n______________________________________________________\n" +
            "BUFFON'S NEEDLE SIMULATION RESULTS:\n");
        for( long num : nums)
          simulateBuffonsNeedle( numSamples, num);
        System.out.println("\n\n______________________________________________________\n" +
            "COMPUTATION OF PI VIA MONTE CARLO INTEGRATION RESULTS:\n");
        for (long num : nums)
          findPiViaIntegral( numSamples, num);
    }






  }

  /**
   * Runs the Buffons Needle experiment multiple times
   * to obtain an expected result
   *
   * @param numSamples The number of sample points to obtain
   * @param trialsPerSample The number of thrown needles the method simulates per sample
   */
  private static void simulateBuffonsNeedle(int numSamples, long trialsPerSample){

    long startTime = System.nanoTime();
    DecimalFormat sciNot = new DecimalFormat("0.00E0");
    double[] samples = new double[numSamples];
    double mean = 0;


    for( int sample = 0; sample < numSamples; sample++){
      samples[sample] = PresentationExamples.getBuffonPi(trialsPerSample);
      mean = mean + samples[sample] / (double)numSamples;
    }

    double standardDeviation = getStandardDeviation( mean, samples);

    long endTime = System.nanoTime();
    double duration = (endTime - startTime)/1000000000.0;

    System.out.print( "Using " + numSamples + " trials with " + trialsPerSample + " samples per trial," +
        "\nexpected value of pi = " + mean +
        ",\nwith a standard deviation of " + sciNot.format(standardDeviation) + ".\n");

    System.out.println("Simulation of " + sciNot.format(trialsPerSample*numSamples) + " thrown needles took "
        + sciNot.format(duration) + " seconds.\n");
  }


  /**
   * Runs the alternate Buffons Needle experiment multiple times
   * to obtain an expected result
   *
   * @param numSamples The number of sample points to obtain
   * @param trialsPerSample The number of thrown needles the method simulates per sample
   */
  private static void simulateBuffonsNeedleAlt(int numSamples, long trialsPerSample){

    long startTime = System.nanoTime();
    DecimalFormat sciNot = new DecimalFormat("0.00E0");
    double[] samples = new double[numSamples];
    double mean = 0;


    for( int sample = 0; sample < numSamples; sample++){
      samples[sample] = PresentationExamples.getBuffonPiAlt(trialsPerSample);
      mean = mean + samples[sample] / (double)numSamples;
    }

    double standardDeviation = getStandardDeviation( mean, samples);

    long endTime = System.nanoTime();
    double duration = (endTime - startTime)/1000000000.0;

    System.out.print( "Using " + numSamples + " trials with " + trialsPerSample + " samples per trial," +
        "\nexpected value of pi = " + mean +
        ",\nwith a standard deviation of " + sciNot.format(standardDeviation) + ".\n");

    System.out.println("Simulation of " + sciNot.format(trialsPerSample*numSamples) + " thrown needles took "
        + sciNot.format(duration) + " seconds.\n");
  }


  /**
   * Finds pi by integrating a circle via the Monte Carlo method numerous times
   *
   * @param numSamples The number of sample points to obtain
   * @param trialsPerSample The number of thrown needles the method simulates per sample
   */
  private static void findPiViaIntegral(int numSamples, long trialsPerSample){

    long startTime = System.nanoTime();
    DecimalFormat sciNot = new DecimalFormat("0.00E0");
    double[] samples = new double[numSamples];
    double mean = 0;

    for( int sample = 0; sample < numSamples; sample++){
      samples[sample] = PresentationExamples.integrateCircle(trialsPerSample);
      mean = mean + samples[sample] / (double)numSamples;
    }

    double standardDeviation = getStandardDeviation( mean, samples);

    long endTime = System.nanoTime();
    double duration = (endTime - startTime)/1000000000.0;

    System.out.print( "Using " + numSamples + " trials with " + trialsPerSample + " samples per trial," +
        "\nexpected value of pi = " + mean +
        ",\nwith a standard deviation of " + sciNot.format(standardDeviation) + ".\n");

    System.out.println("Computation of " + sciNot.format(trialsPerSample*numSamples) + " points took "
        + sciNot.format(duration) + " seconds.\n");
  }

  private static double getStandardDeviation(double mean, double[] samples) {

    int numSamples = samples.length;
    double variance = 0;

    for (double sample : samples)
      variance += (sample - mean) * (sample - mean);

    variance =  variance / (double) numSamples;
    return Math.sqrt( variance);
  }
}

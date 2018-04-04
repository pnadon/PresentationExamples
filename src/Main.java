/*
 * Main.java
 *
 * Philippe Nadon
 * April 1, 2018
 *
 * Uses Monte Carlo methods to obtain calculated results
 * See PresentationExamples.java for the methods themselves
 */


import java.text.DecimalFormat;

public class Main {

  public static void main(String[] args) {

    DecimalFormat sciNot = new DecimalFormat("0.000E0");

    long[] nums = {
        1,
        10,
        100,
        1000,
        10000,
        100000,
        1000000,
        10000000,
        100000000,
        1000000000,
    };

    int example = 0; // Choose the example you want to run
    int numSamples = 20; // Choose the number of sample points per method

    switch ( example) {

      case 0:
        System.out.println("______________________________________________________\n" +
            "BUFFON'S NEEDLE SIMULATION RESULTS:\n");
        for( long num : nums){
          long startTime = System.nanoTime();

          simulateBuffonsNeedle( numSamples, num);

          long endTime = System.nanoTime();
          long duration = (endTime - startTime)/1000000000;
          System.out.println("Simulation of " + num*numSamples + " total needles took " +
              sciNot.format(duration) + " seconds.\n");
        }
        break;

      case 1:
        System.out.println("______________________________________________________\n" +
            "COMPUTATION OF PI VIA MONTE CARLO INTEGRATION RESULTS:\n");
        for (long num : nums){
          long startTime = System.nanoTime();

          findPiViaIntegral( numSamples, num);

          long endTime = System.nanoTime();
          long duration = (endTime - startTime)/1000000000;

          System.out.println("Computation of " + num*numSamples + " points took "
              + sciNot.format(duration) + " seconds.\n");
        }
        break;
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

    double[] samples = new double[numSamples];
    double mean = 0;

    for( int sample = 0; sample < numSamples; sample++){
      samples[sample] = PresentationExamples.getBuffonPi(trialsPerSample);
      mean = mean + samples[sample] / (double)numSamples;
    }

    double standardDeviation = getStandardDeviation( mean, samples);

    System.out.print( "Using " + numSamples + " trials with " + trialsPerSample + " samples per trial,\n" +
        "expected value of pi = " + mean + ", +/- " + 1.96*standardDeviation + " with 95% confidence.\n\n");
  }


  /**
   * Finds pi by integrating a circle via the Monte Carlo method numerous times
   *
   * @param numSamples The number of sample points to obtain
   * @param trialsPerSample The number of thrown needles the method simulates per sample
   */
  private static void findPiViaIntegral(int numSamples, long trialsPerSample){

    double[] samples = new double[numSamples];
    double mean = 0;

    for( int sample = 0; sample < numSamples; sample++){
      samples[sample] = PresentationExamples.integrateCircle(trialsPerSample);
      mean = mean + samples[sample] / (double)numSamples;
    }

    double standardDeviation = getStandardDeviation( mean, samples);

    System.out.print( "Using " + numSamples + " trials with " + trialsPerSample + " samples per trial,\n" +
        "expected value of pi = " + mean + ", +/- " + 1.96*standardDeviation + " with 95% confidence.\n\n");
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

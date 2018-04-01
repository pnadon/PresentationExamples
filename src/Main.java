public class Main {

  public static void main(String[] args) {

    long[] nums = {
        10,
        100,
        1000,
        10000,
        100000,
        1000000, // 10 ^ 6
        10000000,
        100000000,
        1000000000 // 10 ^ 9
    };

    int example = 0; // Choose the example you want to run

    switch ( example) {

      case 0:
        for( long num : nums)
          PresentationExamples.getBuffonPi(num);
        break;

      case 1:
        for (long num : nums)
          PresentationExamples.integrateCircle( num);
        break;
    }



  }
}

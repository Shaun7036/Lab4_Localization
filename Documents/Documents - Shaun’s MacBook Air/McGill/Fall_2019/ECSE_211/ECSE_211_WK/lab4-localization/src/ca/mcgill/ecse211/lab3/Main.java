// Lab2.java
package ca.mcgill.ecse211.lab3;

import static ca.mcgill.ecse211.lab3.Resources.*;
import ca.mcgill.ecse211.lab3.UltrasonicController;
import lejos.hardware.Button;

/**
 * The main driver class for the odometry lab.
 */
public class Main {

  /**
   * The main entry point.
   *
   * @param args
   */

  public static UltrasonicController selectedController;


  public static void main(String[] args) {
    int buttonChoice;
    new Thread(odometer).start();
    

    buttonChoice = chooseLocalizationMethod();

    if (buttonChoice == Button.ID_LEFT) {
      //TODO us localization
      new Thread(new UltrasonicPoller()).start();
      (new USLocalization()).localize(1);
    } else {
      //TODO lightlocalization
      new Thread(new UltrasonicPoller()).start();
      (new USLocalization()).localize(0);
    }

    new Thread(new Display()).start();
    while (Button.waitForAnyPress() != Button.ID_ESCAPE) {
    } // do nothing

    System.exit(0);
  }


  /**
   * Asks the user whether the motors should drive in a square or float.
   *
   * @return the user choice
   */
  private static int chooseLocalizationMethod() {
    int buttonChoice;
    Display.showText("< Left    | Right >",
                     "          |        ",
                     "Faliing   |Rising ",
                     " edges    | edges "
                     );

    do {
      buttonChoice = Button.waitForAnyPress(); // left or right press
    } while (buttonChoice != Button.ID_LEFT && buttonChoice != Button.ID_RIGHT);
    return buttonChoice;
  }


  /**
   * Sleeps current thread for the specified duration.
   *
   * @param duration sleep duration in milliseconds
   */
  public static void sleepFor(long duration) {
    try {
      Thread.sleep(duration);
    } catch (InterruptedException e) {
      // There is nothing to be done here
    }
  }

}

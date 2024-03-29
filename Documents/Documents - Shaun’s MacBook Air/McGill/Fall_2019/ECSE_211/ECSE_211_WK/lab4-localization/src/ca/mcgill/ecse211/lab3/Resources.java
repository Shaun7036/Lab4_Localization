package ca.mcgill.ecse211.lab3;

import java.lang.reflect.Array;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;

/**
 * This class is used to define static resources in one place for easy access and to avoid cluttering the rest of the
 * codebase. All resources can be imported at once like this:
 * 
 * <p>
 * {@code import static ca.mcgill.ecse211.lab3.Resources.*;}
 */
public class Resources {

  /**
   * The wheel radius in centimeters.
   */
  public static final double WHEEL_RAD = 2.130;

  /**
   * The robot width in centimeters.
   */
  public static final double TRACK = 12.955;

  /**
   * The speed at which the robot moves forward in degrees per second.
   */
  public static final int FORWARD_SPEED = 250;

  /**
   * The speed at which the robot rotates in degrees per second.
   */
  public static final int ROTATE_SPEED = 50;

  /**
   * The motor acceleration in degrees per second squared.
   */
  public static final int ACCELERATION = 500;

  /**
   * Timeout period in milliseconds.
   */
  public static final int TIMEOUT_PERIOD = 3000;

  /**
   * The tile size in centimeters.
   */
  public static final double TILE_SIZE = 30.48;

  /**
   * The left motor.
   */
  public static final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("A"));

  /**
   * The right motor.
   */
  public static final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("D"));
  
  /**
   * The sensor motor.
   */
  public static final EV3MediumRegulatedMotor sensorMotor = new EV3MediumRegulatedMotor(LocalEV3.get().getPort("B"));

  /**
   * The ultrasonic sensor.
   */
  public static final EV3UltrasonicSensor US_SENSOR = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));

  public static final int FILTER_OUT = 50; 
  /**
   * The LCD.
   */
  public static final TextLCD LCD = LocalEV3.get().getTextLCD();

  /**
   * The odometer.
   */
  public static Odometer odometer = Odometer.getOdometer();

  public static Boolean navigating;

  public static Boolean wallDetector;

  public  static final float maxDistance = 50; //max distance we read otherwise no wall
  public  static final float constantD = 18;//constant d
  public  static final float risingEdgeDistance = (float) 10.4;
  public  static final float FallingEdgeDistance = 33;
  public  static final float marginDistance = (float) 0.25; //k
  
}

package ca.mcgill.ecse211.lab3;

import static ca.mcgill.ecse211.lab3.Resources.*;
import java.text.DecimalFormat;
public class Navigation {
  private int indexCoordinate=1;
  private double[] direction=new double[3];
  private static Thread T; 
  
  public static void drive( final String[] maps,int withObstacle) {
    // spawn a new Thread to avoid this method blocking
    
    (T = new Thread() {
      public void run() {
        
        int [] current_Coordinate=new int[2];
        int [] prev_Coordinate= {1,1};//initial position
        int [] displacement=new int[2];
        leftMotor.stop();
        rightMotor.stop();
        leftMotor.setAcceleration(ACCELERATION);
        rightMotor.setAcceleration(ACCELERATION);


        // Sleep for 2 seconds
        Main.sleepFor(TIMEOUT_PERIOD);
        for (int i = 0; i < maps.length; i++) {
          
          current_Coordinate=mapCoordinate(maps[i]);

          for(int x=0;x<2;x++) {
            displacement[x]=current_Coordinate[x]-prev_Coordinate[x];
          }
          
          
          travelTo(displacement[0]*TILE_SIZE,displacement[1]*TILE_SIZE);

        }
      }
    }).start();
    
    
    
    
  }
  
  /**
   * Converts input distance to the total rotation of each wheel needed to cover that distance.
   * 
   * @param distance
   * @return the wheel rotations necessary to cover the distance
   */
  public static int convertDistance(double distance) {
    return (int) ((180.0 * distance) / (Math.PI * WHEEL_RAD));
  }

  /**
   * Converts input angle to the total rotation of each wheel needed to rotate the robot by that
   * angle.
   * 
   * @param angle
   * @return the wheel rotations necessary to rotate the robot by the angle
   */
  public static int convertAngle(double angle) {
    return convertDistance(Math.PI * TRACK * angle / 360.0);
  } 
  
  static void travelTo(double x, double y) {
   double currX =odometer.getXYT()[0];
   double currY = odometer.getXYT()[1]; 
   double currTheta= Math.toRadians(odometer.getXYT()[2]);
   
   
   double dx=x-currX;
   double dy=y-currY;

   double dT=Math.atan2(dx,dy)-currTheta;
   turnTo(dT);//get the angle to turn to
   double distance =distanceToTravel(dx,dy);
   leftMotor.setSpeed(FORWARD_SPEED);
   rightMotor.setSpeed(FORWARD_SPEED);

   rightMotor.rotate(convertDistance( distance), true);
   leftMotor.rotate(convertDistance( distance), false);

   leftMotor.stop(true);
   rightMotor.stop(true);
  }

  static void turnTo(double theta) {

    
    theta = getMinAngle(theta);
    leftMotor.setSpeed(ROTATE_SPEED);
    rightMotor.setSpeed(ROTATE_SPEED);
    //turn to the left 
    if (theta < 0) {
        leftMotor.rotate(-convertAngle(-theta), true);
        rightMotor.rotate(convertAngle( -theta), false);
        sensorMotor.rotate(10);
    }
    //turn to the right 
    else {
        leftMotor.rotate(convertAngle( theta), true);
        rightMotor.rotate(-convertAngle(theta), false);
    }
    
  }
  boolean isNavigating() {
    return true;
  }
  static int [] mapCoordinate(String coordinate) {
    String coord_XY_str[]=coordinate.split(",", 2);
    int [] coord_XY= new int[coord_XY_str.length];
    for (int i=0;i<coord_XY_str.length;i++) {
      coord_XY[i]=Integer.parseInt(coord_XY_str[i]);
    }
    return coord_XY;
  }
  static double distanceToTravel(double x, double y) {
    return Math.hypot(x, y);   
  }
  static double getMinAngle(double theta) {
    if (theta <= -Math.PI) {
      theta += Math.PI * 2;
  } else if (theta > Math.PI) {
      theta -= Math.PI * 2;
  }
 
  return theta * 180.0 / Math.PI;
  }
}

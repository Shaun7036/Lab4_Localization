package ca.mcgill.ecse211.lab3;

import static ca.mcgill.ecse211.lab1.Resources.LEFT_MOTOR;
import static ca.mcgill.ecse211.lab1.Resources.MOTOR_HIGH;
import static ca.mcgill.ecse211.lab1.Resources.MOTOR_LOW;
import static ca.mcgill.ecse211.lab1.Resources.RIGHT_MOTOR;
import static ca.mcgill.ecse211.lab2.Resources.TRACK;
import static ca.mcgill.ecse211.lab2.Resources.WHEEL_RAD;
import static ca.mcgill.ecse211.lab3.Resources.*;

/**
 * This class is used to display the content of the odometer variables (x, y, Theta)
 */
public class USLocalization extends UltrasonicController {
  boolean firstwall = false;
  double Alpha = 0;
  double Beta = 0;
  public void localize(int edgeMethod) {

  if(edgeMethod==1) {//falling edge
    
    
  }
  else {
    
  }
  return;
  }


public void turnUntillNoWall() {
  while(this.distance<=maxDistance) {
    leftMotor.setSpeed(ROTATE_SPEED); // Start robot moving forward
    rightMotor.setSpeed(ROTATE_SPEED);//MOTOR_HIGH
    leftMotor.forward();
    rightMotor.backward();
    leftMotor.rotate(convertAngle(90));
    leftMotor.rotate(convertAngle(90));
    
    
  }
}

public static int convertDistance(double distance) {
  return (int) ((180.0 * distance) / (Math.PI * WHEEL_RAD));
}

public static int convertAngle(double angle) {
  return convertDistance(Math.PI * TRACK * angle / 360.0);
}  
}

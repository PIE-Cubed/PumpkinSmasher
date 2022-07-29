package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class Controls {
    //
    private final int portID = 0;
    private XboxController xbox;

    public Controls()   {
        xbox = new XboxController (portID);
    }

    /*Gets left motor power
    positive power to motor when stick is forward*/
    public double getLeftMotorPower() {
        return -1 * xbox.getLeftY();
    }

    /*Gets right motor power
    positive power to motor when stick is forward*/
    public double getRightMotorPower() {
        return -1 * xbox.getRightY(); 
    }

    public boolean deployPlateCylinder() {
        boolean leftBump;
        boolean rightBump;

        leftBump  = xbox.getLeftBumperPressed();
        rightBump = xbox.getRightBumperPressed();
        if ((leftBump == true) && (rightBump == true)) {
            return false;
        }
        else if ((leftBump == true) && (rightBump == false)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean retractPlateCylinder() {
        boolean leftBump;
        boolean rightBump;

        leftBump  = xbox.getLeftBumperPressed();
        rightBump = xbox.getRightBumperPressed();
        if ((leftBump == true) && (rightBump == true)) {
            return false;
        }
        else if ((leftBump == false) && (rightBump == true)) {
            return true;
        }
        else {
            return false;
        }
    }
}

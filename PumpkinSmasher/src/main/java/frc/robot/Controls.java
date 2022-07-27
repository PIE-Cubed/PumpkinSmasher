package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class Controls {
    //
    private final int portID = 1;
    private XboxController xbox;

    public Controls()   {
        xbox = new XboxController (portID);
    }

    //Gets left motor power
    public double getLeftMotorPower() {
        return xbox.getLeftY();
    }

    //Gets right motor power
    public double getRightMotorPower() {
        return xbox.getRightY();
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

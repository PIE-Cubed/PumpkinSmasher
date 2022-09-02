package frc.robot;

// Imports
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;


public class Drive {
    // Declaring objects
    private PWMTalonSRX leftMotorController;
    private PWMTalonSRX rightMotorController;
    private DifferentialDrive diffDrive;

    // Auto drive variables
    private boolean driveFirstTime;
    private long driveFinalMilliSeconds = 0;    

    // Auto rotate variables
    private boolean rotateFirstTime;
    private long rotateFinalMilliSeconds = 0;

    // Constructor to create drive object
    public Drive()  {
        leftMotorController  = new PWMTalonSRX(2);
        rightMotorController = new PWMTalonSRX(1);
        diffDrive            = new DifferentialDrive(leftMotorController, rightMotorController);
        driveFirstTime  = true;
        rotateFirstTime = true;
    }

    /* Function for giving motors power
    negative power make left motor go forward, positive power for right*/
    public void drive(double leftPower, double rightPower)  {
        diffDrive.tankDrive(-1 * leftPower, rightPower);
    }

    // Drives straight forward for a set time at a set power
    public int auto_drive(double seconds, double power) {
        // Ran the first time to initialize values
        if (driveFirstTime == true) {
            driveFirstTime = false;
            long initMilliSeconds = System.currentTimeMillis();
            driveFinalMilliSeconds = initMilliSeconds + (long) (seconds * 1000);
        }

        System.out.println("Drive power: " + power);
        drive(power, power);

        // Returns values to signify when to move to the next step
        if (System.currentTimeMillis() > driveFinalMilliSeconds) {
            // Resets driveFirstTime so it can be used for the next call
            driveFirstTime = true;
            drive(0, 0);
            return Robot.DONE;
        }
        else {
            return Robot.CONT;
        }
    }

    // Rotates for a set time at a set power
    // Positive rotate power makes left motor go forward and right motor go backwards - clockwise
    public int auto_rotate(double seconds, double power) {
        // Ran the first time to initialize values
        if (rotateFirstTime == true) {
            rotateFirstTime = false;
            long initMilliSeconds = System.currentTimeMillis();
            rotateFinalMilliSeconds = initMilliSeconds + (long) (seconds * 1000);
        }

        drive(power, -1 * power);

        // Returns values to signify when to move to the next step
        if (System.currentTimeMillis() > rotateFinalMilliSeconds) {
            // Resets rotateFirstTime so it can be used for the next call
            rotateFirstTime = true;
            drive(0, 0);
            return Robot.DONE;
        }
        else {
            return Robot.CONT;
        }
    }


    // Test functions
    public void testLeftSide(double leftPower) {
        drive(leftPower, 0);
    }

    public void testRightSide(double rightPower) {
        drive(0, rightPower);
    }

}


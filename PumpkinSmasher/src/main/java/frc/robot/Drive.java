package frc.robot;

// Imports
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;


public class Drive {
    // Declaring objects
    private PWMTalonSRX leftMotorController;
    private PWMTalonSRX rightMotorController;
    private DifferentialDrive diffDrive;

    // Constructor to create drive object
    public Drive()  {
        leftMotorController  = new PWMTalonSRX(2);
        rightMotorController = new PWMTalonSRX(1);
        diffDrive = new DifferentialDrive(leftMotorController, rightMotorController);
    }

    /* Function for giving motors power
    negative power make left motor go forward, positive power for right*/
    public void drive(double leftPower, double rightPower)  {
        diffDrive.tankDrive(-1 * leftPower, rightPower);
    }

    // Test functions
    public void testLeftSide(double leftPower) {
        drive(leftPower, 0);
    }

    public void testRightSide(double rightPower) {
        drive(0, rightPower);
    }

}


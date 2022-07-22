package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;

public class Drive {
    private PWMTalonSRX leftMotorController;
    private PWMTalonSRX rightMotorController;
    private DifferentialDrive diffDrive;

    public Drive()  {
        leftMotorController  = new PWMTalonSRX(2);
        rightMotorController = new PWMTalonSRX(1);
        diffDrive = new DifferentialDrive(leftMotorController, rightMotorController);
    }


    public void drive(double leftPower, double rightPower)  {
        diffDrive.tankDrive(leftPower, rightPower);
    }


}

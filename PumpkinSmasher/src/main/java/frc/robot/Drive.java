package frc.robot;

// Imports
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;

public class Drive {
    // Declaring objects
    private PWMTalonSRX leftMotorController;
    private PWMTalonSRX rightMotorController;
    private DifferentialDrive diffDrive;

    private AHRS ahrs;

    // Auto drive variables
    private boolean driveFirstTime;
    private long driveFinalMilliSeconds = 0;    

    // Auto rotate variables
    private boolean rotateFirstTime;

    // Constants
    private final double turnP = 0.09;//0.09
    private final double turnI = 0.00;
    private final double turnD = 0.00;
 
    // PID Controller
    private PIDController turnController;

    // Constructor to create drive object
    public Drive()  {
        ahrs = new AHRS(SPI.Port.kMXP);
        leftMotorController  = new PWMTalonSRX(2);
        rightMotorController = new PWMTalonSRX(1);
        diffDrive            = new DifferentialDrive(leftMotorController, rightMotorController);

        turnController       = new PIDController(turnP, turnI, turnD);
        turnController.setSetpoint(3);

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

    /* Rotates for a set time at a set power
     Positive rotate power makes left motor go forward and right motor go backwards - clockwise
     Degreees parameter is absolute - based on where robot started */
    public int auto_rotate(double targetDegrees) {       
        double power = turnController.calculate(ahrs.getYaw() - targetDegrees);
        drive(power, -1 * power);
        System.out.println("Degrees: " + ahrs.getYaw());

        if (turnController.atSetpoint()) {
            return Robot.DONE;
        }
        else {
            return Robot.CONT;
        }
    }

    public void zeroYaw() {
        ahrs.zeroYaw();
    }

    // Test functions
    public void testLeftSide(double leftPower) {
        drive(leftPower, 0);
    }

    public void testRightSide(double rightPower) {
        drive(0, rightPower);
    }

}


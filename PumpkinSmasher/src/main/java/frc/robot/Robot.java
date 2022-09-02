// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // obj
  private Controls controls;
  private Drive drive;
  private Pneumatics pneumatics;
  private Auto auto;

  // Constants
  public static final int CONT = 0;
  public static final int DONE = 1;

  // Variables
  private int status = CONT;

  // Enumeration for drive methods
  private enum DriveState {
    AUTO_STATE,
    TELEOP_STATE;
  }

  private DriveState driveState = DriveState.TELEOP_STATE;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    // inst
    controls = new Controls();
    drive = new Drive();
    pneumatics = new Pneumatics();
    auto = new Auto();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if (status == CONT) {
      status = auto.myAuto(drive, pneumatics);
    }
    else {
      drive.drive(0, 0);
    }
    /*
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }*/
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    wheelControl();
    pneumaticsControl();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  // Controls the driving portion of teleop
  private void wheelControl() {
    if (driveState == DriveState.TELEOP_STATE) {
      // Input
      double leftPower     = controls.getLeftMotorPower();
      double rightPower    = controls.getRightMotorPower();
      boolean startAuto    = controls.startDriveAuto();
  
      // Next state
      if (startAuto == true) {
        driveState = DriveState.AUTO_STATE;
      }
      else if (startAuto == false) {
        driveState = DriveState.TELEOP_STATE;
      }

      // Action
      drive.drive(leftPower, rightPower);
    }
    else if (driveState == DriveState.AUTO_STATE) {
      // Input and action
      int driveStatus = auto.myAuto(drive, pneumatics);

      // Next state
      if (driveStatus == DONE) {
        driveState = DriveState.TELEOP_STATE;
      }
      else if (driveStatus == CONT) {
        driveState = DriveState.AUTO_STATE;
      }
      else {
        driveState = DriveState.TELEOP_STATE;
      }
    }
    
  }

  // Controls the pneumatics during teleop
  private void pneumaticsControl() {
    boolean deployState  = controls.deployPlateCylinder();
    boolean retractState = controls.retractPlateCylinder();
    boolean sirenState   = controls.getSirenEnabled();

    // Toggles piston
    if (deployState == true) {
      pneumatics.extendPiston();
    }
    else if (retractState == true) {
      pneumatics.retractPiston();
    }

    // Control siren 
    if (sirenState == true) {
      pneumatics.sirenOn();
    }
    else {
      pneumatics.sirenOff();
    }
  }
}

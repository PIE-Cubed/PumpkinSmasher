package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

public class Pneumatics {
    // Constants
    private final int EXTEND_PISTON  = 0;
    private final int RETRACT_PISTON = 1;
    private final int SIREN          = 2;

    // Object declaration
    private DoubleSolenoid doubleSolenoid;
    private Solenoid       sirenSolenoid;

    // Constructor
    public Pneumatics() {
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, EXTEND_PISTON, RETRACT_PISTON);
        sirenSolenoid  = new Solenoid(PneumaticsModuleType.CTREPCM, SIREN);
    }


    // Extends piston
    public void extendPiston() {
        doubleSolenoid.set(Value.kForward);
    }

    // Retracts piston
    public void retractPiston() {
        doubleSolenoid.set(Value.kReverse);
    }

    // Toggles piston
    public void togglePiston() {
        doubleSolenoid.toggle();
    }

    public void sirenOn() {
        sirenSolenoid.set(true);
    }

    public void sirenOff() {
        sirenSolenoid.set(false);
    }

}

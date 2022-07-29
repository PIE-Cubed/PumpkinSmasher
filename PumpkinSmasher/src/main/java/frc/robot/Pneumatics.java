package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Pneumatics {
    // Constants
    private final int EXTEND_PISTON  = 0;
    private final int RETRACT_PISTON = 3;

    // Object declaration
    private DoubleSolenoid doubleSolenoid;

    // Constructor
    public Pneumatics() {
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, EXTEND_PISTON, RETRACT_PISTON);
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
}

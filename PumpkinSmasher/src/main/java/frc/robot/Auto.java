package frc.robot;

public class Auto {
    private boolean firstTime;
    private int step;

    public Auto() {
        firstTime = true;
        step = 1;
    }

    public int myAuto(Drive drive, Pneumatics pneumatics) {

        int status;
        if (firstTime == true) {
            firstTime = false;
            step = 1;
        }

        switch(step) {
            case 1:
                status = drive.auto_drive(1, 0.5);
                break;
            case 2:
                status = drive.auto_rotate(1, 0.75);
                break;
            default:
                drive.drive(0, 0);
                firstTime = true;
                step = 1;
                return Robot.DONE;
        }

        System.out.println("Step: " + step);

        if (status == Robot.DONE) {
            step ++;
        }

        return Robot.CONT;
    }
    
    // Function to Reset Variables
    public void reset(Drive drive){
        firstTime = true;
        step = 1;
        drive.drive(0,0);
    }

}
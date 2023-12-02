package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;

// this program holds the functions for the robot to use during auton such as "drive forward' or "turn"
public class robotDrive {

    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor intake;

    // motor variables
    // encoder resolution
    double ticks = 537.7;
    // measurements in inches
    double wheelDiam = 3.75;
    // inverse gear ratio of sprockets
    double reduction = 5/7;
    double counts_per_inch = (ticks*reduction)/(wheelDiam*Math.PI);
    // Constructor
    public robotDrive(DcMotor left, DcMotor right, DcMotor intk){
        // have the motors that the program is using be the motors for these functions
        leftDrive = left;
        rightDrive = right;
        intake = intk;

        // configure encoders on motors
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // set motor's stopping to break
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // configure drive motors' directions
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    private void stopDrive(){
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void encoderDrive(double power, double leftInches, double rightInches){

        int newLeftTarget = leftDrive.getCurrentPosition() + (int)(leftInches * counts_per_inch);
        int newRightTarget = rightDrive.getCurrentPosition() + (int)(rightInches * counts_per_inch);
        leftDrive.setTargetPosition(newLeftTarget);
        rightDrive.setTargetPosition(newRightTarget);

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDrive.setPower(power);
        rightDrive.setPower(power);

        while(true) {
            if (!leftDrive.isBusy() && !rightDrive.isBusy()) {
                stopDrive();
                break;
            }
        }
    }

    public void turn(boolean left, double power, int duration) throws InterruptedException {
        if(left){
            leftDrive.setPower(-power);
            rightDrive.setPower(power);
        }else{
            leftDrive.setPower(power);
            rightDrive.setPower(-power);
        }

        Thread.sleep(duration);
        stopDrive();
    }

    public void spinIntake(boolean forward, double power, int duration) throws InterruptedException{
        if(forward){
            intake.setPower(power);
        }else{
            intake.setPower(-power);
        }
        Thread.sleep(duration);
        intake.setPower(0);
    }

}

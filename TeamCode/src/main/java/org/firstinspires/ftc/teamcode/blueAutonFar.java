package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;



@Autonomous(name = "FarAuton")

public class blueAutonFar extends LinearOpMode{


    DcMotor leftDrive;

    DcMotor rightDrive;

    DcMotor intake;


    public void runOpMode(){

        // set up motors

        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");

        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");

        intake = hardwareMap.get(DcMotor.class, "intake");

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();


        drive("forward", 2000);

        sleep(300);


        intake.setPower(0.9);

        sleep(500);

        intake.setPower(0);


        drive("reverse", 2000);

        sleep(300);


        turnRight(1500);

        sleep(300);


        drive("forward", 4000);


        intake.setPower(0.9);

        sleep(1000);

        intake.setPower(0);

    }


    // drive functions

    private void stopDrive(){

        leftDrive.setPower(0);

        rightDrive.setPower(0);

    }


    private void drive(String direction, int duration){

        if(direction == "forward") {

            leftDrive.setDirection(DcMotor.Direction.FORWARD);

            rightDrive.setDirection(DcMotor.Direction.REVERSE);

        }else if(direction == "reverse"){

            leftDrive.setDirection(DcMotor.Direction.FORWARD);

            rightDrive.setDirection(DcMotor.Direction.FORWARD);

        }else{

            telemetry.addData("Error:", "Wrong direction in drive parameter");

        }


        leftDrive.setPower(0.9);

        rightDrive.setPower(0.9);


        sleep(duration);

        stopDrive();

    }


    private void turnLeft(int duration){

        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        rightDrive.setDirection(DcMotor.Direction.REVERSE);


        leftDrive.setPower(0.5);

        rightDrive.setPower(0.5);


        sleep(duration);

        stopDrive();

    }


    private void turnRight(int duration){

        leftDrive.setDirection(DcMotor.Direction.FORWARD);

        rightDrive.setDirection(DcMotor.Direction.FORWARD);


        leftDrive.setPower(0.5);

        rightDrive.setPower(0.5);


        sleep(duration);

        stopDrive();

    }


}

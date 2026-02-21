package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class AutonomousTutorial extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        RobotHardwareMap marathonMap = new RobotHardwareMap();
        marathonMap.init(hardwareMap);



        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

//      go to this link and scroll down to see how the mecanum drive works: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html


//            how to write the autonomous:

            long forward = 1200;

//            first make the motors move:
//            the directions is your choice
            marathonMap.frontLeftMotor.setPower(1);
            marathonMap.backLeftMotor.setPower(1);
            marathonMap.frontRightMotor.setPower(1);
            marathonMap.backRightMotor.setPower(1);
//            then make it do that actions for a certain time:
            sleep(forward);
//            then make it stop:
            marathonMap.frontLeftMotor.setPower(0);
            marathonMap.backLeftMotor.setPower(0);
            marathonMap.frontRightMotor.setPower(0);
            marathonMap.backRightMotor.setPower(0);






        }



    }
}
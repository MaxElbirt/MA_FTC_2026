package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class SixBallsAutoRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        RobotHardwareMap marathonMap = new RobotHardwareMap();
        marathonMap.init(hardwareMap);



        waitForStart();

        if (isStopRequested()) return;


//      go to this link and scroll down to see how the mecanum drive works: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html


//            how to write the autonomous:
        // V = 14 - 14.20 - 13.80
        long backwards = 800;
        short shoot = 3000;
        short getreadyshoter = 3000;
        short turnoff2 = 0;
        short kicker = 600;
        short wait = 2000;
        short moveright = 770;
        short turnback = 745;
        short moveback = 1000;
        short movefowrored = 300;
        short getreadyshoter2 = 5000;

        marathonMap.hood.setPosition(0.45);
        marathonMap.frontLeftMotor.setPower(-0.6);
        marathonMap.frontRightMotor.setPower(-0.6);
        marathonMap.backLeftMotor.setPower(-0.5);
        marathonMap.backRightMotor.setPower(-0.5);
        sleep(backwards);

        marathonMap.kickerMotor.setPower(0);
        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
        sleep(getreadyshoter);

        marathonMap.shooterMotor1.setVelocity(300);
        marathonMap.shooterMotor2.setPower(-marathonMap.shooterMotor1.getPower());
        sleep(4000);

        marathonMap.kickerMotor.setPower(-0.6);
        sleep(kicker);

        marathonMap.kickerMotor.setPower(0);
        sleep(wait);

        marathonMap.kickerMotor.setPower(-0.6);
        sleep(kicker);

        marathonMap.kickerMotor.setPower(0);
        sleep(wait);

        marathonMap.kickerMotor.setPower(-0.6);
        sleep(kicker);

        marathonMap.kickerMotor.setPower(0);
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setPower(-marathonMap.shooterMotor1.getPower());
        marathonMap.kickerMotor.setPower(0);



        marathonMap.intakeMotor.setPower(-0.8);
        marathonMap.frontLeftMotor.setPower(-0.7);
        marathonMap.frontRightMotor.setPower(-0.7);
        marathonMap.backLeftMotor.setPower(-0.7);
        marathonMap.backRightMotor.setPower(-0.7);
        sleep(movefowrored);

        marathonMap.frontLeftMotor.setPower(0.7);
        marathonMap.frontRightMotor.setPower(-0.7);
        marathonMap.backLeftMotor.setPower(0.7);
        marathonMap.backRightMotor.setPower(-0.7);
        sleep(turnback);

        marathonMap.frontLeftMotor.setPower(-0.7);
        marathonMap.backRightMotor.setPower(-0.7);
        marathonMap.frontRightMotor.setPower(0.7);
        marathonMap.backLeftMotor.setPower(0.7);

        sleep(moveright);
        marathonMap.frontLeftMotor.setPower(-0.4);
        marathonMap.frontRightMotor.setPower(-0.4);
        marathonMap.backLeftMotor.setPower(-0.4);
        marathonMap.backRightMotor.setPower(-0.4);
        sleep(moveback);



        marathonMap.kickerMotor.setPower(-0.5);
        sleep(400);
        marathonMap.kickerMotor.setPower(0);
        sleep(300);

        marathonMap.kickerMotor.setPower(-0.5);
        sleep(400);
        marathonMap.kickerMotor.setPower(0);
        sleep(300);

        marathonMap.kickerMotor.setPower(-0.5);
        sleep(400);
        marathonMap.kickerMotor.setPower(0);
        sleep(300);

        marathonMap.frontLeftMotor.setPower(0.7);
        marathonMap.frontRightMotor.setPower(0.7);
        marathonMap.backLeftMotor.setPower(0.7);
        marathonMap.backRightMotor.setPower(0.7);
        sleep(800);

        marathonMap.frontLeftMotor.setPower(0.7);
        marathonMap.frontRightMotor.setPower(-0.7);
        marathonMap.backLeftMotor.setPower(-0.7);
        marathonMap.backRightMotor.setPower(0.7);
        sleep(1600);

        marathonMap.frontLeftMotor.setPower(-0.7);
        marathonMap.frontRightMotor.setPower(0.7);
        marathonMap.backLeftMotor.setPower(-0.7);
        marathonMap.backRightMotor.setPower(0.7);
        sleep(600);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);


        sleep(getreadyshoter);

        marathonMap.shooterMotor1.setVelocity(500);
        marathonMap.shooterMotor2.setPower(-marathonMap.shooterMotor1.getPower());
        sleep(4000);

        marathonMap.kickerMotor.setPower(-0.6);
        sleep(kicker);

        marathonMap.kickerMotor.setPower(0);
        sleep(wait);

        marathonMap.kickerMotor.setPower(-0.6);
        sleep(kicker);

        marathonMap.kickerMotor.setPower(0);
        sleep(wait);

        marathonMap.kickerMotor.setPower(-0.6);
        sleep(kicker);

        marathonMap.kickerMotor.setPower(0);
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setPower(-marathonMap.shooterMotor1.getPower());
        marathonMap.kickerMotor.setPower(0);


//  st


        // stop shoter

        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower());

    }
}





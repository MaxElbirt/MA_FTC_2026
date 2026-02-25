package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class SixBallsAutoRed extends LinearOpMode {
    RobotHardwareMap marathonMap = new RobotHardwareMap();

    public void runShooterVelocity(double targetVelo) {
        marathonMap.shooterMotor1.setPower((targetVelo / 1500) +  ((targetVelo - marathonMap.shooterMotor1.getVelocity()) * 0.01));
        telemetry.addData("Target Power", (targetVelo / 1500) +  ((targetVelo - marathonMap.shooterMotor1.getVelocity()) * 0.0001));
    }

    @Override
    public void runOpMode() throws InterruptedException {


        marathonMap.init(hardwareMap);



        waitForStart();

        if (isStopRequested()) return;


        // goes back, shoots 3 balls, collects 3 more, and shoots them again, totalling 6 balls.


//      go to this link and scroll down to see how the mecanum drive works: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html


//            how to write the autonomous:
        // V = 14 - 14.20 - 13.80
        long backwards = 800;
        short shoot = 3000;
        short getreadyshoter = 1500;
        short turnoff2 = 0;
        short kicker = 600;
        short wait = 1000;
        short moveright = 770;
        short turnback = 740;
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


        marathonMap.shooterMotor1.setPower(0.7);
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
        marathonMap.shooterMotor1.setPower(0);
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
        sleep(650);

        marathonMap.frontLeftMotor.setPower(0.7);
        marathonMap.frontRightMotor.setPower(-0.7);
        marathonMap.backLeftMotor.setPower(-0.7);
        marathonMap.backRightMotor.setPower(0.7);
        sleep(1550);

        marathonMap.frontLeftMotor.setPower(-0.7);
        marathonMap.frontRightMotor.setPower(0.7);
        marathonMap.backLeftMotor.setPower(-0.7);
        marathonMap.backRightMotor.setPower(0.7);
        sleep(650);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);


        sleep(getreadyshoter);

      marathonMap.shooterMotor1.setPower(0.7);
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

        marathonMap.frontLeftMotor.setPower(0.6);
        marathonMap.frontRightMotor.setPower(-0.6);
        marathonMap.backLeftMotor.setPower(-0.6);
        marathonMap.backRightMotor.setPower(0.6);
sleep(1200);





        marathonMap.kickerMotor.setPower(0);
        marathonMap.shooterMotor1.setPower(0);
        marathonMap.kickerMotor.setPower(0);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
//  st


        // stop shoter


    }
}





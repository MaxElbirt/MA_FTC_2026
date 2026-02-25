package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
        // V = 13.50 - 13.70- 13.30
        long backwards = 800;
        short shoot = 3000;
        short getreadyshoter = 3000;
        short turnoff2 = 0;
        short kicker = 600;
        short wait = 2000;
        short moveright = 770;
        short turnback = 775;
        short moveback = 2000;
        short movefowrored = 300;


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

        marathonMap.shooterMotor1.setVelocity(1150);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower());
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
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower());
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

        marathonMap.frontLeftMotor.setPower(-0.8);
        marathonMap.backRightMotor.setPower(-0.8);
        marathonMap.frontRightMotor.setPower(0.8);
        marathonMap.backLeftMotor.setPower(0.8);

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
        sleep(1700);

        marathonMap.frontLeftMotor.setPower(0.7);
        marathonMap.frontRightMotor.setPower(-0.7);
        marathonMap.backLeftMotor.setPower(0.7);
        marathonMap.backRightMotor.setPower(-0.7);
        sleep(turnback);


        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
        marathonMap.shooterMotor1.setVelocity(1300);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower());
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



        // stop shooter
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower());

    }
}





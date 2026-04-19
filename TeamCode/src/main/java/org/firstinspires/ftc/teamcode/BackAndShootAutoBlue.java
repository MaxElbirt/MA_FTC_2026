package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class BackAndShootAutoBlue extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        RobotHardwareMap marathonMap = new RobotHardwareMap();
        marathonMap.init(hardwareMap);
        HelperFuncs helper = new HelperFuncs();
        helper.init(hardwareMap, telemetry);
        waitForStart();

        if (isStopRequested()) return;
        //this autonomous goes backwards, shoots, and leaves the big shooting zone.

//      go to this link and scroll down to see how the mecanum drive works: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html


//            how to write the autonomous:

        long backwards = 800;
        short getreadyshooter = 3000;
        short kicker = 600;
        short wait = 2000;
        short moveleft = 1000;

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
        sleep(getreadyshooter);

        helper.setShooterVelocities(0.7);
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



        helper.runShooterVelocity(0);


        marathonMap.frontLeftMotor.setPower(-0.6);
        marathonMap.frontRightMotor.setPower(0.6);
        marathonMap.backLeftMotor.setPower(0.6);
        marathonMap.backRightMotor.setPower(-0.6);

        sleep(moveleft);
        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);









    }
}
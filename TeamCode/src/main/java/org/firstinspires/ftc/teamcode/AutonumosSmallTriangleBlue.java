package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Autonomous
public class AutonumosSmallTriangleBlue extends LinearOpMode {
    private static final Logger log = LoggerFactory.getLogger(AutonumosSmallTriangleBlue.class);

    @Override
    public void runOpMode() throws InterruptedException {


        RobotHardwareMap marathonMap = new RobotHardwareMap();
        marathonMap.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;
        long getReadyShoter = 2000;
        short kicker = 3000;
        short shoot = 600;
        short getreadyshooter = 2000;
        short forward = 1100;
        short turn = 215;
        short warmUp = 2500;
        long stop = 250;
        long forward2 = 250;

        marathonMap.hood.setPosition(0.45);
        marathonMap.frontLeftMotor.setPower(1);
        marathonMap.frontRightMotor.setPower(1);
        marathonMap.backLeftMotor.setPower(1);
        marathonMap.backRightMotor.setPower(1);
        sleep(forward);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);

        marathonMap.frontLeftMotor.setPower(-1);
        marathonMap.backLeftMotor.setPower(-1);
        marathonMap.frontRightMotor.setPower(1);
        marathonMap.backRightMotor.setPower(1);
        sleep(turn);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
        sleep(stop * 2);

        marathonMap.frontRightMotor.setPower(1);
        marathonMap.frontLeftMotor.setPower(1);
        marathonMap.backRightMotor.setPower(1);
        marathonMap.backLeftMotor.setPower(1);
        sleep(forward2);

        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.frontLeftMotor.setPower(0);
        sleep(getreadyshooter);

        marathonMap.shooterMotor1.setVelocity(600);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower());
        sleep(getreadyshooter);

        marathonMap.kickerMotor.setPower(0);
        sleep(warmUp);
        marathonMap.kickerMotor.setPower(-0.6);
        sleep(shoot);
        marathonMap.kickerMotor.setPower(0);
        sleep(warmUp);
        marathonMap.kickerMotor.setPower(-0.6);
        sleep(shoot);
        marathonMap.kickerMotor.setPower(0);
        sleep(warmUp);
        marathonMap.kickerMotor.setPower(-0.6);
        sleep(shoot);

        marathonMap.kickerMotor.setPower(0);
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setVelocity(0);
    }
}
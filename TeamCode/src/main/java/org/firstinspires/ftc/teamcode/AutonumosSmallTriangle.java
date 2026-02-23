package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Autonomous
public class AutonumosSmallTriangle extends LinearOpMode {
    private static final Logger log = LoggerFactory.getLogger(AutonumosSmallTriangle.class);

    @Override
    public void runOpMode() throws InterruptedException {


        RobotHardwareMap marathonMap = new RobotHardwareMap();
        marathonMap.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;
        short shoot = 3000;
        short getreadyshoter = 3000;

        marathonMap.hood.setPosition(0.45);
        marathonMap.frontLeftMotor.setPower(1);
        marathonMap.frontRightMotor.setPower(1);
        marathonMap.backLeftMotor.setPower(1);
        marathonMap.backRightMotor.setPower(1);
        sleep(1100);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);

        marathonMap.frontLeftMotor.setPower(1);
        marathonMap.backLeftMotor.setPower(1);
        marathonMap.frontRightMotor.setPower(-1);
        marathonMap.backRightMotor.setPower(-1);
        sleep(145);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
        sleep(500);
        marathonMap.frontRightMotor.setPower(1);
        marathonMap.frontLeftMotor.setPower(1);
        marathonMap.backRightMotor.setPower(1);
        marathonMap.backLeftMotor.setPower(1);
        sleep(200);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.frontLeftMotor.setPower(0);
        sleep(getreadyshoter);

        marathonMap.shooterMotor1.setVelocity(810);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower());
        sleep(2000);

        marathonMap.kickerMotor.setPower(0);
        sleep(2500);
        marathonMap.kickerMotor.setPower(-0.6);
        sleep(600);
        marathonMap.kickerMotor.setPower(0);
        sleep(2500);
        marathonMap.kickerMotor.setPower(-0.6);
        sleep(600);
        marathonMap.kickerMotor.setPower(0);
        sleep(2500);
        marathonMap.kickerMotor.setPower(-0.6);
        sleep(800 );
        marathonMap.kickerMotor.setPower(0);
        sleep(shoot);

        marathonMap.kickerMotor.setPower(0);
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setVelocity(0);
        sleep(500);






    }
}
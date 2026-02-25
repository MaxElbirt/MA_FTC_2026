package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Autonomous
public class leaveShootingZoneBlue extends LinearOpMode {
    private static final Logger log = LoggerFactory.getLogger(leaveShootingZoneBlue.class);

    @Override
    public void runOpMode() throws InterruptedException {


        RobotHardwareMap marathonMap = new RobotHardwareMap();
        marathonMap.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;


        long backwards1 = 600;
        long right = 800;
        marathonMap.frontRightMotor.setPower(-1);
        marathonMap.frontLeftMotor.setPower(-1);
        marathonMap.backRightMotor.setPower(-1);
        marathonMap.backLeftMotor.setPower(-1);
        sleep(right);
        marathonMap.frontLeftMotor.setPower(-1);
        marathonMap.backRightMotor.setPower(-1);
        marathonMap.frontRightMotor.setPower(1);
        marathonMap.backLeftMotor.setPower(1);
        sleep(backwards1);
        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);




    }
}



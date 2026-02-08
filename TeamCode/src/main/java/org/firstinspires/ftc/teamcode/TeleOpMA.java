package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class TeleOpMA extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        RobotHardwareMap marathonMap = new RobotHardwareMap();
        marathonMap.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            //changing rotation, correcting to north of field
            double currHeading = marathonMap.getHeading();
            double rotateY = y*Math.cos(-currHeading) - y*Math.sin(-currHeading);
            double rotateX = x*Math.sin(-currHeading) + y*Math.cos(-currHeading);

            //creating denominator for normalization of stick values (see below)
            double denominator = Math.max(Math.abs(rotateY) + Math.abs(rotateX) + Math.abs(rx), 1);

            //Normalizing to balance effect of stick values
            double frontLeftPower = (rotateY + rotateX + rx) / denominator;
            double backLeftPower = (rotateY - rotateX + rx) / denominator;
            double frontRightPower = (rotateY - rotateX - rx) / denominator;
            double backRightPower = (rotateY + rotateX - rx) / denominator;

            //Passing power to motor
            marathonMap.frontLeftMotor.setPower(frontLeftPower);
            marathonMap.backLeftMotor.setPower(backLeftPower);
            marathonMap.frontRightMotor.setPower(frontRightPower);
            marathonMap.backRightMotor.setPower(backRightPower);
        }
    }
}

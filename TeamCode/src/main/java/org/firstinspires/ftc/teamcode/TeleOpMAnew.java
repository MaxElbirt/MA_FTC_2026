package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class TeleOpMAnew extends LinearOpMode {
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
            double rotX = x * Math.cos(-currHeading) - y * Math.sin(-currHeading);
            double rotY = x * Math.sin(-currHeading) + y * Math.cos(-currHeading);

            //creating denominator for normalization of stick values (see below)
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            //Passing power to motor
            marathonMap.frontLeftMotor.setPower(frontLeftPower);
            marathonMap.backLeftMotor.setPower(backLeftPower);
            marathonMap.frontRightMotor.setPower(frontRightPower);
            marathonMap.backRightMotor.setPower(backRightPower);


            if (gamepad1.right_bumper == true) {
                marathonMap.shooterMotor1.setVelocity(1300);
                marathonMap.shooterMotor2.setVelocity(-1300);
            } else if (gamepad1.x == true) {
                marathonMap.shooterMotor1.setVelocity(0);
                marathonMap.shooterMotor2.setVelocity(0);

            } else if (gamepad1.y == true ) {
                marathonMap.shooterMotor1.setVelocity(-950);
                marathonMap.shooterMotor2.setVelocity(950);
            } else {
                marathonMap.shooterMotor1.setVelocity(0);
                marathonMap.shooterMotor2.setVelocity(0);
            }


            if (gamepad1.left_trigger_pressed) {
                marathonMap.kickerMotor.setPower(-0.9);
            } else if (gamepad1.left_bumper) {
                marathonMap.kickerMotor.setPower(-0.9);
            } else if (gamepad1.y) {
                marathonMap.kickerMotor.setPower(0.9);
            } else {
                marathonMap.kickerMotor.setPower(0.0);
            }


            if (gamepad1.right_trigger_pressed) {
                marathonMap.intakeMotor.setPower(-0.8);
            } else if (gamepad1.y) {
                marathonMap.intakeMotor.setPower(0.8);
            } else {
                marathonMap.intakeMotor.setPower(0.0);
                {
                }


                double hoodposition = marathonMap.hood.getPosition();
                double hoodincrement = 0.005;
                if (gamepad1.dpad_up) {
                    marathonMap.hood.setPosition(Math.abs(Math.min(0.45, hoodposition + hoodincrement)));
                } else if (gamepad1.dpad_down) {
                    marathonMap.hood.setPosition(Math.abs(Math.max(0.0, hoodposition - hoodincrement)));
                }
            }


            //resetting imu yaw ----> options button+------------------------------------------------------------------------------------------------------------------------------.
            if (gamepad1.options) {
                marathonMap.imu.resetYaw();
                
                
                

            }
            telemetry.addData("hood pos: ", marathonMap.hood.getPosition());
            telemetry.addData("shooter 1 velocity: ", marathonMap.shooterMotor1.getVelocity());
            telemetry.addData("shooter 2 velocity: ", marathonMap.shooterMotor2.getVelocity());
            telemetry.update();
        }

    }
}



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

            boolean kickerOn = false;     // current state
            boolean lastB = false;
            boolean lastY = false;          // previous button state
            boolean intakeOn = false;        // previous button state

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


            if (gamepad1.a == true) {
                marathonMap.shooterMotor1.setVelocity(1400);
                marathonMap.shooterMotor2.setVelocity(1400);
            } else if (gamepad1.x == true) {
                marathonMap.shooterMotor1.setVelocity(0);
                marathonMap.shooterMotor2.setVelocity(0);
            }

            boolean currentB = gamepad1.left_bumper;
            boolean currentX = gamepad1.left_trigger_pressed;

            if (currentB) {

            } else if (currentX) {

            } else {
                marathonMap.kickerMotor.setPower(0);
            }


            if (currentB && !lastB)
                kickerOn = !kickerOn;

            if (kickerOn) {
                marathonMap.kickerMotor.setPower(-0.9);
            } else {
                marathonMap.kickerMotor.setPower(0.0);
            }

            boolean currentY = gamepad1.right_trigger_pressed;

            if (currentY && !lastY)
                intakeOn = !intakeOn;


            if (intakeOn) {
                marathonMap.intakeMotor.setPower(-0.8);
            } else {
                marathonMap.intakeMotor.setPower(0.0);
            }


            boolean currentIdle = gamepad1.y;
            {

                if (currentIdle) {
                    marathonMap.kickerMotor.setPower(0.9);
                    marathonMap.intakeMotor.setPower(0.9);
                } else {
                    marathonMap.kickerMotor.setPower(0.0);
                    marathonMap.intakeMotor.setPower(0.0);
                    double hoodposition = marathonMap.hood.getPosition();
                    double hoodincrement = 0.005;
                    if (gamepad1.dpad_up){
                        marathonMap.hood.setPosition(Math.abs(Math.min(0.45, hoodposition + hoodincrement)));
                    }else if(gamepad1.dpad_down){
                        marathonMap.hood.setPosition(Math.abs(Math.max(0.0, hoodposition - hoodincrement)));
                    }
                }


                //resetting imu yaw ----> options button+------------------------------------------------------------------------------------------------------------------------------.
                if (gamepad1.options) {
                    marathonMap.imu.resetYaw();
                }
            }
        }
    }

}

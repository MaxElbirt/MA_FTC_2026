package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous
public class AutoOnTicks extends LinearOpMode {

    RobotHardwareMap marathonMap = new RobotHardwareMap();

    @Override
    public void runOpMode() throws InterruptedException {

        marathonMap.init(hardwareMap);
        marathonMap.frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        marathonMap.frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        marathonMap.backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        marathonMap.backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        marathonMap.frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        marathonMap.frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        marathonMap.backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        marathonMap.backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        waitForStart();

        if (isStopRequested()) return;

        // דוגמא לשימוש בפונקציה
        driveForwardTicks(2000, 0.5);
    }

    // פונקציה לשיוט
    public void runShooterVelocity(double targetVelo) {
        marathonMap.shooterMotor1.setPower(
                (targetVelo / 1500) + ((targetVelo - marathonMap.shooterMotor1.getVelocity()) * 0.01)
        );
        telemetry.addData("Target Power",
                (targetVelo / 1500) + ((targetVelo - marathonMap.shooterMotor1.getVelocity()) * 0.0001)
        );
    }

    // פונקציה לנסיעה לפי ticks
    public void driveForwardTicks(int ticks, double power) {









//        marathonMap.frontLeftMotor.setPower(power);
//        marathonMap.frontRightMotor.setPower(power);
//        marathonMap.backLeftMotor.setPower(power);
//        marathonMap.backRightMotor.setPower(power);

        while (opModeIsActive() &&
                (marathonMap.frontLeftMotor.isBusy() ||
                        marathonMap.frontRightMotor.isBusy() ||
                        marathonMap.backLeftMotor.isBusy() ||
                        marathonMap.backRightMotor.isBusy())) {

            marathonMap.frontLeftMotor.setTargetPosition(ticks);
            marathonMap.frontRightMotor.setTargetPosition(ticks);
            marathonMap.backLeftMotor.setTargetPosition(ticks);
            marathonMap.backRightMotor.setTargetPosition(ticks);


            telemetry.addData("FL ticks", marathonMap.frontLeftMotor.getCurrentPosition());
            telemetry.addData("FR ticks", marathonMap.frontRightMotor.getCurrentPosition());
            telemetry.addData("BL ticks", marathonMap.backLeftMotor.getCurrentPosition());
            telemetry.addData("BR ticks", marathonMap.backRightMotor.getCurrentPosition());
            telemetry.update();
        }


    }


}
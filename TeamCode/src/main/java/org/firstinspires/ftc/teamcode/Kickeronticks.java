package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

@Autonomous
public class Kickeronticks extends LinearOpMode {

    DcMotorEx frontLeftMotor;
    DcMotorEx frontRightMotor;
    DcMotorEx backLeftMotor;
    DcMotorEx backRightMotor;


    RobotHardwareMap marathonMap = new RobotHardwareMap();


    public void runShooterVelocity(double targetVelo) {
        marathonMap.shooterMotor1.setPower((targetVelo / 1500) + ((targetVelo - marathonMap.shooterMotor1.getVelocity()) * 0.01));
        telemetry.addData("Target Power", (targetVelo / 1500) + ((targetVelo - marathonMap.shooterMotor1.getVelocity()) * 0.0001));
    }

    @Override
    public void runOpMode() {

        waitForStart();


        marathonMap.kickerMotor = hardwareMap.get(DcMotorEx.class, "kickerMotor");
        marathonMap.init(hardwareMap);


        frontLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        // Hardware mapping
        resetkiceronthicks2();
        tardetforticks(830, 0.5);

    }

    public void resetkiceronthicks2() {
        marathonMap.kickerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    public void tardetforticks(int ticks, double power) {


        marathonMap.kickerMotor.setTargetPosition(ticks);

        marathonMap.kickerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        marathonMap.kickerMotor.setPower(power);

        while (opModeIsActive());

        marathonMap.kickerMotor.isBusy(); {

marathonMap.kickerMotor.setPower(0);



telemetry.addData("current ticks", marathonMap.kickerMotor.getCurrentPosition());
telemetry.addData("taget", marathonMap.kickerMotor.getTargetPosition());
telemetry.addData("isbusy", marathonMap.kickerMotor.isBusy());
telemetry.update();
        }


    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous
public class ThreeBallsAutoRed extends LinearOpMode {

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


        marathonMap.init(hardwareMap);
        // Hardware mapping

        marathonMap.hood.setPosition(0);
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotorEx.class, "backRightMotor");


        // Reverse right side (typical drivetrain)
//        frontRightMotor.setDirection(DcMotorEx.Direction.REVERSE);
//        backRightMotor.setDirection(DcMotorEx.Direction.REVERSE);

        // Brake so robot stops cleanly
        frontLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        resetEncoders();



        telemetry.addLine("Ready");
        telemetry.update();

        waitForStart();

        moveTicks(-1000, 0.5);

        shoter();



    }

    public void resetEncoders() {

        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    public void moveTicks(int ticks, double power) {

        int flTarget = frontLeftMotor.getCurrentPosition() + ticks;
        int frTarget = frontRightMotor.getCurrentPosition() + ticks;
        int blTarget = backLeftMotor.getCurrentPosition() + ticks;
        int brTarget = backRightMotor.getCurrentPosition() + ticks;

        frontLeftMotor.setTargetPosition(flTarget);
        frontRightMotor.setTargetPosition(frTarget);
        backLeftMotor.setTargetPosition(blTarget);
        backRightMotor.setTargetPosition(brTarget);

        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(power);

        // Debug loop showing encoder ticks
        while (opModeIsActive() &&
                (frontLeftMotor.isBusy() ||
                        frontRightMotor.isBusy() ||
                        backLeftMotor.isBusy() ||
                        backRightMotor.isBusy())) {


            telemetry.addData("FL ticks", frontLeftMotor.getCurrentPosition());
            telemetry.addData("FR ticks", frontRightMotor.getCurrentPosition());
            telemetry.addData("BL ticks", backLeftMotor.getCurrentPosition());
            telemetry.addData("BR ticks", backRightMotor.getCurrentPosition());
            telemetry.update();


        }
    }

    public void shoter() {
        marathonMap.hood.setPosition(0.45);

        marathonMap.shooterMotor1.setVelocity(1550);
        sleep(3500);

        marathonMap.kickerMotor.setPower(0);
        sleep(100);


        marathonMap.kickerMotor.setPower(-0.65);
        sleep(500);

        marathonMap.kickerMotor.setPower(0);
        sleep(700);


        marathonMap.kickerMotor.setPower(-0.65);
        sleep(600);

        marathonMap.kickerMotor.setPower(0);
        sleep(800);


        marathonMap.kickerMotor.setPower(-1);
        sleep(600);


        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.kickerMotor.setPower(0);


    }
}

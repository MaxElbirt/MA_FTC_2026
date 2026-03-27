package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
@Autonomous
public class AutonomusByTicksPractice extends LinearOpMode {

    DcMotorEx frontLeftMotor;
    DcMotorEx frontRightMotor;
    DcMotorEx backLeftMotor;
    DcMotorEx backRightMotor;
    DcMotorEx shooterMotor1;
    DcMotorEx kickerMotor;
    Servo hood;
    DcMotor intakeMotor;
    Limelight3A limelight;


    public void runShooterVelocity(double targetVelo) {
        shooterMotor1.setPower((targetVelo / 1500) + ((targetVelo - shooterMotor1.getVelocity()) * 0.01));
        telemetry.addData("Target Power", (targetVelo / 1500) + ((targetVelo - shooterMotor1.getVelocity()) * 0.0001));
    }

    @Override
    public void runOpMode() {


        // Hardware mapping
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotorEx.class, "backRightMotor");
        shooterMotor1 = hardwareMap.get(DcMotorEx.class, "shooterMotor1");
        kickerMotor = hardwareMap.get(DcMotorEx.class, "kickerMotor");
        hood = hardwareMap.get(Servo.class, "hood");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();
        // Reverse right side (typical drivetrain)
        frontRightMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorEx.Direction.REVERSE);

        // Brake so robot stops cleanly
        frontLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        resetEncoders();

        telemetry.addLine("Ready");
        telemetry.update();


        waitForStart();

        moveTicks(-2100,0.65);
        sleep(200);

        turnTicks(-315, 0.65);
        limelightAlign( 2000);
        shooter(1300);

        turnTicks(870,0.65);
        sleep(200);

        moveBackAndPickUp(1260,0.7,700, 0.3);
        sleep(800);

        moveTicks(-1085,0.65);
        sleep(200);

        turnTicks(-900, 0.65);
        limelightAlign( 2000);
        shooter(1300);

        turnTicks(900, 0.65);
        sleep(200);

        moveright(-990, 0.65);
        sleep(200);

        moveBackAndPickUp(1250, 0.65, 1400, 0.3);

    }   public void resetEncoders() {

        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        kickerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        kickerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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

        while (opModeIsActive() &&
                (frontLeftMotor.isBusy()  &&
                        frontRightMotor.isBusy()
                        && backRightMotor.isBusy()
                        && backLeftMotor.isBusy())){

            telemetry.addData("FL ticks", frontLeftMotor.getCurrentPosition());
            telemetry.addData("FR ticks", frontRightMotor.getCurrentPosition());
            telemetry.addData("BL ticks", backLeftMotor.getCurrentPosition());
            telemetry.addData("BR ticks", backRightMotor.getCurrentPosition());
            telemetry.update();
            idle();

        }
    }

    public void turnTicks(int ticks, double power) {

        int flTarget = frontLeftMotor.getCurrentPosition() + ticks;
        int frTarget = frontRightMotor.getCurrentPosition() - ticks;
        int blTarget = backLeftMotor.getCurrentPosition() + ticks;
        int brTarget = backRightMotor.getCurrentPosition() - ticks;

        frontLeftMotor.setTargetPosition(flTarget);
        frontRightMotor.setTargetPosition(frTarget);
        backLeftMotor.setTargetPosition(blTarget);
        backRightMotor.setTargetPosition(brTarget);

        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);




        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(-power);

        while (opModeIsActive() &&
                (frontLeftMotor.isBusy()  &&
                        frontRightMotor.isBusy()
                        && backRightMotor.isBusy()
                        && backLeftMotor.isBusy())){

            telemetry.addData("FL ticks", frontLeftMotor.getCurrentPosition());
            telemetry.addData("FR ticks", frontRightMotor.getCurrentPosition());
            telemetry.addData("BL ticks", backLeftMotor.getCurrentPosition());
            telemetry.addData("BR ticks", backRightMotor.getCurrentPosition());
            telemetry.update();
            idle();

        }

    }



    public void moveright(int ticks, double power) {

        int flTarget = frontLeftMotor.getCurrentPosition() - ticks;
        int frTarget = frontRightMotor.getCurrentPosition() + ticks;
        int blTarget = backLeftMotor.getCurrentPosition() + ticks;
        int brTarget = backRightMotor.getCurrentPosition() - ticks;

        frontLeftMotor.setTargetPosition(flTarget);
        frontRightMotor.setTargetPosition(frTarget);
        backLeftMotor.setTargetPosition(blTarget);
        backRightMotor.setTargetPosition(brTarget);

        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);




        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(-power);

        while (opModeIsActive() &&
                (frontLeftMotor.isBusy()  &&
                        frontRightMotor.isBusy()
                        && backRightMotor.isBusy()
                        && backLeftMotor.isBusy())){


            telemetry.addData("FL ticks", frontLeftMotor.getCurrentPosition());
            telemetry.addData("FR ticks", frontRightMotor.getCurrentPosition());
            telemetry.addData("BL ticks", backLeftMotor.getCurrentPosition());
            telemetry.addData("BR ticks", backRightMotor.getCurrentPosition());
            telemetry.update();

            idle();

        }



    }

    public void moveBackAndPickUp(int ticks, double power, int ticksKicker, double powerKicker) {

        intakeMotor.setPower(-0.7);



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


        kickerWithTicks(ticksKicker, powerKicker);
        // Debug loop showing encoder ticks
        while (opModeIsActive() &&
                (frontLeftMotor.isBusy()  &&
                        frontRightMotor.isBusy()
                        && backRightMotor.isBusy()
                        && backLeftMotor.isBusy())) {
            kickerWithTicks(ticksKicker, powerKicker);

            telemetry.addData("FL ticks", frontLeftMotor.getCurrentPosition());
            telemetry.addData("FR ticks", frontRightMotor.getCurrentPosition());
            telemetry.addData("BL ticks", backLeftMotor.getCurrentPosition());
            telemetry.addData("BR ticks", backRightMotor.getCurrentPosition());
            telemetry.update();

            idle();


        }

    }




    public void getCloser(int ticks, double power, double Velocity) {

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

        limelightAlign( 2000);
        hood.setPosition(0.45);

        shooterMotor1.setVelocity(Velocity);
        sleep(2800);


        kickerWithTicks(1500, 0.25);

        sleep(2500);
        sleep(150);

        shooterMotor1.setVelocity(0);



        while (opModeIsActive() &&
                (frontLeftMotor.isBusy())) {
            idle();
        }


    }
    public void stopMotors(){
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        shooterMotor1.setVelocity(0);
        kickerMotor.setPower(0);
        hood.setPosition(0);
    }




    public void limelightAlign(long timeoutMillis) {

        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        long startTime = System.currentTimeMillis();
        double Kp = 0.02;
        double minPower = 0.02  ;

        while (opModeIsActive() && (System.currentTimeMillis() - startTime < timeoutMillis)) {
            LLResult result = limelight.getLatestResult();
            if (result != null && result.isValid()) {
                double tx = result.getTx();
                if (Math.abs(tx) < 1) break;

                double steerPower = (tx * Kp);
                if (steerPower > 0) steerPower += minPower;
                else steerPower -= minPower;

                frontLeftMotor.setPower(-steerPower);
                backLeftMotor.setPower(-steerPower);
                frontRightMotor.setPower(steerPower);
                backRightMotor.setPower(steerPower);
            } else {
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
            }
        }
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        runUsingEncoder();
    }

    public void kickerWithTicks(int ticks, double power) {

        int kickerTarget = kickerMotor.getCurrentPosition() - ticks;

        kickerMotor.setTargetPosition(kickerTarget);

        kickerMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        kickerMotor.setPower(-power);
    }


    public void turnAndShoot(int ticks, double power, int Velocity) {

        int flTarget = frontLeftMotor.getCurrentPosition() + ticks;
        int frTarget = frontRightMotor.getCurrentPosition() - ticks;
        int blTarget = backLeftMotor.getCurrentPosition() + ticks;
        int brTarget = backRightMotor.getCurrentPosition() - ticks;

        frontLeftMotor.setTargetPosition(flTarget);
        frontRightMotor.setTargetPosition(frTarget);
        backLeftMotor.setTargetPosition(blTarget);
        backRightMotor.setTargetPosition(brTarget);

        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);


        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(-power);


        while (opModeIsActive() &&
                (frontLeftMotor.isBusy())) {

            hood.setPosition(0.45);

            shooterMotor1.setVelocity(Velocity);
            sleep(3100);

            kickerWithTicks(1500, 0.3);
        }


    }

    public void shooter(int velocity) {
        hood.setPosition(0.45);
        shooterMotor1.setVelocity(1500);
        sleep(2500);
        kickerWithTicks(1500,0.2);
        sleep(3500);
        shooterMotor1.setVelocity(0);

    }


    public void runUsingEncoder(){
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}



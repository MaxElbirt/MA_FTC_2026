package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.hardware.limelightvision.LLResult;

@Autonomous
public class SixBallOnRedOnTicks extends LinearOpMode {

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
        marathonMap.limelight.pipelineSwitch(0);
        marathonMap.limelight.start();
        marathonMap.hood.setPosition(0);
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotorEx.class, "backRightMotor");
        marathonMap.kickerMotor = hardwareMap.get(DcMotorEx.class, "kickerMotor");

        // Reverse right side (typical drivetrain)
//        frontRightMotor.setDirection(DcMotorEx.Direction.REVERSE);
//        backRightMotor.setDirection(DcMotorEx.Direction.REVERSE);

        // Brake so robot stops cleanly
        frontLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        marathonMap.kickerMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        resetEncoders();
        runUsingIncoder();

        while (!isStarted() && !isStopRequested()) {
            LLResult result = marathonMap.limelight.getLatestResult();
            if (result != null && result.isValid()) {
                telemetry.addData("Limelight", "Target Locked!");
                telemetry.addData("TX", result.getTx());
            } else {
                telemetry.addLine("Limelight: Searching for target...");
            }
            telemetry.update();
        }


        telemetry.addLine("Ready");
        telemetry.update();

        waitForStart();




        moveTicks(-1100, 0.5);
        sleep(200);

        turnTicks(1420, 0.6);
        sleep(200);


        moveright(580, 0.5);
        sleep(200);


        moveBack(-1000, 0.6);
        sleep(200);


        getMore3Balls();
        sleep(200);


        movefowroed(1020, 0.8);
        sleep(200);


        turnforshoot3balls(882, 0.5);
        sleep(130);


//       turnForGettingTheLast3Balls(850, 0.6);
        turnTicks(1470, 0.6);
        sleep(200);






        MoveLeftForThe3Balls(910,0.5);
        sleep(200);



        moveBack(-1150, 0.6);
        sleep(200);



        getMore3Balls();

        sleep(200);


        movefowroed(400, 0.8);
        sleep(200);


        moverightForOpenTheGate(300 ,1);
        sleep(250);


        moveBack(-600, 0.8);
        sleep(200);

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
            LLResult result = marathonMap.limelight.getLatestResult();
            if (result != null && result.isValid()) {
                double tx = result.getTx();
                if (Math.abs(tx) < 1) break;

                double steerPower = (tx * Kp);
                if (steerPower > 0) steerPower += minPower;
                else steerPower -= minPower;

                frontLeftMotor.setPower(steerPower);
                backLeftMotor.setPower(steerPower);
                frontRightMotor.setPower(-steerPower);
                backRightMotor.setPower(-steerPower);
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
        runUsingIncoder();
    }

    public void resetEncoders() {


        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        marathonMap.kickerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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


        while (opModeIsActive() &&
                (frontLeftMotor.isBusy() &&
                        frontRightMotor.isBusy()
                        && backRightMotor.isBusy()
                        && backLeftMotor.isBusy()))

            telemetry.addData("FL ticks", frontLeftMotor.getCurrentPosition());
        telemetry.addData("FR ticks", frontRightMotor.getCurrentPosition());
        telemetry.addData("BL ticks", backLeftMotor.getCurrentPosition());
        telemetry.addData("BR ticks", backRightMotor.getCurrentPosition());
        telemetry.update();
        idle();



        limelightAlign(1000);

        marathonMap.hood.setPosition(0.45);

        marathonMap.shooterMotor1.setVelocity(1350);
        sleep(3100);
        marathonMap.kickerMotor.setPower(0);
        sleep(300);
        kickerWithTicks(1500, 0.34);


        marathonMap.shooterMotor1.setVelocity(0);

    }
    public void runUsingIncoder() {


        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
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

        // Debug loop showing encoder ticks
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

        // Debug loop showing encoder ticks
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

    public void moveBack(int ticks, double power) {

        marathonMap.intakeMotor.setPower(-1);


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

    public void getMore3Balls() {

        kickerWithTicks(650, 0.49);


    }


    public void movefowroed(int ticks, double power) {

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

    public void turnforshoot3balls(int ticks, double power) {

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


        // Debug loop showing encoder ticks
        while (opModeIsActive() &&
                (frontLeftMotor.isBusy() &&
                        frontRightMotor.isBusy()
                        && backRightMotor.isBusy()
                        && backLeftMotor.isBusy()))

            telemetry.addData("FL ticks", frontLeftMotor.getCurrentPosition());
        telemetry.addData("FR ticks", frontRightMotor.getCurrentPosition());
        telemetry.addData("BL ticks", backLeftMotor.getCurrentPosition());
        telemetry.addData("BR ticks", backRightMotor.getCurrentPosition());
        telemetry.update();
        idle();



        limelightAlign(1500);

        marathonMap.hood.setPosition(0.45);

        marathonMap.shooterMotor1.setVelocity(1440);
        sleep(3100);
        marathonMap.kickerMotor.setPower(0);
        sleep(300);
        kickerWithTicks(1500, 0.32);


        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.intakeMotor.setPower(0);





    }


    public void turnForGettingTheLast3Balls(int ticks, double power) {

        int flTarget = frontLeftMotor.getCurrentPosition() - ticks;
        int frTarget = frontRightMotor.getCurrentPosition() + ticks;
        int blTarget = backLeftMotor.getCurrentPosition() - ticks;
        int brTarget = backRightMotor.getCurrentPosition() + ticks;

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
        backLeftMotor.setPower(-power);
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

    public void MoveLeftForThe3Balls(int ticks ,double power) {
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

    public void moverightForOpenTheGate(int ticks , double power){


        int flTarget = frontLeftMotor.getCurrentPosition() + ticks;
        int frTarget = frontRightMotor.getCurrentPosition() - ticks;
        int blTarget = backLeftMotor.getCurrentPosition() - ticks;
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
        frontRightMotor.setPower(-power);
        backLeftMotor.setPower(-power);
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














    public boolean isRobotBusy() {
        int tolerance = 20;


        int flError = Math.abs(frontLeftMotor.getTargetPosition() - frontLeftMotor.getCurrentPosition());
        int frError = Math.abs(frontRightMotor.getTargetPosition() - frontRightMotor.getCurrentPosition());
        int blError = Math.abs(backLeftMotor.getTargetPosition() - backLeftMotor.getCurrentPosition());
        int brError = Math.abs(backRightMotor.getTargetPosition() - backRightMotor.getCurrentPosition());


        boolean isFLBusy = frontLeftMotor.isBusy() && flError > tolerance;
        boolean isFRBusy = frontRightMotor.isBusy() && frError > tolerance;
        boolean isBLBusy = backLeftMotor.isBusy() && blError > tolerance;
        boolean isBRBusy = backRightMotor.isBusy() && brError > tolerance;

        return isFLBusy || isFRBusy || isBLBusy || isBRBusy;
    }

    public void kickerWithTicks(int ticks, double power) {

        int kickerTarget = marathonMap.kickerMotor.getCurrentPosition() - ticks;

        marathonMap.kickerMotor.setTargetPosition(kickerTarget);

        marathonMap.kickerMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        marathonMap.kickerMotor.setPower(-power);

        while (opModeIsActive() && marathonMap.kickerMotor.isBusy()) {

            telemetry.addData("kicker ticks", marathonMap.kickerMotor.getCurrentPosition());
            telemetry.update();

            idle();
        }
    }
}





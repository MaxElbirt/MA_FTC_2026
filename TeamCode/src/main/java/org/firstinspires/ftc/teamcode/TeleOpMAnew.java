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
        LimelightSubSystem limelight = new LimelightSubSystem(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            limelight.update();


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

            //REV SHOOTER UP WHEN IN RANGE
            if(limelight.hasValidResult() && limelight.isOkToShoot()){
                double currentMotorPower = 0;
                marathonMap.shooterMotor1.setVelocity(1450);
                currentMotorPower = marathonMap.shooterMotor1.getPower();
                marathonMap.shooterMotor2.setPower(currentMotorPower);
            }


            //REV SHOOTER UP
            if(gamepad1.right_bumper){
                double currentMotorPower = 0;
                marathonMap.shooterMotor1.setVelocity(1450);
                currentMotorPower = marathonMap.shooterMotor1.getPower();
                marathonMap.shooterMotor2.setPower(currentMotorPower);
            }
//            else if(gamepad1.b && limelight.hasValidResult()){
//                rx = limelight.getSteeringToTarget();
//                rx = Math.max(-Constants.MAX_STEERING_POWER,Math.min(Constants.MAX_STEERING_POWER,rx));
//            }
            //STOP SHOOTER
            else if(gamepad1.x){
                double currentMotorPower = 0;
                marathonMap.shooterMotor1.setVelocity(0);
                currentMotorPower = marathonMap.shooterMotor1.getPower();
                marathonMap.shooterMotor2.setPower(currentMotorPower);
            }

            else if (gamepad1.b){
                marathonMap.hood.setPosition(0.0);
            }
            //CLEAR MECHANISM
         else if(gamepad1.a) {
                marathonMap.hood.setPosition(0.45);
                marathonMap.shooterMotor1.setVelocity(-1450);
                marathonMap.shooterMotor2.setPower(1450);
                marathonMap.kickerMotor.setPower(1);
                marathonMap.intakeMotor.setPower(1);
         }

            //EXPELL BALLS FROM INTAKE
            else if(gamepad1.right_trigger_pressed){
                marathonMap.intakeMotor.setPower(-1);
            }//EXPELL BALLS FROM KICKER
            else if(gamepad1.left_trigger_pressed){
                marathonMap.kickerMotor.setPower(-1);
            }//STOP BALL KICKER AND INTAKE
            else{
                marathonMap.kickerMotor.setPower(0);
                marathonMap.intakeMotor.setPower(0);
            }

            if (gamepad1.dpad_up) {
                marathonMap.hood.setPosition((0.45));
                double currentMotorPower = 0;
                marathonMap.shooterMotor1.setVelocity(1450);
                currentMotorPower = marathonMap.shooterMotor1.getPower();
                marathonMap.shooterMotor2.setPower(-currentMotorPower);
            }else if(gamepad1.dpad_down){
                marathonMap.hood.setPosition((0.45));
                double currentMotorPower = 0;
                marathonMap.shooterMotor1.setVelocity(1100);
                currentMotorPower = marathonMap.shooterMotor1.getPower();
                marathonMap.shooterMotor2.setPower(-currentMotorPower);
            }

//            marathonMap.hood.setPosition(0);

            //resetting imu yaw ----> options button+------------------------------------------------------------------------------------------------------------------------------.
            if (gamepad1.share){
                marathonMap.imu.resetYaw();
            }
            double hoodposition = marathonMap.hood.getPosition();
            telemetry.addData("hood pos: ", hoodposition);
            telemetry.addData("shooter1 velo: ", marathonMap.shooterMotor1.getVelocity());
            telemetry.addData("shooter2 velo", marathonMap.shooterMotor2.getVelocity());
            telemetry.addData("hood differential: ", Math.abs(marathonMap.shooterMotor1.getVelocity() - marathonMap.shooterMotor2.getVelocity() * -1) );
            telemetry.update();

        }

    }
}

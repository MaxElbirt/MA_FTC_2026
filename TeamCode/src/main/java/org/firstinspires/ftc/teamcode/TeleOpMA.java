package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOpMA extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        RobotHardwareMap marathonMap = new RobotHardwareMap();
        marathonMap.init(hardwareMap);


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {


            //start of shooter{
            // if L1 is pressed it activates the shooter.
            // if b is pressed on the controller then the shooter stops.
            if(gamepad1.right_bumper){
                marathonMap.shooterMotor1.setVelocity(1500);
                marathonMap.shooterMotor2.setVelocity(-1500);
            }else if(gamepad1.b){
                marathonMap.shooterMotor1.setVelocity(0);
                marathonMap.shooterMotor2.setVelocity(0);

            }
            // }end of shooter



            // start of hood{
            double hoodIncrement = 0.0025;
            // if arrow up is pressed on the controller, the hood goes up
            if (gamepad1.dpad_up) {
                // Get current position and add the increment, ensuring it doesn't exceed 0.45 (guessed value, check again)
                double newPosition = Math.min(0.45, marathonMap.hood.getPosition() + hoodIncrement);
                marathonMap.hood.setPosition(newPosition);
            // if arrow down is pressed on the controller, the hood goes down
            } else if (gamepad1.dpad_down) {
                //  Get current position and subtract the increment, ensuring it doesn't go below 0.0
                double newPosition = Math.max(0.0, marathonMap.hood.getPosition() - hoodIncrement);
                marathonMap.hood.setPosition(newPosition);
            }
            // }end of hood

            // beginning of reverse kicker{
            // if a is pressed on the controller then the kicker spins backwards to prevent clogging
            if(gamepad1.a) {
                marathonMap.kickerMotor.setPower(1);
            }else if (gamepad1.left_trigger_pressed){
                marathonMap.kickerMotor.setPower(-1);
            }else{
                marathonMap.kickerMotor.setPower(0);
            }
            // if a isn't pressed then the kicker stops
            // check for conflict with the forward kicker button
            // }end of reverse kicker



            //start of intake{
            // if right trigger is pressed on controller then intake activates
            if(gamepad1.right_trigger_pressed){
                marathonMap.intakeMotor.setPower(-1);
            }else{
                marathonMap.intakeMotor.setPower(0);
            }
            // }end of intake

            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            //Marathon Hardware Map
            marathonMap.frontLeftMotor.setPower(frontLeftPower);
            marathonMap.backLeftMotor.setPower(backLeftPower);
            marathonMap.frontRightMotor.setPower(frontRightPower);
            marathonMap.backRightMotor.setPower(backRightPower);
        }
    }
}

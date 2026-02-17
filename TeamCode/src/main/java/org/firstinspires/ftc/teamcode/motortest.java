package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class motortest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        RobotHardwareMap marathonMap = new RobotHardwareMap();
        marathonMap.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if(gamepad1.a){
                marathonMap.frontRightMotor.setPower(1);

            }else{
                marathonMap.frontRightMotor.setPower(0);
            }
            if(gamepad1.b){
                marathonMap.frontLeftMotor.setPower(1);
            }else{
                marathonMap.frontLeftMotor.setPower(0);
            }
            if(gamepad1.y){
                marathonMap.backRightMotor.setPower(1);
            }else{
                marathonMap.backRightMotor.setPower(0);
            }
            if(gamepad1.x){
                marathonMap.backLeftMotor.setPower(1);
            }else{
                marathonMap.frontRightMotor.setPower(0);

            }

        }
    }
}
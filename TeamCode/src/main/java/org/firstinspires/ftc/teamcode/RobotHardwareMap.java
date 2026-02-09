package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class RobotHardwareMap {

    public DcMotor frontLeftMotor = null;
    public DcMotor frontRightMotor = null;
    public DcMotor backLeftMotor = null;
    public DcMotor backRightMotor = null;
    public DcMotorEx shooterMotor1 = null;
    public DcMotorEx shooterMotor2 = null;
    public DcMotor kickerMotor = null;
    public DcMotor intakeMotor = null;
    public IMU imu = null;
    public HardwareMap localHardwareMap = null;
    public RobotHardwareMap(){}

    public void init(HardwareMap maHardwareMap){
        //Saving Local Copy Of Hardware Map
        localHardwareMap = maHardwareMap;

        //IMU Setup
        imu = maHardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logo = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection usb = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot onRobotOrientation = new RevHubOrientationOnRobot(logo,usb);
        imu.initialize(new IMU.Parameters(onRobotOrientation));
        imu.resetYaw();

        //Naming Motors -> Needs To Be The Same As Config On Driver Station
        frontLeftMotor = maHardwareMap.get(DcMotor.class, Constants.FRONT_LEFT_DRIVE_NAME);
        frontRightMotor = maHardwareMap.get(DcMotor.class, Constants.FRONT_RIGHT_DRIVE_NAME);
        backLeftMotor = maHardwareMap.get(DcMotor.class, Constants.BACK_LEFT_DRIVE_NAME);
        backRightMotor = maHardwareMap.get(DcMotor.class, Constants.BACK_RIGHT_DRIVE_NAME);
        kickerMotor = maHardwareMap.get(DcMotor.class, Constants.KICKER_NAME);
        shooterMotor1 = maHardwareMap.get(DcMotorEx.class, Constants.SHOOTER_NAME1);
        shooterMotor2 = maHardwareMap.get(DcMotorEx.class, Constants.SHOOTER_NAME2);
        intakeMotor = maHardwareMap.get(DcMotor.class, Constants.KICKER_NAME);

        //Drive Motor Direction Setting
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        //Shooter Motor Direction Setting
        shooterMotor1.setDirection(DcMotorEx.Direction.FORWARD);
        shooterMotor2.setDirection(DcMotorEx.Direction.REVERSE);
        //Motor Zero Power Behavior
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        kickerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Starting Power For All Motors
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        kickerMotor.setPower(0);
        intakeMotor.setPower(0);
        shooterMotor1.setPower(0);
        shooterMotor2.setPower(0);


    }

    public double getHeading(){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

}

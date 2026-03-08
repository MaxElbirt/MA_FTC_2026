package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HelperFuncs {

    private RobotHardwareMap marathonMap = new RobotHardwareMap();

    public HelperFuncs() {

    }

    public void init(HardwareMap hardwareMap) {
        marathonMap.init(hardwareMap);
    }

    // =========================
    // Existing shooter/intake funcs
    // =========================

    public void runShooterVelocity(double targetVelo) {
        marathonMap.shooterMotor1.setPower(
                (targetVelo / 1500) + ((targetVelo - marathonMap.shooterMotor1.getVelocity()) * 0.01)
        );
        telemetry.addData(
                "Target Power",
                (targetVelo / 1500) + ((targetVelo - marathonMap.shooterMotor1.getVelocity()) * 0.0001)
        );
    }

    public void setShooterVelocities(double velocity) {
        marathonMap.shooterMotor1.setVelocity(velocity);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower() * -1);
    }

    public void reset() {
        marathonMap.hood.setPosition(0.0);
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setVelocity(0);
    }

    public void expelBalls() {
        marathonMap.intakeMotor.setPower(1);
        marathonMap.kickerMotor.setPower(1);
        marathonMap.shooterMotor1.setVelocity(700);
        marathonMap.shooterMotor2.setVelocity(-700);
        marathonMap.hood.setPosition(0.45);
    }

    public void collectBalls() {
        marathonMap.intakeMotor.setPower(-1);
        marathonMap.kickerMotor.setPower(-0.5);
    }

    public void stopIntake() {
        marathonMap.intakeMotor.setPower(0);
        marathonMap.kickerMotor.setPower(0);
    }

    public void kickBalls() {
        marathonMap.kickerMotor.setPower(-1);
    }

    public void stopKicker() {
        marathonMap.kickerMotor.setPower(0);
    }

    public void stopShooter() {
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setVelocity(0);
    }

    public void setHoodPosition(double position) {
        position = Math.max(0.0, Math.min(0.45, position));
        marathonMap.hood.setPosition(position);
    }



    //autonomous helpers below



    public void resetDriveEncoders() {
        marathonMap.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        marathonMap.frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        marathonMap.backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        marathonMap.backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        driveRunUsingEncoder();
    }

    public void driveRunUsingEncoder() {
        marathonMap.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        marathonMap.frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        marathonMap.backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        marathonMap.backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void stopDrive() {
        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
    }

    private double percentToPower(double percent) {
        double power = Math.abs(percent) / 100.0;
        return Math.min(power, 1.0);
    }

    public int meToTicks(double me) {
        return (int) Math.round(me * Constants.TICKS_PER_ME);
    }

    public void moveByTicks(int frontLeftTicks, int frontRightTicks, int backLeftTicks, int backRightTicks, double speedPercent) {
        int flTarget = marathonMap.frontLeftMotor.getCurrentPosition() + frontLeftTicks;
        int frTarget = marathonMap.frontRightMotor.getCurrentPosition() + frontRightTicks;
        int blTarget = marathonMap.backLeftMotor.getCurrentPosition() + backLeftTicks;
        int brTarget = marathonMap.backRightMotor.getCurrentPosition() + backRightTicks;

        marathonMap.frontLeftMotor.setTargetPosition(flTarget);
        marathonMap.frontRightMotor.setTargetPosition(frTarget);
        marathonMap.backLeftMotor.setTargetPosition(blTarget);
        marathonMap.backRightMotor.setTargetPosition(brTarget);

        marathonMap.frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        marathonMap.frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        marathonMap.backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        marathonMap.backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double power = percentToPower(speedPercent);

        marathonMap.frontLeftMotor.setPower(power);
        marathonMap.frontRightMotor.setPower(power);
        marathonMap.backLeftMotor.setPower(power);
        marathonMap.backRightMotor.setPower(power);

        while (marathonMap.frontLeftMotor.isBusy()
                || marathonMap.frontRightMotor.isBusy()
                || marathonMap.backLeftMotor.isBusy()
                || marathonMap.backRightMotor.isBusy()) {

            telemetry.addData("FL", "%d / %d", marathonMap.frontLeftMotor.getCurrentPosition(), flTarget);
            telemetry.addData("FR", "%d / %d", marathonMap.frontRightMotor.getCurrentPosition(), frTarget);
            telemetry.addData("BL", "%d / %d", marathonMap.backLeftMotor.getCurrentPosition(), blTarget);
            telemetry.addData("BR", "%d / %d", marathonMap.backRightMotor.getCurrentPosition(), brTarget);
            telemetry.update();
        }

        stopDrive();
        driveRunUsingEncoder();
    }

    public void forwardME(double me) {
        forwardME(me, Constants.DRIVE_SPEED);
    }

    public void forwardME(double me, double speedPercent) {
        int ticks = meToTicks(me);
        moveByTicks(ticks, ticks, ticks, ticks, speedPercent);
    }

    public void backwardME(double me) {
        backwardME(me, Constants.DRIVE_SPEED);
    }

    public void backwardME(double me, double speedPercent) {
        int ticks = meToTicks(me);
        moveByTicks(-ticks, -ticks, -ticks, -ticks, speedPercent);
    }

    public void strafeRightME(double me) {
        strafeRightME(me, Constants.DRIVE_SPEED);
    }

    public void strafeRightME(double me, double speedPercent) {
        int ticks = meToTicks(me);
        moveByTicks(ticks, -ticks, -ticks, ticks, speedPercent);
    }

    public void strafeLeftME(double me) {
        strafeLeftME(me, Constants.DRIVE_SPEED);
    }

    public void strafeLeftME(double me, double speedPercent) {
        int ticks = meToTicks(me);
        moveByTicks(-ticks, ticks, ticks, -ticks, speedPercent);
    }

    public void turnRightTicks(int ticks) {
        turnRightTicks(ticks, Constants.TURN_SPEED);
    }

    public void turnRightTicks(int ticks, double speedPercent) {
        moveByTicks(ticks, -ticks, ticks, -ticks, speedPercent);
    }

    public void turnLeftTicks(int ticks) {
        turnLeftTicks(ticks, Constants.TURN_SPEED);
    }

    public void turnLeftTicks(int ticks, double speedPercent) {
        moveByTicks(-ticks, ticks, -ticks, ticks, speedPercent);
    }

    public void driveForward(double power, long wait){
        marathonMap.frontLeftMotor.setPower(power);
        marathonMap.frontRightMotor.setPower(power);
        marathonMap.backLeftMotor.setPower(power);
        marathonMap.backRightMotor.setPower(power);

        sleep(wait);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
    }

    public void driveBackwards(double power, long wait) {
        marathonMap.frontLeftMotor.setPower(-power);
        marathonMap.frontRightMotor.setPower(-power);
        marathonMap.backLeftMotor.setPower(-power);
        marathonMap.backRightMotor.setPower(-power);

        sleep(wait);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
    }

    public void driveRight(double power, long wait) {
        marathonMap.frontLeftMotor.setPower(power);
        marathonMap.frontRightMotor.setPower(-power);
        marathonMap.backLeftMotor.setPower(-power);
        marathonMap.backRightMotor.setPower(power);

        sleep(wait);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
    }

    public void driveLeft(double power, long wait){
        marathonMap.frontLeftMotor.setPower(-power);
        marathonMap.frontRightMotor.setPower(power);
        marathonMap.backLeftMotor.setPower(power);
        marathonMap.backRightMotor.setPower(-power);

        sleep(wait);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
    }

    public void turnRight(double power, long wait){
        marathonMap.frontLeftMotor.setPower(power);
        marathonMap.frontRightMotor.setPower(-power);
        marathonMap.backLeftMotor.setPower(power);
        marathonMap.backRightMotor.setPower(-power);

        sleep(wait);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
    }

    public void turnLeft(double power, long wait){
        marathonMap.frontLeftMotor.setPower(-power);
        marathonMap.frontRightMotor.setPower(power);
        marathonMap.backLeftMotor.setPower(-power);
        marathonMap.backRightMotor.setPower(power);

        sleep(wait);

        marathonMap.frontLeftMotor.setPower(0);
        marathonMap.frontRightMotor.setPower(0);
        marathonMap.backLeftMotor.setPower(0);
        marathonMap.backRightMotor.setPower(0);
    }

}
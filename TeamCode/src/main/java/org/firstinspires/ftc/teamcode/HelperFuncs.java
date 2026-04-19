package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * HelperFuncs: A utility library for common robot actions.
 */
public class HelperFuncs {

    private RobotHardwareMap marathonMap = new RobotHardwareMap();
    LimelightSubSystem limelight = new LimelightSubSystem(marathonMap);
    private Telemetry telemetry;
    private Gamepad gamepad1;

    public HelperFuncs() {
    }

    public void init(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad1) {
        marathonMap.init(hardwareMap);
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
    }

    // =========================
    // Shooter & Intake Subsystem
    // =========================

    public void runShooterVelocity(double targetVelo) {
        // Manual P-controller for shooter power
        double currentVelo = marathonMap.shooterMotor1.getVelocity();
        double power = (targetVelo / 1500.0) + ((targetVelo - currentVelo) * 0.01);
        
        marathonMap.shooterMotor1.setPower(power);
        // Motor 2 usually follows Motor 1 inverted for dual-flywheel setups
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower() * -1);

        if (telemetry != null) {
            telemetry.addData("Shooter Target", targetVelo);
            telemetry.addData("Shooter Actual", currentVelo);
            telemetry.addData("Shooter Power", power);
        }
    }

    public void setShooterVelocities(double velocity) {
        marathonMap.shooterMotor1.setVelocity(velocity);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower() * -1);
    }

    public void reset() {
        marathonMap.hood.setPosition(0.0);
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setVelocity(0);
        marathonMap.kickerMotor.setPower(0);
        marathonMap.intakeMotor.setPower(0);
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
        // Clamp position between 0 and 0.45 based on robot limits
        position = Math.max(0.0, Math.min(0.45, position));
        marathonMap.hood.setPosition(position);
    }

    // =========================
    // Autonomous Movement (Encoder)
    // =========================

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

    public void moveByTicks(int flTicks, int frTicks, int blTicks, int brTicks, double speedPercent) {
        int flTarget = marathonMap.frontLeftMotor.getCurrentPosition() + flTicks;
        int frTarget = marathonMap.frontRightMotor.getCurrentPosition() + frTicks;
        int blTarget = marathonMap.backLeftMotor.getCurrentPosition() + blTicks;
        int brTarget = marathonMap.backRightMotor.getCurrentPosition() + brTicks;

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

        while (marathonMap.frontLeftMotor.isBusy() || marathonMap.frontRightMotor.isBusy() || 
               marathonMap.backLeftMotor.isBusy() || marathonMap.backRightMotor.isBusy()) {
            if (telemetry != null) {
                telemetry.addData("Moving", "To Targets...");
                telemetry.addData("FL", "%d / %d", marathonMap.frontLeftMotor.getCurrentPosition(), flTarget);
                telemetry.addData("FR", "%d / %d", marathonMap.frontRightMotor.getCurrentPosition(), frTarget);
                telemetry.update();
            }
        }

        stopDrive();
        driveRunUsingEncoder();
    }

    public void forwardME(double me) { forwardME(me, Constants.DRIVE_SPEED); }
    public void forwardME(double me, double speedPercent) {
        int ticks = meToTicks(me);
        moveByTicks(ticks, ticks, ticks, ticks, speedPercent);
    }

    public void backwardME(double me) { backwardME(me, Constants.DRIVE_SPEED); }
    public void backwardME(double me, double speedPercent) {
        int ticks = meToTicks(me);
        moveByTicks(-ticks, -ticks, -ticks, -ticks, speedPercent);
    }

    public void strafeRightME(double me) { strafeRightME(me, Constants.DRIVE_SPEED); }
    public void strafeRightME(double me, double speedPercent) {
        int ticks = meToTicks(me);
        moveByTicks(ticks, -ticks, -ticks, ticks, speedPercent);
    }

    public void strafeLeftME(double me) { strafeLeftME(me, Constants.DRIVE_SPEED); }
    public void strafeLeftME(double me, double speedPercent) {
        int ticks = meToTicks(me);
        moveByTicks(-ticks, ticks, ticks, -ticks, speedPercent);
    }

    /**
     * Move Diagonally (Encoder Based)
     * @param meForward Positive for forward, negative for backward
     * @param meStrafe Positive for right, negative for left
     */
    public void moveDiagonalME(double meForward, double meStrafe, double speedPercent) {
        int forwardTicks = meToTicks(meForward);
        int strafeTicks = meToTicks(meStrafe);
        
        int flTicks = forwardTicks + strafeTicks;
        int frTicks = forwardTicks - strafeTicks;
        int blTicks = forwardTicks - strafeTicks;
        int brTicks = forwardTicks + strafeTicks;

        moveByTicks(flTicks, frTicks, blTicks, brTicks, speedPercent);
    }

    public void turnRightTicks(int ticks) { turnRightTicks(ticks, Constants.TURN_SPEED); }
    public void turnRightTicks(int ticks, double speedPercent) {
        moveByTicks(ticks, -ticks, ticks, -ticks, speedPercent);
    }

    public void turnLeftTicks(int ticks) { turnLeftTicks(ticks, Constants.TURN_SPEED); }
    public void turnLeftTicks(int ticks, double speedPercent) {
        moveByTicks(-ticks, ticks, -ticks, ticks, speedPercent);
    }

    // =========================
    // Time-Based Movement (Backup)
    // =========================

    public void driveForward(double power, long wait){
        marathonMap.frontLeftMotor.setPower(power);
        marathonMap.frontRightMotor.setPower(power);
        marathonMap.backLeftMotor.setPower(power);
        marathonMap.backRightMotor.setPower(power);
        sleep(wait);
        stopDrive();
    }

    public void driveBackwards(double power, long wait) {
        marathonMap.frontLeftMotor.setPower(-power);
        marathonMap.frontRightMotor.setPower(-power);
        marathonMap.backLeftMotor.setPower(-power);
        marathonMap.backRightMotor.setPower(-power);
        sleep(wait);
        stopDrive();
    }
    
    public void motorTest() {
        if(gamepad1 == null) return;

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
            marathonMap.backLeftMotor.setPower(0);
        }
    }
}

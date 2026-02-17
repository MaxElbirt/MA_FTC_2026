package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class HelperFuncs {

    private RobotHardwareMap marathonMap = new RobotHardwareMap();

    public HelperFuncs() {

    }

    public void init(HardwareMap hardwareMap) {
        marathonMap.init(hardwareMap);
    }

    public void setShooterVelocities(double velocity) {
        marathonMap.shooterMotor1.setVelocity(velocity);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower() * -1);
    }
    public void reset(){
        marathonMap.hood.setPosition(0.0);
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setVelocity(0);
    }
    public void expelBalls(){
        marathonMap.intakeMotor.setPower(1);
        marathonMap.kickerMotor.setPower(1);
        marathonMap.shooterMotor1.setVelocity(700);
        marathonMap.shooterMotor2.setVelocity(-700);
        marathonMap.hood.setPosition(0.45);
    }
    public void collectBalls(){
        marathonMap.intakeMotor.setPower(-1);
        marathonMap.kickerMotor.setPower(-0.5);
    }
    public void stopIntake(){
        marathonMap.intakeMotor.setPower(0);
        marathonMap.kickerMotor.setPower(0);
    }
    public void kickBalls(){
        marathonMap.kickerMotor.setPower(-1);
    }
    public void stopKicker(){
        marathonMap.kickerMotor.setPower(0);
    }
    public void stopShooter(){
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setVelocity(0);
    }


    public void setHoodPosition(double position) {
        position = Math.max(0.0, Math.min(0.45, position));
        marathonMap.hood.setPosition(position);
    }

    

}

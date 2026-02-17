package org.firstinspires.ftc.teamcode;

// You can import other FTC libraries here as you add more functions

public class HelperFuncs {

    // all speeds are optimized so put them all in positives
    private RobotHardwareMap marathonMap;

    public HelperFuncs(RobotHardwareMap robotHardwareMap) {
        this.marathonMap = robotHardwareMap;
    }

    // directions are optimized so forward is forward for both motors
    public void setShooterVelocities(double setVelocity){
        marathonMap.shooterMotor1.setVelocity(-setVelocity);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower());
        marathonMap.hood.setPosition(0.45);
    }

//    Enables the kicker at a constant power and intake motor at a custom set power
    public void feedRobot(double power){
        marathonMap.intakeMotor.setPower(-power);
        marathonMap.kickerMotor.setPower(-0.6);
    }

    public void resetRobot(){
        marathonMap.intakeMotor.setPower(0);
        marathonMap.kickerMotor.setPower(0);
        marathonMap.shooterMotor1.setVelocity(0);
        marathonMap.shooterMotor2.setPower(0);
        marathonMap.hood.setPosition(0);
    }

    public void expelBalls(){
        marathonMap.shooterMotor1.setVelocity(1400);
        marathonMap.shooterMotor2.setPower(marathonMap.shooterMotor1.getPower());
        marathonMap.hood.setPosition(0.45);
        marathonMap.intakeMotor.setPower(1);
        marathonMap.kickerMotor.setPower(1);
    }

}

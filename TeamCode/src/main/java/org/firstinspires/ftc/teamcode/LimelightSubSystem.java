package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class LimelightSubSystem {
    private RobotHardwareMap maRobotMap = new RobotHardwareMap();
    private Limelight3A limelight;
    private LLResult cachedResult;

    public LimelightSubSystem(HardwareMap hardwareMap) {
        maRobotMap.init(hardwareMap);
        limelight = maRobotMap.limelight;
    }

    //update method to make sure all data collected uniformly
    public void update() {
        cachedResult = limelight.getLatestResult();
    }

    public boolean hasValidResult() {
        return cachedResult != null && cachedResult.isValid();
    }

    //get distance from target (no directrion)
    public double getDistanceFromTarget() {
        if (!hasValidResult()) return -1;

        double ty = cachedResult.getTy();
        return (Constants.aprilTagHeightInch - Constants.mountingHeightInch) /
                Math.tan(Math.toRadians(Constants.mountingAngleDeg + ty));
    }

    //x axis steering correction
    public double getSteeringToTarget() {
        if (!hasValidResult()) return 0;

        double tx = cachedResult.getTx();
        return Constants.STEERING_KP * tx;
    }

    //getting botpose
    public double[] getFieldPosition() {
        if (!hasValidResult()) return null;

        Pose3D pose = cachedResult.getBotpose();
        if (pose == null) return null;

        return new double[]{pose.getPosition().x, pose.getPosition().y};
    }

    //is robot withing shooting range
    public boolean isOkToShoot() {
        return hasValidResult() && getDistanceFromTarget() < 98.4;
    }
}
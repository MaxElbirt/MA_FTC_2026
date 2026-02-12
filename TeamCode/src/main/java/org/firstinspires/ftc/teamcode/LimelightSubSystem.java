package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class LimelightSubSystem {
    public RobotHardwareMap maRobotMap = new RobotHardwareMap();
    public LimelightSubSystem(HardwareMap hardwareMap){
        maRobotMap.init(hardwareMap);
    }

    Limelight3A limelight = maRobotMap.limelight;

    //Calculate Distance From AprilTag
    public double getDistanceFromTarget(){
        LLResult llResult = limelight.getLatestResult();
        double ty = llResult.getTy();
        double distance = (Constants.aprilTagHeightInch - Constants.mountingHeightInch) /
                (Math.tan(Math.toRadians(Constants.mountingAngleDeg + ty)));

        return distance;
    }

    public double getSteeringToTarget(){
        LLResult llResult = limelight.getLatestResult();
        double tx = llResult.getTx();
        return Constants.steeringKP * tx;
    }

    public double[] getFieldPosition(){
        LLResult llResult = limelight.getLatestResult();
        Pose3D pose = llResult.getBotpose();
        double[] coordinates = {pose.getPosition().x, pose.getPosition().y};
        return coordinates;
    }

    public boolean isOkToShoot(){
        LLResult llResult = limelight.getLatestResult();
        return llResult.isValid() && getDistanceFromTarget() < 98.4;
    }
}

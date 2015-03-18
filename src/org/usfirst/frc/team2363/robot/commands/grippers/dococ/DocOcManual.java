package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DocOcManual extends Command {

    public DocOcManual() {
    	requires(Robot.leftDocOcArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.leftDocOcArm.setYawControlMethod(ControlMode.PercentVbus);
    	Robot.leftDocOcArm.setElevationControlMethod(ControlMode.PercentVbus);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.leftDocOcArm.setYaw(Robot.oi.getOpRightX() * -0.2);
//    		Robot.leftDocOcArm.setElevation(Robot.oi.getOpRightY() * 0.5);
    		double armDistance = Math.abs(DocOcArmPosition.LEFT_STOWED.getElevation() - leftDocOcArm.getElevationPosition());
    		double scaledArmDistance = armDistance / Math.abs(DocOcArmPosition.LEFT_STOWED.getElevation() - DocOcArmPosition.LEFT_FLOOR.getElevation());
    		Robot.leftDocOcArm.setElevation(Robot.oi.getOpRightY() * getScaledValue(scaledArmDistance));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftDocOcArm.setYaw(0);
		Robot.leftDocOcArm.setElevation(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.leftDocOcArm.setYaw(0);
		Robot.leftDocOcArm.setElevation(0);
    }
    
    private double getScaledValue(double power) {
    	double max = 1;
    	double min = 0.5;
    	return (max - min) * power + min;
    }
}

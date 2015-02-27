package org.usfirst.frc.team2363.robot.commands.grippers;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

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
    		Robot.leftDocOcArm.setYaw(Robot.oi.getOpRightX());
    		Robot.leftDocOcArm.setElevationSpeed(Robot.oi.getOpRightY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftDocOcArm.setYaw(0);
		Robot.leftDocOcArm.setElevationSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.leftDocOcArm.setYaw(0);
		Robot.leftDocOcArm.setElevationSpeed(0);
    }
}
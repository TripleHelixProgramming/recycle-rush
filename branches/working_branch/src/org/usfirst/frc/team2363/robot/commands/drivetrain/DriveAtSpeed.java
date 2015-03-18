package org.usfirst.frc.team2363.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.Robot.drivetrain;

/**
 *
 */
public class DriveAtSpeed extends PIDCommand {
	
    public DriveAtSpeed() {
    	super(" Drive at a speed", 0, 0.003, 0);
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setSetpoint(5.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drivetrain.arcadeDrive(0, 0);
    }

	@Override
	protected double returnPIDInput() {
//		SmartDashboard.putNumber("currentSpeed",(drivetrain.getLeftSpeed() + drivetrain.getRightSpeed()) / 2);
		return drivetrain.getLeftSpeed();
	}

	@Override
	protected void usePIDOutput(double output) {
//		drivetrain.arcadeDrive(-1, 0);
		drivetrain.arcadeDrive(-(getFeedForward() + output), 0);
	}
	
	protected double getFeedForward() {
		return getSetpoint() / 13.0;
	}
}

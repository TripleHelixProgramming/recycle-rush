package org.usfirst.frc.team2363.robot.commands.drivetrain;

import static org.usfirst.frc.team2363.robot.Robot.drivetrain;

/**
 *
 */
public class DriveStraightAtSpeed extends DriveAtSpeed {

    private double distance;

	public DriveStraightAtSpeed(double speed, double distance) {
    	super();
    	setSetpoint(speed);
    }

    protected void initialize() {
    	drivetrain.resetEncoders();
    }
    
    protected void usePIDOutput(double output) {
    	super.usePIDOutput(output);
    }
    
    protected boolean isFinished() {
    	return (distance - (drivetrain.getLeftPosition() + drivetrain.getRightPosition()) / 2) < 0;
    }
}

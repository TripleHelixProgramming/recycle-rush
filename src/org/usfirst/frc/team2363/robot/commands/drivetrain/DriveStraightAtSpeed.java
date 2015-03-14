package org.usfirst.frc.team2363.robot.commands.drivetrain;

import static org.usfirst.frc.team2363.robot.Robot.drivetrain;

/**
 *
 */
public class DriveStraightAtSpeed extends DriveAtSpeed {

    private double distance;
    private double maxSpeed;
    private final double P = 0.01;
    private final double dP = 3;

	public DriveStraightAtSpeed(double maxSpeed, double distance) {
    	super();
    	this.distance = distance;
    	this.maxSpeed = maxSpeed;
    }
	
	protected void execute() {
		double currentDistance = (drivetrain.getLeftPosition() + drivetrain.getRightPosition()) / 2;
    	double distanceToTarget = distance - currentDistance;
    	double scaledSpeed = dP * distanceToTarget;
    	
    	if (scaledSpeed > maxSpeed) {
    		setSetpoint(maxSpeed);
    	} else if (scaledSpeed < -maxSpeed) {
    		setSetpoint(-maxSpeed);
    	} else {
    		setSetpoint(scaledSpeed);
    	}
	}

    protected void initialize() {
    	drivetrain.resetEncoders();
    	drivetrain.resetHeading();
    }
    
    protected void usePIDOutput(double output) {
    	drivetrain.arcadeDrive(-(getFeedForward() + output), P * drivetrain.getHeading());
    }
    
    protected boolean isFinished() {
    	double currentDistance = (drivetrain.getLeftPosition() + drivetrain.getRightPosition()) / 2;
    	double distanceToTarget = distance - currentDistance;
    	return Math.abs(distanceToTarget) < 0.5;    }
}

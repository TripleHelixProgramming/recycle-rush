package org.usfirst.frc.team2363.robot.commands.drivetrain;

import static org.usfirst.frc.team2363.robot.Robot.drivetrain;
import static org.usfirst.frc.team2363.robot.Robot.visionProcessor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveAtSpeedToTote extends DriveAtSpeed {
	
	private static final double tP = 0.03;
	private static final double dP = 3;
	
	private double maxSpeed;
	private double maxTurnPower;
	private double distance;
	
	public DriveAtSpeedToTote(double maxSpeed, double maxTurnPower, double distance) {
		this.maxSpeed = maxSpeed;
		this.maxTurnPower = maxTurnPower;
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		drivetrain.resetEncoders();
	}
	@Override
	protected void execute() {
		double currentDistance = drivetrain.getLeftPosition();
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
	
	@Override
	protected boolean isFinished() {
		double currentDistance = drivetrain.getLeftPosition();
    	double distanceToTarget = distance - currentDistance;
    	return Math.abs(distanceToTarget) < 0.5;
	}
	
	@Override
	protected void usePIDOutput(double output) {
		if (visionProcessor.getCenter() * tP > maxTurnPower) {
    		drivetrain.arcadeDrive(-output, maxTurnPower);
    	} else if (visionProcessor.getCenter() * tP < -maxTurnPower) {
    		drivetrain.arcadeDrive(-output, -maxTurnPower);
    	} else {
    		drivetrain.arcadeDrive(-output, visionProcessor.getCenter() * tP);
    	}
    	SmartDashboard.putNumber("Turn Power", visionProcessor.getCenter() * tP);
    }
}

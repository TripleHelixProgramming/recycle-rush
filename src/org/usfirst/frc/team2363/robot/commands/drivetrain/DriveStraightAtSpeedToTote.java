package org.usfirst.frc.team2363.robot.commands.drivetrain;

import static org.usfirst.frc.team2363.robot.Robot.drivetrain;
import static org.usfirst.frc.team2363.robot.Robot.visionProcessor;


/**
 *
 */
public class DriveStraightAtSpeedToTote extends DriveStraightAtSpeed {

	private double maxTurnPower;
	private final double tP =  0.03;
	public DriveStraightAtSpeedToTote(double maxSpeed, double distance, double maxTurnPower) {
		super(maxSpeed, distance);
		this.maxTurnPower = maxTurnPower;
	}

	protected void usePIDOutput(double output) {
		if (visionProcessor.getCenter() < 1 && visionProcessor.getCenter() > -1) {
			super.usePIDOutput(output);
		} else {
			if (visionProcessor.getCenter() * tP > maxTurnPower) {
				drivetrain.arcadeDrive(-output, maxTurnPower);
			} else if (visionProcessor.getCenter() * tP < -maxTurnPower) {
				drivetrain.arcadeDrive(-output, -maxTurnPower);
			} else {
				drivetrain.arcadeDrive(-output, visionProcessor.getCenter() * tP);
			}
		}
	}
}

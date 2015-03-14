package org.usfirst.frc.team2363.robot.commands.grippers.dococ;


import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.PIDCommand;
import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;
/**
 *
 */
public class ElevateArmToOnboard extends PIDCommand {
	
	private DocOcArmPosition position;
	private double maxSpeed;
	
	private final double P = 0.5;
	
	public ElevateArmToOnboard(DocOcArmPosition position, double maxSpeed) {
		super(0, 0.001, 0);
		this.position = position;
		this.maxSpeed = maxSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		leftDocOcArm.setElevationControlMethod(ControlMode.PercentVbus);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double currentPosition = leftDocOcArm.getElevationPosition();
		double distanceToTarget = position.getElevation() - currentPosition;
		double speed = distanceToTarget * P;
		if (speed > maxSpeed) {
			setSetpoint(maxSpeed);
		} else if (speed < -maxSpeed) {
			setSetpoint(-maxSpeed); 
		} else {
			setSetpoint(speed);
		}
			
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double currentPosition = leftDocOcArm.getElevationPosition();
		double distanceToTarget = position.getElevation() - currentPosition;
		return Math.abs(distanceToTarget) < 10 
				|| leftDocOcArm.getElevationCurrent() > 70;
	}

	// Called once after isFinished returns true
	protected void end() {
		leftDocOcArm.setElevation(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		leftDocOcArm.setElevation(0);
	}

	@Override
	protected double returnPIDInput() {
		return leftDocOcArm.getElevationSpeed();
	}

	@Override
	protected void usePIDOutput(double output) {
		double armDistance = Math.abs(DocOcArmPosition.LEFT_STOWED.getElevation() - leftDocOcArm.getElevationPosition());
		double scaledArmDistance = armDistance / Math.abs(DocOcArmPosition.LEFT_STOWED.getElevation() - DocOcArmPosition.LEFT_FLOOR.getElevation());
		
		leftDocOcArm.setElevation(output * getScaledValue(scaledArmDistance));
	}
	
	private double getScaledValue(double power) {
		double max = 1;
		double min = 0.5;
		return (max - min) * power + min;
	}
}

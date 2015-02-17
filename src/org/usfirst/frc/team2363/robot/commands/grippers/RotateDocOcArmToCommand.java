package org.usfirst.frc.team2363.robot.commands.grippers;

import org.usfirst.frc.team2363.robot.subsystems.DocOcArm;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateDocOcArmToCommand extends Command {

	private DocOcArm arm;
	private DocOcArmPosition position;
	private double maxSpeed;
	
	private static final double P = 0.2;

	public RotateDocOcArmToCommand(DocOcArm arm, DocOcArmPosition position, double maxSpeed) {
		this.arm = arm;
		this.position = position;
		this.maxSpeed = maxSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		arm.setControlMethod(ControlMode.Speed);
		arm.setControlEnabled(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double distanceTo = position.getYaw() - arm.getYawPosition();
		double distanceScalar = distanceTo * P;
		if (distanceScalar > maxSpeed) {
			arm.setYawSpeed(maxSpeed);
		} else if (distanceScalar < -maxSpeed) {
			arm.setYawSpeed(-maxSpeed);
		} else {
			arm.setYawSpeed(distanceScalar);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return arm.isYawOnTarget(position);
	}

	// Called once after isFinished returns true
	protected void end() {
		arm.setYawSpeed(0);
//		arm.setControlEnabled(false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		arm.setYawSpeed(0);
//		arm.setControlEnabled(false);
	}
}

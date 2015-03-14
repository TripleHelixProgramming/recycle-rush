package org.usfirst.frc.team2363.robot.commands.elevators.tote;

import static org.usfirst.frc.team2363.robot.Robot.toteElevator;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.BrakePosition;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevateAtSpeed extends PIDCommand {

	private double maxUp;
	private double maxDown;
	private double p = 3;

	private ElevatorPosition position;
	
	public ElevateAtSpeed(ElevatorPosition position) {
		this(position, 15, -15);
	}
	
	public ElevateAtSpeed(ElevatorPosition position, double speed) {
		this(position, speed, -speed);
	}
	
	public ElevateAtSpeed(ElevatorPosition position, double maxUp, double maxDown) {
		super(0, 0.01, 0);
		requires(toteElevator);
		this.position = position;
		this.maxUp = maxUp;
		this.maxDown = maxDown;
	}

	protected void initialize() {
		toteElevator.setBrake(BrakePosition.OFF);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double currentDistance = toteElevator.getPosition();
		double distanceToTarget = position.getHeight() - currentDistance;
		double scaledSpeed = p * distanceToTarget;

		//Speed Scaling
		if (scaledSpeed > maxUp) {
			setSetpoint(maxUp);
		} else if (scaledSpeed < maxDown) {
			setSetpoint(maxDown);
		} else {
			setSetpoint(scaledSpeed);
		}

		//Limit Switch Check
		if (toteElevator.isAtBottomLimit()) {
			toteElevator.resetAtBottom();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double currentDistance = toteElevator.getPosition();
		double distanceToTarget = position.getHeight() - currentDistance;
		
		return Math.abs(distanceToTarget) < 0.25
				|| (Robot.bearHuggerElevator.isAtTopLimit() && getSetpoint() > 0)
				|| toteElevator.aboveWorkingCurrent();
	}

	// Called once after isFinished returns true
	protected void end() {
		toteElevator.setPower(0);
		toteElevator.setBrake(BrakePosition.ENGAGED);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		toteElevator.setPower(0);
	}

	@Override
	protected double returnPIDInput() {
		return toteElevator.getElevatorSpeed();
	}

	@Override
	protected void usePIDOutput(double output) {
		toteElevator.setPower(output);
		SmartDashboard.putNumber("Tote Elevator Power", output);
	}
}

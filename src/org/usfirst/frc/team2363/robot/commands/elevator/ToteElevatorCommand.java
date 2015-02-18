package org.usfirst.frc.team2363.robot.commands.elevator;

import org.usfirst.frc.team2363.robot.RobotMap;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.BrakePosition;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.Robot.toteElevator;

/**
 *
 */
public class ToteElevatorCommand extends PIDCommand {

	private ElevatorPosition position;

	public ToteElevatorCommand(ElevatorPosition position) {
//		super(1.5, 0, 1);
		super(0.5, 0, 0);
		requires(toteElevator);
		this.position = position;
		setSetpoint(position.getHeight());
		getPIDController().setAbsoluteTolerance(0.1);
		getPIDController().setOutputRange(-0.75, 1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		toteElevator.setBrake(BrakePosition.OFF);
		getPIDController().enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (toteElevator.isAtBottomLimit()) {
			toteElevator.resetAtBottom();
		} 
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return getPIDController().onTarget()
				|| toteElevator.aboveWorkingCurrent();
	}

	// Called once after isFinished returns true
	protected void end() {
		if (position != ElevatorPosition.GROUND) {
			toteElevator.setBrake(BrakePosition.ENGAGED);
		}
		getPIDController().disable();
		toteElevator.setPower(0);
		SmartDashboard.putString("Current elevator position", position.getName());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.getPIDController().disable();
		toteElevator.setPower(0);
	}

	@Override
	protected double returnPIDInput() {
		return toteElevator.getPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
//		if (output > 0 && toteElevator.isAtTopLimit()
//				|| output < 0 && toteElevator.isAtBottomLimit()) {
		if (output < 0 && toteElevator.isAtBottomLimit()) {
			toteElevator.setPower(0);
			cancel();
		} else {
			SmartDashboard.putNumber("Elevator Power", output);
			toteElevator.setPower(output);
		}
	}	
}

package org.usfirst.frc.team2363.robot.commands.elevators;

import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;

import static org.usfirst.frc.team2363.robot.Robot.bearHuggerElevator;

public class ElevateBothAtSpeed extends ElevateAtSpeed {

	public ElevateBothAtSpeed(ElevatorPosition position) {
		super(position);
		requires(bearHuggerElevator);
	}
	
	public ElevateBothAtSpeed(ElevatorPosition position, double elevatorSpeed) {
		super(position, elevatorSpeed);
		requires(bearHuggerElevator);
	}
	
	@Override
	protected void execute() {
		super.execute();
		bearHuggerElevator.drive(-BearHuggerElevator.BEAR_HUGGER_ELEVATOR_POWER);
	}

	@Override
	protected void end() {
		super.end();
		bearHuggerElevator.drive(0);
	}
	
	@Override
	protected void interrupted() {
		super.interrupted();
		bearHuggerElevator.drive(0);
	}
}

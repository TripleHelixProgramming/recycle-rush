package org.usfirst.frc.team2363.robot.commands.elevators;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.WaitForToteCommand;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.BrakePosition;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutomatedHPThing extends CommandGroup {

	public  AutomatedHPThing() {
//		addParallel(new DriveBearHuggerToTopLimit());
		addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
		addParallel(new ElevateBothAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
		addSequential(new WaitForToteCommand());
		addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE));
		addSequential(new ElevateBothAtSpeed(ElevatorPosition.GROUND));
		addSequential(new ElevateAtSpeed(ElevatorPosition.TWO_TOTE_CARRY));
		addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN), 0.5);
	}
		
	public void end() {
		Robot.toteElevator.setBrake(BrakePosition.ENGAGED);
	}
}

package org.usfirst.frc.team2363.robot.commands.elevator;

import org.usfirst.frc.team2363.robot.commands.grippers.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;
import org.usfirst.frc.team2363.robot.util.NoneCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToPosition extends CommandGroup {

	public  GoToPosition(ElevatorPosition position) {
		if (position == ElevatorPosition.GROUND) {
			addSequential(new NoneCommand());
		} else {
			addSequential(new ElevateAtSpeed(position));
			addParallel(new RollerGripperCommand(0, ClawPosition.OPEN));
		}
	}
}

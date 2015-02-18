package org.usfirst.frc.team2363.robot.commands.elevator;

import org.usfirst.frc.team2363.robot.commands.grippers.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DefaultGround extends CommandGroup {

	public  DefaultGround() {
		addParallel(new RollerGripperCommand(0, ClawPosition.CLOSE));
		addSequential(new WaitCommand(0.5));
		addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
	}
}

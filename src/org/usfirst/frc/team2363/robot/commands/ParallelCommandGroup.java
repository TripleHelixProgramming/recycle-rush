package org.usfirst.frc.team2363.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ParallelCommandGroup extends CommandGroup {

	public ParallelCommandGroup(Command... commands) {
		for (Command command : commands) {
			addParallel(command);
		}
	}
}

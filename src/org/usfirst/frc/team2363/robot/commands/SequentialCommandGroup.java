package org.usfirst.frc.team2363.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SequentialCommandGroup extends CommandGroup {

	public SequentialCommandGroup(Command... commands) {
		for (Command command : commands) {
			addSequential(command);
		}
	}
}

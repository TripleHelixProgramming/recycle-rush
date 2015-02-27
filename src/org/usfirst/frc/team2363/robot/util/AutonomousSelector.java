package org.usfirst.frc.team2363.robot.util;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomousSelector extends BinarySelector {

	private List<Command> commands = new ArrayList<>();

	public AutonomousSelector(Button... buttons) {
		super(buttons);
	}


	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	public Command getSelectedCommand() {
		if (getSelectedNumber() > commands.size() || commands.size() == 0) {
			return new NoneCommand();
		} else {
			return commands.get(getSelectedNumber());
		}
	}





}

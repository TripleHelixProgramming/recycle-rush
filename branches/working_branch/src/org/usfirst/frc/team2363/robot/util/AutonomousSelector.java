package org.usfirst.frc.team2363.robot.util;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.buttons.Button;

public class AutonomousSelector extends BinarySelector {

	private List<SelectableCommand> commands = new ArrayList<>();

	public AutonomousSelector(Button... buttons) {
		super(buttons);
	}


	public void setCommands(List<SelectableCommand> commands) {
		this.commands = commands;
	}

	public SelectableCommand getSelectedCommand() {
		if (getSelectedNumber() > commands.size() || commands.size() == 0) {
			return new NoneCommand();
		} else {
			return commands.get(getSelectedNumber());
		}
	}





}

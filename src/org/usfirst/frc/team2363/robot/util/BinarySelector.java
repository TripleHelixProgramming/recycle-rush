package org.usfirst.frc.team2363.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;

public class BinarySelector {
	private Button[] buttons;
	
	/**
	 * @param buttons The list of buttons from least significant to most significant
	 */
	public BinarySelector(Button... buttons) {
		this.buttons = buttons;
	}
	
	public int getSelectedNumber() {
		int sum = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].get()) {
				sum += Math.pow(2, i);
			}
		}
		return sum;
	}
}

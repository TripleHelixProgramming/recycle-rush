package org.usfirst.frc.team2363.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;

public class BinarySelector {
	private Button autoSelector1;
	private Button autoSelector2;
	private Button autoSelector3;

	public BinarySelector(Button button1, Button button2, Button button3) {
		autoSelector1 = button1;
		autoSelector2 = button2;
		autoSelector3 = button3;
		
	}
	
	public int getSelectedNumber() {
		return (autoSelector1.get() ? 1 : 0) 
				+ 2 * (autoSelector2.get() ? 1 : 0) 
				+ 4 * (autoSelector3.get() ? 1 : 0);
	}
}

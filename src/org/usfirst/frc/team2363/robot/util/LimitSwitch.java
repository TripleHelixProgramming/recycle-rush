package org.usfirst.frc.team2363.robot.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class LimitSwitch extends Trigger {
	private DigitalInput dio;

	public LimitSwitch(int channel) {
		dio = new DigitalInput(channel);
	}

	@Override
	public boolean get() {
		return dio.get();
	}

}

package org.usfirst.frc.team2363.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class PS4Controller extends Joystick {
	private Joystick ps4Controller;

	//Joystick Axis
	private static final int LEFT_STICK_X = 0;
	private static final int LEFT_STICK_Y = 1;
	private static final int RIGHT_STICK_X = 2;
	private static final int LEFT_TRIGGER = 3;
	private static final int RIGHT_TRIGGER = 4;
	private static final int RIGHT_STICK_Y = 5;

	public enum TriggerSide {
		RIGHT,
		LEFT,
	}


	public PS4Controller(int port) {
		super(port);
		ps4Controller = new Joystick(port);
	}

	@Override
	public double getThrottle() {
		return ps4Controller.getRawAxis(LEFT_STICK_Y);
	}

	@Override
	public double getX(Hand hand) {
		return ps4Controller.getRawAxis(RIGHT_STICK_X);
	}

	@Override
	public double getY(Hand hand) {
		return ps4Controller.getRawAxis(LEFT_STICK_Y);
	}

	public double getZ(TriggerSide side) {
		if (side == TriggerSide.RIGHT) {
			return ps4Controller.getRawAxis(RIGHT_TRIGGER);
		} else {
			return ps4Controller.getRawAxis(LEFT_TRIGGER);
		}
	}
}

package org.usfirst.frc.team2363.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorPanel extends Joystick {
	public OperatorPanel(int port) {
		super(port);
		operatorControl = new Joystick(port);
	}

	private Joystick operatorControl; {

	}

	//Control Panel Buttons
	public static final int GROUND_BUTTON = 1;
	public static final int CARRY_PLACE_BUTTON = 2;
	public static final int STEP_BUTTON = 3;
	public static final int ONE_TOTE_BUTTON = 4;
	public static final int TWO_TOTE_BUTTON = 5;
	public static final int HUMAN_PLAYER_BUTTON = 6;

	/**
	public static final int Btn8 = 7;
	public static final int Btn9 = 8;
	public static final int Btn10 = 9;
	public static final int Btn11 = 10;
	public static final int Btn12 = 11;
	public static final int Btn13 = 12;
	public static final int Btn14 = 13;
	public static final int Btn15 = 14;
	public static final int Btn16 = 15;
	public static final int Btn17 = 16;
	public static final int Btn18 = 17;
	public static final int Btn19 = 18;
	public static final int Btn20 = 19;
	public static final int Btn21 = 20;
	public static final int Btn22 = 21;
	public static final int Btn23 = 22;
	public static final int Btn24 = 23;
	public static final int Btn25 = 24;
	public static final int Btn26 = 25;
	public static final int Btn27 = 26;
	public static final int Btn28 = 27;
	 */

}

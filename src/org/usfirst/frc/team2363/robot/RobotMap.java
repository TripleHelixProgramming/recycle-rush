package org.usfirst.frc.team2363.robot;

public class RobotMap {
	
	//Constants
	public static final double HIGH_SPEED_SCALING = 0.5;
	public static final double LOW_SPEED_SCALING = 0.9;
	
	//Joysticks
	public static final int PS4_PORT = 0;
	
	//Joystick Buttons
	public static final int SHIFT_UP_BUTON_PORT = 5; //R1
	public static final int SHIFT_DOWN_BUTTON_PORT = 4; //R2
	
	//Joystick Axis
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int RIGHT_STICK_X = 2;
	public static final int RIGHT_TRIGGER = 3;
	public static final int LEFT_TRIGGER = 4;
	public static final int RIGHT_STICK_Y = 5;
	
	//Motor Controllers
	public static final int FRONT_LEFT_TALON_CHANNEL = 0;
	public static final int FRONT_RIGHT_TALON_CHANNEL = 1;
	public static final int REAR_LEFT_TALON_CHANNEL = 2;
	public static final int REAR_RIGHT_TALON_CHANNEL = 3;
	public static final int ELEVATOR_TALON_CHANNEL = 4;
	
	//Solenoids
	public static final int SHIFT_SOLENOID_1_PORT = 1;
	public static final int SHIFT_SOLENOID_2_PORT = 2;
	
	//Encoders
	public static final int ELEVATOR_ENCODER_CHANNEL_A = 1;
	public static final int ELEVATOR_ENCODER_CHANNEL_B = 2;
}

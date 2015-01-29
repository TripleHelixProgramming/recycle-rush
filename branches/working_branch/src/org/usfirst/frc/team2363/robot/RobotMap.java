package org.usfirst.frc.team2363.robot;

public class RobotMap {
	
	//Constants
	public static final double HIGH_SPEED_SCALING = 0.5;
	public static final double LOW_SPEED_SCALING = 0.9;
	public static final double ELEVATOR_DISTANCE_PER_PULSE = 0.0111875510;
	public static final int ELEVATOR_MOTOR_MAX_CURRENT = 70;
	public static final double ROBOT_TOP_SPEED = 11;
	
	//Joysticks
	public static final int PS4_PORT = 0;
	
	//Joystick Buttons
	public static final int SHIFT_DOWN_BUTTON = 5; //R1
	public static final int SHIFT_UP_BUTTON = 6; //L1
	public static final int CARRY_BUTTON = 7; // L2
	public static final int SCORE_BUTTON = 8; // R2
	public static final int THROTTLE_SCALING_BUTTON = 14; // Touchpad ;)
	
	//Joystick Axis
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int RIGHT_STICK_X = 2;
	public static final int LEFT_TRIGGER = 3;
	public static final int RIGHT_TRIGGER = 4;
	public static final int RIGHT_STICK_Y = 5;
	
	//Motor Controllers
	public static final int FRONT_LEFT_TALON_CHANNEL = 0;
	public static final int FRONT_RIGHT_TALON_CHANNEL = 1;
	public static final int REAR_LEFT_TALON_CHANNEL = 2;
	public static final int REAR_RIGHT_TALON_CHANNEL = 3;
	public static final int ELEVATOR_TALON_CHANNEL = 4;
	public static final int LEFT_ROLLER_GRIPPER_TALON_CHANNEL = 5;
	public static final int RIGHT_ROLLER_GRIPPER_TALON_CHANNEL = 6;
	
	//Solenoids
	public static final int SHIFT_SOLENOID_1_CHANNEL = 0;
	public static final int SHIFT_SOLENOID_2_CHANNEL = 1;
	public static final int INTERNAL_GRIPPER_SOLENOID_CHANNEL = 2;
	public static final int ROLLER_GRIPPER_SOLENOID_CHANNEL = 4;
	public static final int BRAKE_SOLENOID_CHANNEL = 7;
	
	
	//Encoders
	public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_A = 0;
	public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_B = 1;
	public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A = 2;
	public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B = 3;
	public static final int ELEVATOR_ENCODER_CHANNEL_A = 4;
	public static final int ELEVATOR_ENCODER_CHANNEL_B = 5;
	
	//Limit Switches
	public static final int ELEVATOR_BOTTOM_LIMIT_CHANNEL = 6;
	public static final int ELEVATOR_TOP_LIMIT_CHANNEL = 7;
	
	//PDP
	public static final int ELEVATOR_PDP_CHANNEL = 0;
}

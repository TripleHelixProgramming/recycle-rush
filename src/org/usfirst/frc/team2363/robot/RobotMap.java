package org.usfirst.frc.team2363.robot;

public class RobotMap {

	//Constants
	public static final double HIGH_SPEED_SCALING = 0.5;
	public static final double LOW_SPEED_SCALING = 0.9;
	public static final double TOTE_ELEVATOR_DISTANCE_PER_PULSE = 0.015;
	public static final double BEAR_HUGGER_ELEVATOR_DISTANCE_PER_PULSE = 0.030;
	public static final int TOTE_ELEVATOR_MOTOR_MAX_CURRENT = 70;
	public static final double ROBOT_TOP_SPEED = 11;
	public static final double ROLLER_POWER = -1;
	public static final double BEAR_HUGGER_ELEVATOR_TOP_LIMIT = 28;

	//Joysticks
	public static final int PS4_PORT = 0;
	public static final int OPERATOR_PORT = 1;
	public static final int MANUAL_OPERATOR_PORT = 3;

	//Operator Buttons
	public static final int GROUND_BUTTON = 10;
	public static final int CARRY_PLACE_BUTTON = 9;
	public static final int STEP_BUTTON = 8;
	public static final int ONE_TOTE_BUTTON = 7;
	public static final int TWO_TOTE_BUTTON = 6;
	public static final int HUMAN_PLAYER_BUTTON = 11;

	//PS4 Buttons
	public static final int SHIFT_DOWN_BUTTON = 5; // R1
	public static final int SHIFT_UP_BUTTON = 6; // L1
	public static final int INTAKE_BUTTON = 7; // R2
	public static final int EJECT_BUTTON = 8; // L2
	public static final int CARRY_BUTTON = 4; // TRIANGLE
	public static final int SCORE_BUTTON = 2; // X
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
	public static final int TOTE_ELEVATOR_TALON_CHANNEL_A = 4;
	public static final int TOTE_ELEVATOR_TALON_CHANNEL_B = 5;
	public static final int BEAR_HUGGER_ELEVATOR_TALON_CHANNEL = 7;
	public static final int LEFT_ROLLER_GRIPPER_TALON_CHANNEL = 9;
	public static final int RIGHT_ROLLER_GRIPPER_TALON_CHANNEL = 8;
	
	//CAN Motor Controllers
	public static final int LEFT_DOC_OC_ARM_YAW_CHANNEL = 0;
	public static final int LEFT_DOC_OC_ARM_ELEVATE_CHANNEL = 1;
//	public static final int RIGHT_DOC_OC_ARM_YAW_CHANNEL = 2;
//	public static final int RIGHT_DOC_OC_ARM_ELEVATE_CHANNEL = 5;

	//Solenoids Module 1
	public static final int SHIFT_SOLENOID_1_CHANNEL = 0;
	public static final int SHIFT_SOLENOID_2_CHANNEL = 1;
	public static final int EXTEND_ROLLER_GRIPPER_SOLENOID_CHANNEL = 2;
	public static final int RETRACT_ROLLER_GRIPPER_SOLENOID_CHANNEL = 3;
	public static final int BEAR_HUGGER_TILT_CHANNEL = 4;
	public static final int BEAR_HUGGER_UNTILT_CHANNEL = 5;
	public static final int LEFT_DOC_OC_ARM_OPEN_CHANNEL = 7;
	public static final int LEFT_DOC_OC_ARM_CLOSE_CHANNEL = 6;
	
	
	//Slenoids Module 2
	public static final int BEAR_HUGGER_OPEN_CHANNEL = 2;
	public static final int BEAR_HUGGER_CLOSE_CHANNEL = 1;
	public static final int BRAKE_SOLENOID_CHANNEL = 0;
	
	//Encoders
	public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_A = 8;
	public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_B = 9;
	public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A = 6;
	public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B = 7;
	public static final int TOTE_ELEVATOR_ENCODER_CHANNEL_A = 4;
	public static final int TOTE_ELEVATOR_ENCODER_CHANNEL_B = 5;
	public static final int BEAR_HUGGER_ELEVATOR_CHANNEL_A = 0;
	public static final int BEAR_HUGGER_ELEVATOR_CHANNEL_B = 1;

	//Limit Switches
	public static final int ELEVATOR_BOTTOM_LIMIT_CHANNEL = 3;
//	public static final int ELEVATOR_TOP_LIMIT_CHANNEL = 7;
	public static final int ROLLER_STOP_LIMIT_CHANNEL = 2;

	//PDP
	public static final int ELEVATOR_PDP_CHANNEL_B = 0;
	public static final int ELEVATOR_PDP_CHANNEL_A = 1;
}

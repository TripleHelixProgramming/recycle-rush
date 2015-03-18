package org.usfirst.frc.team2363.robot;

public class RobotMap {

	//Constants
	public static final double HIGH_SPEED_SCALING = 0.5;
	public static final double LOW_SPEED_SCALING = 0.9;
	public static final double ROBOT_TOP_SPEED = 11;
	public static final double CENTER_CALIBRATION = 20;

	//Joysticks
	public static final int PS4_PORT = 0;
	public static final int OPERATOR_PORT = 1;
	public static final int MANUAL_OPERATOR_PORT = 2;
	
	//Manual Operator Buttons
	public static final int DOC_OC_OPEN = 8;
	public static final int DOC_OC_CLOSE = 7;
	public static final int CLOSE_BEAR_HUGGER = 6;
	public static final int OPEN_BEAR_HUGGER = 5;
	public static final int BEAR_HUGGER_UP = 4;
	public static final int BEAR_HUGGER_DOWN = 2;

	//Operator Buttons
	public static final int GROUND_BUTTON = 1;
	public static final int CARRY_PLACE_BUTTON = 2;
	public static final int STEP_BUTTON = 3;
	public static final int ONE_TOTE_BUTTON = 4;
	public static final int TWO_TOTE_BUTTON = 5;
	public static final int TILT_BUTTON = 7;
	public static final int BEAR_OPEN_CLOSE = 6;
	public static final int PICK_UP_BUTTON = 8;
	public static final int DOC_OC_SEQUENCE_BUTTON = 9;
	public static final int HUMAN_PLAYER_BUTTON = 10;

	//PS4 Buttons
	public static final int SHIFT_DOWN_BUTTON = 5; // R1
	public static final int SHIFT_UP_BUTTON = 6; // L1
	public static final int INTAKE_BUTTON = 8; // R2
	public static final int EJECT_BUTTON = 7; // L2
	public static final int CARRY_BUTTON = 4; // TRIANGLE
	public static final int GROUND_WITHOUT_OPEN = 2; // X
	public static final int SCORE_BUTTON = 1; // SQUARE
	public static final int THROTTLE_SCALING_BUTTON = 14; // Touchpad ;)
	public static final int LANDFILL_BUTTON = 8;
	public static final int BEAR_RESET_BUTTON = 3;
	public static final int JUST_INTAKE_BUTTON = 5;

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
	public static final int ELEVATOR_PDP_CHANNEL_B = 2;
	public static final int ELEVATOR_PDP_CHANNEL_A = 3;
}

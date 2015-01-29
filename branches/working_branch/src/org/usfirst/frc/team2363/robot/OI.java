package org.usfirst.frc.team2363.robot;

import static org.usfirst.frc.team2363.robot.RobotMap.*;
import static org.usfirst.frc.team2363.robot.subsystems.Drivetrain.ShifterState.*;

import org.usfirst.frc.team2363.robot.commands.SequentialCommandGroup;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveAtSpeed;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveToToteCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team2363.robot.commands.drivetrain.ShiftCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnTowardsTote;
import org.usfirst.frc.team2363.robot.commands.elevator.ElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.elevator.ResetElevatorEncoder;
import org.usfirst.frc.team2363.robot.subsystems.Elevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.AutonomousSelector;
import org.usfirst.frc.team2363.robot.util.LimitSwitch;
import org.usfirst.frc.team2363.robot.util.RollingAverager;
import org.usfirst.frc.team2363.robot.util.SelectableCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private RollingAverager throttleAverager = new RollingAverager(12, 0);
	//Controllers
	private Joystick ps4Controller;
	private Joystick operatorControl;
	private AutonomousSelector autoSelector;
	
	public OI() {
		//Controllers
		ps4Controller = new Joystick(PS4_PORT);
		operatorControl = new Joystick(2);
		
		
		//Joystick Buttons
		JoystickButton throttleScalingButton = new JoystickButton(ps4Controller, THROTTLE_SCALING_BUTTON);
		JoystickButton shiftDownButton = new JoystickButton(ps4Controller, SHIFT_DOWN_BUTTON);
		JoystickButton shiftUpButton = new JoystickButton(ps4Controller, SHIFT_UP_BUTTON);
		JoystickButton scoreButton = new JoystickButton(ps4Controller, SCORE_BUTTON);
		JoystickButton carryButton = new JoystickButton(ps4Controller, CARRY_BUTTON);
		
		shiftUpButton.whenPressed(new ShiftCommand(HIGH));
		shiftDownButton.whenPressed(new ShiftCommand(LOW));
		scoreButton.whenPressed(new ElevatorCommand(ElevatorPosition.GROUND));
		carryButton.whenPressed(new ElevatorCommand(ElevatorPosition.CARRY));
		throttleScalingButton.whenActive(new JoystickDrive());
		
		//Autonomous 
		JoystickButton autoSelector1 = new JoystickButton(operatorControl, 1);
		JoystickButton autoSelector2 = new JoystickButton(operatorControl, 2);
		JoystickButton autoSelector3 = new JoystickButton(operatorControl, 3);
		
		autoSelector = new AutonomousSelector(autoSelector1, autoSelector2, autoSelector3);
		
		//Elevator commands
		SmartDashboard.putData(ElevatorPosition.GROUND.getName(), new ElevatorCommand(ElevatorPosition.GROUND));
		SmartDashboard.putData(ElevatorPosition.CARRY.getName(), new ElevatorCommand(ElevatorPosition.CARRY));
		SmartDashboard.putData(ElevatorPosition.SCORE_PLATFORM.getName(), new ElevatorCommand(ElevatorPosition.SCORE_PLATFORM));
		SmartDashboard.putData(ElevatorPosition.STEP_CARRY.getName(), new ElevatorCommand(ElevatorPosition.STEP_CARRY));
		SmartDashboard.putData(ElevatorPosition.STEP_PLACE.getName(), new ElevatorCommand(ElevatorPosition.STEP_PLACE));
		SmartDashboard.putData(ElevatorPosition.STACK_TWO_CARRY.getName(), new ElevatorCommand(ElevatorPosition.STACK_TWO_CARRY));
		SmartDashboard.putData(ElevatorPosition.STACK_TWO_PLACE.getName(), new ElevatorCommand(ElevatorPosition.STACK_TWO_PLACE));
		SmartDashboard.putData(ElevatorPosition.STACK_THREE_CARRY.getName(), new ElevatorCommand(ElevatorPosition.STACK_THREE_CARRY));
		SmartDashboard.putData(ElevatorPosition.STACK_STEP_CARRY.getName(), new ElevatorCommand(ElevatorPosition.STACK_STEP_CARRY));
		SmartDashboard.putData(ElevatorPosition.STACK_STEP_PLACE.getName(), new ElevatorCommand(ElevatorPosition.STACK_STEP_PLACE));
		SmartDashboard.putData("Automated Human Player Station", new SequentialCommandGroup(new ElevatorCommand(ElevatorPosition.GROUND), new ElevatorCommand(ElevatorPosition.STACK_THREE_CARRY)));
		SmartDashboard.putData("Turn Towards Tote", new TurnTowardsTote());
		SmartDashboard.putData("DriveToTote", new DriveToToteCommand(0.5, 18));
		SmartDashboard.putData("DriveAtSpeed", new DriveAtSpeed());
		
		
		//Limit Switches
		LimitSwitch elevatorLimit = new LimitSwitch(ELEVATOR_BOTTOM_LIMIT_CHANNEL);
		elevatorLimit.whenActive(new ResetElevatorEncoder());
		elevatorLimit.whenInactive(new ResetElevatorEncoder());
	}
	public SelectableCommand getAutoCommand() {
		return autoSelector.getSelectedCommand();
		
	}
	public double getThrottle() {
		//Inverted Throttle
		if (ps4Controller.getRawAxis(LEFT_TRIGGER) > 0) {
			throttleAverager.addValue(-ps4Controller.getRawAxis(LEFT_STICK_Y));	
		} else {
		//Regular Throttle
			throttleAverager.addValue(ps4Controller.getRawAxis(LEFT_STICK_Y));
		}
		return throttleAverager.getAverage();
	}
	
	public double getTurn() {
		return ps4Controller.getRawAxis(RIGHT_STICK_X) * getTurnScaling(getThrottle());
	}

	public static double getTurnScaling(double x) {
		return -Math.abs(LOW_SPEED_SCALING - HIGH_SPEED_SCALING) * x + LOW_SPEED_SCALING;
	}
}

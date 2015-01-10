package org.usfirst.frc.team2363.robot;

import static org.usfirst.frc.team2363.robot.RobotMap.*;
import static org.usfirst.frc.team2363.robot.subsystems.Drivetrain.ShifterState.*;

import org.usfirst.frc.team2363.robot.commands.drivetrain.ShiftCommand;
import org.usfirst.frc.team2363.robot.util.AutonomousSelector;
import org.usfirst.frc.team2363.robot.util.SelectableCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//Controllers
	private Joystick ps4Controller;
	public Joystick operatorControl;
	private AutonomousSelector autoSelector;
	
	public OI() {
		
		//Controllers
		ps4Controller = new Joystick(PS4_PORT);
		operatorControl = new Joystick(2);
		
		
		//Joystick Buttons
		JoystickButton shiftDownButton = new JoystickButton(ps4Controller, SHIFT_DOWN_BUTTON_PORT);
		JoystickButton shiftUpButton = new JoystickButton(ps4Controller, SHIFT_UP_BUTON_PORT);
		
		shiftUpButton.whenPressed(new ShiftCommand(HIGH));
		shiftDownButton.whenPressed(new ShiftCommand(LOW));
		
		//Autonomous 
		JoystickButton autoSelector1 = new JoystickButton(operatorControl, 1);
		JoystickButton autoSelector2 = new JoystickButton(operatorControl, 2);
		JoystickButton autoSelector3 = new JoystickButton(operatorControl, 3);
		
		autoSelector = new AutonomousSelector(autoSelector1, autoSelector2, autoSelector3);
		
	}
	public SelectableCommand getAutoCommand() {
		return autoSelector.getSelectedCommand();
		
	}
	public double getThrottle() {
		//Inverted Throttle
		if (ps4Controller.getRawAxis(LEFT_TRIGGER) > 0) {
			return -ps4Controller.getRawAxis(LEFT_STICK_Y);	
		} else {
		//Regular Throttle
			return ps4Controller.getRawAxis(LEFT_STICK_Y);
		}
	}
	
	public double getTurn() {
		return ps4Controller.getRawAxis(RIGHT_STICK_X) * getTurnScaling(getThrottle());
	}

	private double getTurnScaling(double x) {
		return -Math.abs(LOW_SPEED_SCALING - HIGH_SPEED_SCALING) * x + LOW_SPEED_SCALING;
	}
}

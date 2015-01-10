package org.usfirst.frc.team2363.robot;

import static org.usfirst.frc.team2363.robot.RobotMap.highSpeedScaling;
import static org.usfirst.frc.team2363.robot.RobotMap.leftStickY;
import static org.usfirst.frc.team2363.robot.RobotMap.leftTrigger;
import static org.usfirst.frc.team2363.robot.RobotMap.lowSpeedScaling;
import static org.usfirst.frc.team2363.robot.RobotMap.ps4Port;
import static org.usfirst.frc.team2363.robot.RobotMap.rightStickX;
import static org.usfirst.frc.team2363.robot.RobotMap.shiftDownButtonPort;
import static org.usfirst.frc.team2363.robot.RobotMap.shiftUpButtonPort;

import org.usfirst.frc.team2363.robot.commands.ShiftCommand;
import org.usfirst.frc.team2363.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {


	//Controllers
	private Joystick ps4Controller;
	
	public OI() {
		
		//Controllers
		ps4Controller = new Joystick(1);
		
		//Joystick Buttons
		JoystickButton shiftDownButton = new JoystickButton(ps4Controller, shiftDownButtonPort);
		JoystickButton shiftUpButton = new JoystickButton(ps4Controller, shiftUpButtonPort);
		
		shiftUpButton.whenPressed(new ShiftCommand(Drivetrain.SHIFT_UP));
		shiftDownButton.whenPressed(new ShiftCommand(Drivetrain.SHIFT_DOWN));
	}
	
	/**
	 * 
	 * @return
	 */
	public double getThrottle() {
		//Inverted Throttle
		if (ps4Controller.getRawAxis(leftTrigger) > 0) {
			return -ps4Controller.getRawAxis(leftStickY);	
		} else {
		//Regular Throttle
			return ps4Controller.getRawAxis(leftStickY);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public double getTurn() {
		return ps4Controller.getRawAxis(rightStickX) * getTurnScaling(getThrottle());
	}

	private double getTurnScaling(double x) {
		return - Math.abs((lowSpeedScaling - highSpeedScaling)) * x + lowSpeedScaling;
	}

}



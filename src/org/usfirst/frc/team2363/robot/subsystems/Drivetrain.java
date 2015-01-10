
package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.commands.drivetrain.JoystickDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Drivetrain extends Subsystem {
	
	public enum ShifterState {
		HIGH,
		LOW;
	}
	
	//Motor Controllers
    private SpeedController frontLeft = new Talon(FRONT_LEFT_TALON_CHANNEL);
    private SpeedController frontRight = new Talon(FRONT_RIGHT_TALON_CHANNEL);
    private SpeedController rearLeft = new Talon(REAR_LEFT_TALON_CHANNEL);
    private SpeedController rearRight = new Talon(REAR_RIGHT_TALON_CHANNEL);
    
    //Solenoids
    private Solenoid shiftSolenoid1 = new Solenoid(SHIFT_SOLENOID_1_PORT);
    private Solenoid shiftSolenoid2 = new Solenoid(SHIFT_SOLENOID_2_PORT);
    
    private RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }
    
    public void arcadeDrive(double throttle, double turn) {
    	robotDrive.arcadeDrive(throttle, turn);
    }
    
    public void shift(ShifterState newState) {
    	switch (newState) {
    		case HIGH:
    			shiftSolenoid1.set(true);
    			shiftSolenoid2.set(false);
    			break;
    		case LOW:
    			shiftSolenoid1.set(false);
    			shiftSolenoid2.set(true);
    			break;
    		default:
    			break;
    	}
    }
    
}

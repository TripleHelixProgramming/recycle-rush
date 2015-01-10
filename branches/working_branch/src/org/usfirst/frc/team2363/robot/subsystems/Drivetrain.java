
package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Drivetrain extends Subsystem {
	
	//Constant
	public static final boolean SHIFT_UP = true;
	public static final boolean SHIFT_DOWN = false;
	
	//Motor Controllers
    private SpeedController frontLeft = new Talon(frontLeftTalonChannel);
    private SpeedController frontRight = new Talon(frontRightTalonChannel);
    private SpeedController rearLeft = new Talon(rearLeftTalonChannel);
    private SpeedController rearRight = new Talon(rearRightTalonChannel);
    
    //Solenoids
    private Solenoid shiftSolenoid1 = new Solenoid(shiftSolenoid1Port);
    private Solenoid shiftSolenoid2 = new Solenoid(shiftSolenoid2Port);
    
    private RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }
    
    public void arcadeDrive(double throttle, double turn) {
    	robotDrive.arcadeDrive(throttle, turn);
    }
    
    public void shift(boolean shiftUp) {
    	shiftSolenoid1.set(shiftUp);
    	shiftSolenoid2.set(!shiftUp);
    }
    
}

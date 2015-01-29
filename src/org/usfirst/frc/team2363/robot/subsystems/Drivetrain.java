
package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.commands.drivetrain.JoystickDrive;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
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
	//Encoders
	private Encoder leftEncoder = new Encoder(DRIVETRAIN_LEFT_ENCODER_CHANNEL_A, DRIVETRAIN_LEFT_ENCODER_CHANNEL_B, true, EncodingType.k4X);
	private Encoder rightEncoder = new Encoder(DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A, DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B, true, EncodingType.k4X);

	//Motor Controllers
    private SpeedController frontLeft = new Talon(FRONT_LEFT_TALON_CHANNEL);
    private SpeedController frontRight = new Talon(FRONT_RIGHT_TALON_CHANNEL);
    private SpeedController rearLeft = new Talon(REAR_LEFT_TALON_CHANNEL);
    private SpeedController rearRight = new Talon(REAR_RIGHT_TALON_CHANNEL);
    
    //Solenoids
    private Solenoid shiftSolenoid1 = new Solenoid(SHIFT_SOLENOID_1_CHANNEL);
    private Solenoid shiftSolenoid2 = new Solenoid(SHIFT_SOLENOID_2_CHANNEL);
    
    private RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    
    public Drivetrain() {
    	leftEncoder.setDistancePerPulse(0.01636);
    	leftEncoder.setSamplesToAverage(12);
    	
    	rightEncoder.setDistancePerPulse(0.01636);
    	rightEncoder.setSamplesToAverage(12);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }
    
    public void arcadeDrive(double throttle, double turn) {
    	robotDrive.arcadeDrive(throttle, turn);
    }
    
    public double getLeftPosition() {
    	return leftEncoder.getDistance();
    }
    
    public double getRightPosition() {
    	return rightEncoder.getDistance();
    }
    
    public double getLeftSpeed() {
    	return leftEncoder.getRate() / 4;
    }
    
    public double getRightSpeed() {
    	return rightEncoder.getRate() / 4;
    }
    
    public void resetEncoders() {
    	rightEncoder.reset();
    	leftEncoder.reset();
    }
    public void shift(ShifterState newState) {
    	if (newState == ShifterState.HIGH) {
    		shiftSolenoid1.set(false);
    		shiftSolenoid2.set(true);
    	} else {
    		shiftSolenoid1.set(true);
    		shiftSolenoid2.set(false);
    	}
    }
    
}

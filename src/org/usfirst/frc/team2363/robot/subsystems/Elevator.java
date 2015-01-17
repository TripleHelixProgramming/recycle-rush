package org.usfirst.frc.team2363.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

/**
 *
 */
public class Elevator extends Subsystem {
	
	public enum ElevatorPosition {
		GROUND("Ground", 0),
		CARRY("Carrying", 4),
		SCORE_PLATFORM("Scoring Platform", 2),
		STEP_CARRY("Step Carrying", 8),
		STEP_PLACE("Step Placement", 6),
		STACK_TWO_CARRY("Stack of Two Carry", 14),
		STACK_TWO_PLACE("Stack of Two Placement", 12),
		STACK_THREE_CARRY("Stack of Three Carry", 26),
		STACK_STEP_CARRY("Stack of Three Carry, For Step", 19),
		STACK_STEP_PLACE("Stack of Three Place, For Step", 17);
		
		private String displayName;
		private double height;
		
		private ElevatorPosition(String displayName, double height) {
			this.height = height;
			this.displayName = displayName;
		}
		
		public double getHeight() {
			return height;
		}
		public String getName() {
			return displayName;
		}
	}
	
    private Encoder encoder = new Encoder(ELEVATOR_ENCODER_CHANNEL_A, ELEVATOR_ENCODER_CHANNEL_B);
    private SpeedController motor = new Talon(ELEVATOR_TALON_CHANNEL);

    public void initDefaultCommand() {
        
    }
    
    public void elevate(double power) {
    	motor.set(power);
    }
    
    public double getPosition() {
    	return encoder.getDistance();
    }
}


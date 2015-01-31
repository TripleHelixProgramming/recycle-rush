package org.usfirst.frc.team2363.robot.subsystems;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.RobotMap.*;

/**
 *
 */
public class Elevator extends Subsystem {
	
	public enum ElevatorPosition {
		GROUND("Ground", 0),
		CARRY("Carrying", 7.5),
		SCORE_PLATFORM("Scoring Platform", 5.5),
		STEP_CARRY("Step Carrying", 11.5),
		STEP_PLACE("Step Placement", 9.5),
		STACK_TWO_CARRY("Stack of Two Carry", 17.5),
		STACK_TWO_PLACE("Stack of Two Placement", 15.5),
		STACK_THREE_CARRY("Stack of Three Carry", 27),
		STACK_STEP_CARRY("Stack of Three Carry, For Step", 22.5),
		STACK_STEP_PLACE("Stack of Three Place, For Step", 20.5);
		
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
	
	public enum BrakePosition {
		ENGAGED,
		OFF;
	}
	
    private Encoder encoder = new Encoder(ELEVATOR_ENCODER_CHANNEL_A, ELEVATOR_ENCODER_CHANNEL_B, true, EncodingType.k4X);
    private SpeedController motor = new Talon(ELEVATOR_TALON_CHANNEL);
    private Solenoid brake = new Solenoid(BRAKE_SOLENOID_CHANNEL);
    private DigitalInput topLimit = new DigitalInput(ELEVATOR_TOP_LIMIT_CHANNEL);
    
    public Elevator() {
    	encoder.setDistancePerPulse(ELEVATOR_DISTANCE_PER_PULSE);
    	encoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
    }

    @Override
    public void initDefaultCommand() {
        
    }
    
    public void setPower(double power) {
    	motor.set(-power);
    }
    
    public double getPosition() {
    	return encoder.getDistance();
    }
    
    public double getElevatorCurrent() {
    	return Robot.pdp.getCurrent(ELEVATOR_PDP_CHANNEL);
    }
    
    public void resetEncoder() {
    	encoder.reset();
    }
    
    public void setBrake(BrakePosition position) {
    	if (position == BrakePosition.ENGAGED) {
    		brake.set(true);
    		SmartDashboard.putBoolean("Brake", true);
    	} else {
    		brake.set(false);
    		SmartDashboard.putBoolean("Brake", false);
    	}
    }
    
    public boolean isAtTopLimit() {
    	return topLimit.get();
    }
}


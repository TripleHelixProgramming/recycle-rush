package org.usfirst.frc.team2363.robot.subsystems;

import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ExternalGripper extends Subsystem {
	
	public enum ExternalGripperPosition {
		STOWED(0),
		STEP(0),
		FLOOR(0);
		
		private double position;
		
		private ExternalGripperPosition(double position) {
			this.position = position;
		}
		
		public double getPosition() {
			return position;
		}
	}
	
	public enum ExternalExtensionPosition {
		EXTEND(0),
		RETRACT(0);
		
		private double position;
		
		private ExternalExtensionPosition(double position) {
			this.position = position;
		}
		
		public double getPosition() {
			return position;
		}
	}
	
    private Solenoid claw;
    private CANTalon rotate;
    private CANTalon extend;
    
    
    public ExternalGripper(int clawChannel, int rotateMotorChannel, int extendMotorChannel) {
    	claw = new Solenoid(clawChannel);
    	
    	rotate = new CANTalon(rotateMotorChannel);
    	rotate.changeControlMode(ControlMode.Position);
    	rotate.setPID(0, 0, 0);
    	
    	extend = new CANTalon(extendMotorChannel);
    	extend.changeControlMode(ControlMode.Position);
    	extend.setPID(0, 0, 0);
    }
    
    @Override 
    public void initDefaultCommand() {
        
    }
    
    public void setRotatePosition(double position) {
    	rotate.set(position);
    }
    
    public void setExtendPosition(double position) {
    	extend.set(position);
    }
    
    public void setClaw(ClawPosition position) {
    	if (position == ClawPosition.OPEN) {
    		claw.set(true);
    	} else {
    		claw.set(false);
    	}
    }
}



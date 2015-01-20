package org.usfirst.frc.team2363.robot.subsystems;

import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ExternalGripper extends Subsystem {
	
	public enum ExternalGripperPosition {
		STOWED,
		STEP,
		FLOOR;
	}
	
	public enum ExternalExtensionPosition {
		EXTEND,
		RETRACT;
	}
	
    private Solenoid claw;
    private Solenoid firstExtension;
    private Solenoid secondExtension;
    
    public ExternalGripper(int clawChannel, int firstExtentionChannel, int secondExtentionChannel) {
    	claw = new Solenoid(clawChannel);
    	firstExtension = new Solenoid(firstExtentionChannel);
    	secondExtension = new Solenoid(secondExtentionChannel);
    }
    
    public void initDefaultCommand() {
        
    }
    
    public void openClaw(ClawPosition position) {
    	if (position == ClawPosition.OPEN) {
    		claw.set(true);
    	} else {
    		claw.set(false);
    	}
    }
    
    public void extendFirstLevel(ExternalExtensionPosition position) {
    	if (position == ExternalExtensionPosition.EXTEND) {
    		firstExtension.set(true);
    	} else {
    		firstExtension.set(false);
    	}
    }
    
    public void extendSecondLevel(ExternalExtensionPosition position) {
    	if (position == ExternalExtensionPosition.EXTEND) {
    		secondExtension.set(true);
    	} else {
    		secondExtension.set(false);
    	}
    }
    
}


package org.usfirst.frc.team2363.robot.subsystems;

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
    
    public void openClaw(boolean open) {
    	claw.set(open);
    }
    
    public void extendFirstLevel(boolean extend) {
    	firstExtension.set(extend);
    }
    
    public void extendSecondLevel(boolean extend) {
    	secondExtension.set(extend);
    }
    
}


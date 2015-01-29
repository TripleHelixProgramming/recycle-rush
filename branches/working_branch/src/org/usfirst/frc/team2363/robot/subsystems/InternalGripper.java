package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class InternalGripper extends Subsystem {
    
    private Solenoid claw = new Solenoid(INTERNAL_GRIPPER_SOLENOID_CHANNEL);
    
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void openClaw(ClawPosition position) {
    	if (position == ClawPosition.OPEN) {
    		claw.set(true);
    	} else {
    		claw.set(false);
    	}
    }
}


package org.usfirst.frc.team2363.robot.subsystems;

import org.usfirst.frc.team2363.robot.RobotMap;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RollerGripper extends Subsystem {
    
	private SpeedController left = new Talon(RobotMap.LEFT_ROLLER_GRIPPER_TALON_CHANNEL);
	private SpeedController right = new Talon(RobotMap.RIGHT_ROLLER_GRIPPER_TALON_CHANNEL);
	
	private Solenoid gripper = new Solenoid(RobotMap.ROLLER_GRIPPER_SOLENOID_CHANNEL);
	
	@Override
    public void initDefaultCommand() {

    }
    
    public void setRollerPower(double power) {
    	left.set(power);
    	right.set(-power);
    }
    
    public void setGripper(ClawPosition position) {
    	if (position == ClawPosition.OPEN) {
    		gripper.set(true);
    	} else {
    		gripper.set(false);
    	}
    }
}


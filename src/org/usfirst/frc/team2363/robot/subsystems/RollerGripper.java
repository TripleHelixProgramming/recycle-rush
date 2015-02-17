package org.usfirst.frc.team2363.robot.subsystems;

import org.usfirst.frc.team2363.robot.RobotMap;
import org.usfirst.frc.team2363.robot.commands.grippers.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RollerGripper extends Subsystem {

	private SpeedController left = new Talon(RobotMap.LEFT_ROLLER_GRIPPER_TALON_CHANNEL);
	private SpeedController right = new Talon(RobotMap.RIGHT_ROLLER_GRIPPER_TALON_CHANNEL);

	private DoubleSolenoid gripper = new DoubleSolenoid(RobotMap.EXTEND_ROLLER_GRIPPER_SOLENOID_CHANNEL, RobotMap.RETRACT_ROLLER_GRIPPER_SOLENOID_CHANNEL);

	private DigitalInput toteLimit = new DigitalInput(RobotMap.ROLLER_STOP_LIMIT_CHANNEL);
	@Override
	public void initDefaultCommand() {
//		setDefaultCommand(new RollerGripperCommand(0, ClawPosition.CLOSE));
	}

	public void setRollerPower(double power) {
		left.set(power);
		right.set(-power);
	}

	public void setGripper(ClawPosition position) {
		if (position == ClawPosition.OPEN) {
			gripper.set(DoubleSolenoid.Value.kForward);
		} else {
			gripper.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public boolean isToteIn() {
		return toteLimit.get();
	}
}


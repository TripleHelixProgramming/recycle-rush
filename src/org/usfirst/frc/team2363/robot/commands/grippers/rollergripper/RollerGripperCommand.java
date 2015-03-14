package org.usfirst.frc.team2363.robot.commands.grippers.rollergripper;

import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team2363.robot.Robot.rollerGripper;
/**
 *
 */
public class RollerGripperCommand extends Command {

	private RollerGripperDirection direction;
	private ClawPosition position;

	public RollerGripperCommand(RollerGripperDirection direction, ClawPosition position) {
		this.direction = direction;
		this.position = position;
		requires(rollerGripper);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		rollerGripper.setGripper(position);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		if (rollerGripper.isToteIn()) {
//			rollerGripper.setRollerPower(0);
//		} else {
			rollerGripper.setRollerDirection(direction);
//		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		rollerGripper.setRollerDirection(RollerGripperDirection.OFF);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		rollerGripper.setRollerDirection(RollerGripperDirection.OFF);
	}
}

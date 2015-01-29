package org.usfirst.frc.team2363.robot.commands.grippers;

import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team2363.robot.Robot.rollerGripper;
/**
 *
 */
public class RollerGripperCommand extends Command {
	
	private double power;
	private ClawPosition position;

    public RollerGripperCommand(double power, ClawPosition position) {
    	this.power = power;
    	this.position = position;
    	requires(rollerGripper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	rollerGripper.setGripper(position);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rollerGripper.setRollerPower(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

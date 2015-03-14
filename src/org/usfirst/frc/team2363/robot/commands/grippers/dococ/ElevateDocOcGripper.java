package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import org.usfirst.frc.team2363.robot.subsystems.DocOcArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevateDocOcGripper extends Command {

	private DocOcArm arm;
	private double power;
	
    public ElevateDocOcGripper(DocOcArm arm, double power) {
    	this.arm = arm;
    	this.power = power;
    	requires(arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	arm.setElevation(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	arm.setElevation(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	arm.setElevation(0);
    }
}

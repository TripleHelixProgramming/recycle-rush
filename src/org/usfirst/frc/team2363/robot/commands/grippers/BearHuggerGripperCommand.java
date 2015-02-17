package org.usfirst.frc.team2363.robot.commands.grippers;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BearHuggerGripperCommand extends Command {

	private ClawPosition position;
	
    public BearHuggerGripperCommand(ClawPosition position) {
        requires(Robot.bearHugger);
        this.position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.bearHugger.openClaw(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.bearHugger.openClaw(position);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

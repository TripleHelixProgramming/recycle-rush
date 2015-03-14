package org.usfirst.frc.team2363.robot.commands.grippers.bearhugger;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustBearHuggerCommand extends Command {

    public AdjustBearHuggerCommand() {
    	requires(Robot.bearHugger);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.bearHuggerElevator.isBetweenAdjustHeights() && Robot.bearHuggerElevator.isElevatorMoving()) {
    		Robot.bearHugger.openClaw(ClawPosition.OPEN);
    	}
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

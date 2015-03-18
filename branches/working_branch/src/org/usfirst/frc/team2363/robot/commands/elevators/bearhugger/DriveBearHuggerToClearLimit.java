package org.usfirst.frc.team2363.robot.commands.elevators.bearhugger;

import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team2363.robot.Robot.bearHuggerElevator;

/**
 *
 */
public class DriveBearHuggerToClearLimit extends Command {

    public DriveBearHuggerToClearLimit() {
        requires(bearHuggerElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	bearHuggerElevator.drive(BearHuggerElevator.BEAR_HUGGER_ELEVATOR_POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return bearHuggerElevator.isAtClearLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	bearHuggerElevator.drive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	bearHuggerElevator.drive(0);
    }
}

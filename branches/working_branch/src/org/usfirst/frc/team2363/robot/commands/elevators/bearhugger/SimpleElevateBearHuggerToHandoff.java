package org.usfirst.frc.team2363.robot.commands.elevators.bearhugger;

import static org.usfirst.frc.team2363.robot.Robot.bearHuggerElevator;

import org.usfirst.frc.team2363.robot.RobotMap;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SimpleElevateBearHuggerToHandoff extends Command {

    public SimpleElevateBearHuggerToHandoff() {
       requires(bearHuggerElevator);
       this.setTimeout(1);
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
        return this.isTimedOut();
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

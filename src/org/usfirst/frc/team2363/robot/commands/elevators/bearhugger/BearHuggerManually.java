package org.usfirst.frc.team2363.robot.commands.elevators.bearhugger;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BearHuggerManually extends Command {

    public BearHuggerManually() {
    	requires(Robot.bearHuggerElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() { }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.ps4Controller.getRawButton(1)) {
    		Robot.bearHuggerElevator.drive(BearHuggerElevator.BEAR_HUGGER_ELEVATOR_POWER);
    	} else if (Robot.oi.ps4Controller.getRawButton(3)) {
    		Robot.bearHuggerElevator.drive(-BearHuggerElevator.BEAR_HUGGER_ELEVATOR_POWER);
    	} else {
    		Robot.bearHuggerElevator.drive(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() { }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { }
}

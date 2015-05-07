package org.usfirst.frc.team2363.robot.commands.elevators.bearhugger;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.BearHugger.TiltPosition;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HomeBearHugger extends Command {

    public HomeBearHugger() {
        requires(Robot.bearHuggerElevator);
        setTimeout(1.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.bearHugger.tilt(TiltPosition.UNTILT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.bearHuggerElevator.drive(-BearHuggerElevator.BEAR_HUGGER_ELEVATOR_POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.bearHuggerElevator.drive(0);
    	Robot.bearHuggerElevator.resetAtBottom();
    	Robot.bearHugger.openClaw(ClawPosition.CLOSE);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.bearHuggerElevator.drive(0);
    }
}

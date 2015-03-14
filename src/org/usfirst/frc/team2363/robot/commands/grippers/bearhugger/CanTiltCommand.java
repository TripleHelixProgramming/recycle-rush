package org.usfirst.frc.team2363.robot.commands.grippers.bearhugger;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.BearHugger.TiltPosition;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CanTiltCommand extends Command {

	private TiltPosition position;
	
    public CanTiltCommand(TiltPosition position) {
    	requires(Robot.bearHugger);
        this.position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.bearHuggerElevator.getPosition() <= BearHuggerElevator.CAN_HAND_OFF) {
    		Robot.bearHugger.tilt(position);
    	} else {
    		Robot.bearHugger.tilt(TiltPosition.UNTILT);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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

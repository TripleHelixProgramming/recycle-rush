package org.usfirst.frc.team2363.robot.commands.elevators.tote;

import static org.usfirst.frc.team2363.robot.Robot.toteElevator;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.BrakePosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevateToteManually extends Command {

    public ElevateToteManually() {
    	requires(toteElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	toteElevator.setBrake(BrakePosition.ENGAGED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (toteElevator.isAtBottomLimit()) {
    		toteElevator.resetAtBottom();
    	}
    	
    	if (Math.abs(Robot.oi.getElevatorPower()) > 0.1) {
    		toteElevator.setBrake(BrakePosition.OFF);
    	} else {
    		toteElevator.setBrake(BrakePosition.ENGAGED);
    	}
    	toteElevator.setPower(Robot.oi.getElevatorPower() * -0.68);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	toteElevator.setPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	toteElevator.setPower(0);
    }
}

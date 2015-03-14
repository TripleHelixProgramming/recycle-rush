package org.usfirst.frc.team2363.robot.commands.elevators.tote;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2363.robot.Robot.toteElevator;

/**
 *
 */
public class SimpleToteElevatorCommand extends Command {

	private double power;
	
    public SimpleToteElevatorCommand(double power) {
    	this.power = power;
        requires(toteElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	toteElevator.setPower(power);
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

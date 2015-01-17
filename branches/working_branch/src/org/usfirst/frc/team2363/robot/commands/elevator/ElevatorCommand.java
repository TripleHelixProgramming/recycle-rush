package org.usfirst.frc.team2363.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.PIDCommand;
import static org.usfirst.frc.team2363.robot.Robot.elevator;

/**
 *
 */
public class ElevatorCommand extends PIDCommand {

    public ElevatorCommand(double position) {
    	super(0, 0, 0);
        requires(elevator);
        setSetpoint(position);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.getPIDController().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.getPIDController().disable();
    }

	@Override
	protected double returnPIDInput() {
		return elevator.getPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		elevator.elevate(output);
	}	
}

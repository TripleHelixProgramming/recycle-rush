package org.usfirst.frc.team2363.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import static org.usfirst.frc.team2363.robot.Robot.drivetrain;

/**
 *
 */
public class TurnToAngle extends PIDCommand {

	private double angle;
	private double maxTurnPower;

    public TurnToAngle(double angle, double maxTurnPower) {
    	super(0.04, 0, 0.04);
        requires(drivetrain);
        this.angle = angle;
        this.maxTurnPower = maxTurnPower;
        getPIDController().setAbsoluteTolerance(5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.resetHeading();
    	setSetpoint(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		return drivetrain.getHeading();
	}

	@Override
	protected void usePIDOutput(double output) {
    	if (output > maxTurnPower) {
    		drivetrain.arcadeDrive(0, maxTurnPower);
    	} else if (output < -maxTurnPower) {
    		drivetrain.arcadeDrive(0, -maxTurnPower);
    	} else {
    		drivetrain.arcadeDrive(0, output);
    	}
	}
}

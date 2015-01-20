package org.usfirst.frc.team2363.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import static org.usfirst.frc.team2363.robot.Robot.drivetrain;

/**
 *
 */
public class DriveStraightCommand extends PIDCommand {
		
    public DriveStraightCommand(double power, int distance) {
        super(0.0016, 0.00009, 0);
        requires(drivetrain);
        getPIDController().setOutputRange(-power, power);     
        getPIDController().setAbsoluteTolerance(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.resetEncoders();
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	getPIDController().disable();
    }

	@Override
	protected double returnPIDInput() {
		return (drivetrain.getLeftPosition() + drivetrain.getRightPosition()) / 2;
	}

	@Override
	protected void usePIDOutput(double output) {
		drivetrain.arcadeDrive(output, 0);
	}
}

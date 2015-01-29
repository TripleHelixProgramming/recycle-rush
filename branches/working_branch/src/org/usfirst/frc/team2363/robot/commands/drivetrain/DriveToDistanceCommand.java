package org.usfirst.frc.team2363.robot.commands.drivetrain;

import org.usfirst.frc.team2363.robot.subsystems.Drivetrain.ShifterState;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.Robot.drivetrain;

/**
 *
 */
public class DriveToDistanceCommand extends PIDCommand {
		
    public DriveToDistanceCommand(double power, double distance) {
        super(0.16, 0, 0);
        requires(drivetrain);
        getPIDController().setOutputRange(-power, power);     
        getPIDController().setAbsoluteTolerance(0.5);
        setSetpoint(distance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.shift(ShifterState.HIGH);
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
		SmartDashboard.putNumber("distanceDriven", (drivetrain.getLeftPosition() + drivetrain.getRightPosition()) / 2);
		return (drivetrain.getLeftPosition() + drivetrain.getRightPosition()) / 2;
	}

	@Override
	protected void usePIDOutput(double output) {
		drivetrain.arcadeDrive(-output, 0);
	}
}

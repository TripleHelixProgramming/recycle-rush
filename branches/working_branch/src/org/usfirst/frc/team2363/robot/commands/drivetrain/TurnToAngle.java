package org.usfirst.frc.team2363.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.Robot.drivetrain;

/**
 *
 */
public class TurnToAngle extends Command {

	private double angle;
	private double maxTurnPower;
	private final double P = 0.02;

    public TurnToAngle(double angle, double maxTurnPower) {
        requires(drivetrain);
        this.angle = angle;
        this.maxTurnPower = maxTurnPower;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.resetHeading();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double turnPower = -P * (drivetrain.getHeading() - angle);
    	if (turnPower > maxTurnPower) {
    		drivetrain.arcadeDrive(-0.75, maxTurnPower);
    	} else if (turnPower < -maxTurnPower) {
    		drivetrain.arcadeDrive(-0.75, -maxTurnPower);
    	} else {
    		drivetrain.arcadeDrive(-0.75, turnPower);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("Distance to angle", drivetrain.getHeading() - angle);
        return Math.abs(drivetrain.getHeading() - angle) < 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

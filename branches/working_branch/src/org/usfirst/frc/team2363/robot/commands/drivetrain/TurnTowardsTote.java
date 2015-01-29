package org.usfirst.frc.team2363.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.Robot.drivetrain;
import static org.usfirst.frc.team2363.robot.Robot.visionProcessor;;

/**
 *
 */
public class TurnTowardsTote extends Command {
	
	private static final  double P = 0.01;
	private static final double maxPower = 0.3;
	
    public TurnTowardsTote() {
       requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (visionProcessor.getCenter() * P > maxPower) {
    		drivetrain.arcadeDrive(0, maxPower);
    	} else if (visionProcessor.getCenter() * P < -maxPower) {
    		drivetrain.arcadeDrive(0, -maxPower);
    	} else {
    	drivetrain.arcadeDrive(0, visionProcessor.getCenter() * P);
    	}
    	SmartDashboard.putNumber("Turn Power", visionProcessor.getCenter() * P);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drivetrain.arcadeDrive(0, 0);
    }
}

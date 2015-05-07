package org.usfirst.frc.team2363.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.Robot.drivetrain;
import static org.usfirst.frc.team2363.robot.Robot.visionProcessor;

/**
 *
 */
public class DrivePowerStraightAtTote extends Command {
	
	private double power;
	private double distance;
	
	private final double vP =  0.1;
	private final double gP = -0.5;
	
	private double maxTurnPower = 0.4;

    public DrivePowerStraightAtTote(double power, double distance) {
        requires(drivetrain);
        this.power = power;
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.resetHeading();
    	drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = 0;
//    	if (visionProcessor.getCenter() < 1 && visionProcessor.getCenter() > -1) {
    		speed = drivetrain.getHeading() * gP;
//    	} else {
//    		speed = visionProcessor.getCenter() * vP;
//    	}
    	
//    	double distanceTo = distance - (drivetrain.getLeftPosition() + drivetrain.getRightPosition()) / 2;
    	double distanceTo = distance - drivetrain.getLeftPosition();
//    	SmartDashboard.putNumber("Distance to tote", distanceTo);
    	double drivePower = 0;
    	if (distanceTo > power) {
    		drivePower = power;
    	} else if (distanceTo < -power) {
    		drivePower = -power;
    	} else {
    		drivePower = distanceTo;
    	}
    	
    	if (speed > maxTurnPower) {
			drivetrain.arcadeDrive(-drivePower, maxTurnPower);
    	} else if (speed < -maxTurnPower) {
    		drivetrain.arcadeDrive(-drivePower, -maxTurnPower);
    	} else {
    		drivetrain.arcadeDrive(-drivePower, speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(distance - drivetrain.getLeftPosition()) < 1;
//        return Math.abs(distance - (drivetrain.getLeftPosition() + drivetrain.getRightPosition()) / 2) < 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drivetrain.arcadeDrive(0, 0);
    }
}

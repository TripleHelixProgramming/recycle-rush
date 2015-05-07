package org.usfirst.frc.team2363.robot.commands.drivetrain;

import org.usfirst.frc.team2363.robot.Robot;

/**
 *
 */
public class DriveStraightAtSpeedConstantHeading extends DriveStraightAtSpeed {


    public DriveStraightAtSpeedConstantHeading(double maxSpeed, double distance) {
		super(maxSpeed, distance);
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    }
}

package org.usfirst.frc.team2363.robot.commands.drivetrain;

import static org.usfirst.frc.team2363.robot.Robot.drivetrain;
import static org.usfirst.frc.team2363.robot.Robot.visionProcessor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToToteCommand extends DriveToDistanceCommand {
	
	private static final double P = 0.01;
	private static final double maxPower = 0.5;

	public DriveToToteCommand(double power, int distance) {
		super(power, distance);
	}

	@Override
	protected void usePIDOutput(double output) {
		if (visionProcessor.getCenter() * P > maxPower) {
    		drivetrain.arcadeDrive(-output, maxPower);
    	} else if (visionProcessor.getCenter() * P < -maxPower) {
    		drivetrain.arcadeDrive(-output, -maxPower);
    	} else {
    		drivetrain.arcadeDrive(-output, visionProcessor.getCenter() * P);
    	}
    	SmartDashboard.putNumber("Turn Power", visionProcessor.getCenter() * P);
    }
}

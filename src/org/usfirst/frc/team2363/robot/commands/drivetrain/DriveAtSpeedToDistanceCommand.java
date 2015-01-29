package org.usfirst.frc.team2363.robot.commands.drivetrain;

public class DriveAtSpeedToDistanceCommand extends DriveAtSpeed {
	
	private double distance;
	private double maxSpeed;
	
	public DriveAtSpeedToDistanceCommand(double distance, double maxSpeed) {
		super();
		this.distance = distance;
		this.maxSpeed = maxSpeed;
	}
	
	public void execute() {
		
	}
}

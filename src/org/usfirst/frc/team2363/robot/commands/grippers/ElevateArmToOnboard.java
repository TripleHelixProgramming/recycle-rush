package org.usfirst.frc.team2363.robot.commands.grippers;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;
/**
 *
 */
public class ElevateArmToOnboard extends PIDCommand {
private double position;
    public ElevateArmToOnboard(double position) {
    	super(0, 0, 0);
        this.position = position;
        this.getPIDController().setAbsoluteTolerance(1);
        this.getPIDController().setOutputRange(-0.5, 0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setSetpoint(position);
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
    	leftDocOcArm.setElevationSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	leftDocOcArm.setElevationSpeed(0);
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return leftDocOcArm.getElevationPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		leftDocOcArm.setElevationSpeed(output);
		// TODO Auto-generated method stub
		
	}
}

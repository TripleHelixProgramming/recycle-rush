package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class ElevateDocOcArmToCommand extends PIDCommand {

	private DocOcArmPosition position;
	
	public ElevateDocOcArmToCommand(DocOcArmPosition position, double maxPower) {
		super(0.023, 0, 0);
		requires(Robot.leftDocOcArm);
		this.position = position;
		setSetpoint(position.getElevation());
		getPIDController().setOutputRange(-maxPower, maxPower);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
//		Robot.leftDocOcArm.setElevationControlMethod(ControlMode.PercentVbus);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.leftDocOcArm.isElevationOnTarget(position);
	}

	// Called once after isFinished returns true
	protected void end() {
		 Robot.leftDocOcArm.setElevation(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		 Robot.leftDocOcArm.setElevation(0);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.leftDocOcArm.getElevationPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.leftDocOcArm.setElevation(output);
		
	}
}

package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevateDocOcToYaw extends PIDCommand {
	

    public ElevateDocOcToYaw(double maxPower) {
    	super(0.01, 0, 0);
		requires(Robot.leftDocOcArm);
		getPIDController().setOutputRange(-maxPower, maxPower);
    }

    protected void initialize() {
		leftDocOcArm.setElevationControlMethod(ControlMode.PercentVbus);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double expectedPosition = getExpectedElevation(leftDocOcArm.getYawPosition());
		SmartDashboard.putNumber("Expected Elevation Position", expectedPosition);
		if (expectedPosition > DocOcArmPosition.PREP_FOR_HANDOFF.getElevation()) {
			setSetpoint(DocOcArmPosition.PREP_FOR_HANDOFF.getElevation());
		} else {
			setSetpoint(expectedPosition);
		}
		Robot.leftDocOcArm.setYaw(-0.3);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		SmartDashboard.putNumber("Distance to Stowed", Math.abs(leftDocOcArm.getYawPosition() - DocOcArmPosition.LEFT_STOWED.getYaw()));
		return Math.abs(leftDocOcArm.getYawPosition() - DocOcArmPosition.LEFT_STOWED.getYaw()) < 10;
	}

	// Called once after isFinished returns true
	protected void end() {
		leftDocOcArm.setElevation(0);
		Robot.leftDocOcArm.setYaw(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		leftDocOcArm.setElevation(0);
		Robot.leftDocOcArm.setYaw(0);
	}

	@Override
	protected double returnPIDInput() {
		return leftDocOcArm.getElevationPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.leftDocOcArm.setElevation(output);
	}
	
	private double getExpectedElevation(double x) {
		return 2.3 * x + -134;
	}
}

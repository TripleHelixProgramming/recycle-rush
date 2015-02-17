package org.usfirst.frc.team2363.robot.subsystems;

import org.usfirst.frc.team2363.robot.commands.grippers.DocOcManual;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DocOcArm extends Subsystem {

	public enum DocOcArmPosition {
		LEFT_STOWED(380, 700),
//		LEFT_STEP(500, 0),
		LEFT_FLOOR(660, 505),
		LEFT_HANDOFF(415, 450),
		LEFT_OFF_FLOOR(525, 505),
		PREP_FOR_HANDOFF(455, 485),
		RIGHT_STOWED(0, 0),
		RIGHT_STEP(0, 0),
		RIGHT_FLOOR(0, 0),
		RIGHT_HANDOFF(0, 0);

		private double elevation;
		private double yaw;

		private DocOcArmPosition(double elevation, double yaw) {
			this.elevation = elevation;
			this.yaw = yaw;
		}

		public double getElevation() {
			return elevation;
		}
		
		public double getYaw() {
			return yaw;
		}
	}

	private DoubleSolenoid claw;
	private CANTalon yaw;
	private CANTalon elevation;


	public DocOcArm(int openClawChannel, int closeClawChannel, int rotateMotorChannel, int extendMotorChannel) {
		claw = new DoubleSolenoid(openClawChannel, closeClawChannel);

		yaw = new CANTalon(rotateMotorChannel);
		yaw.changeControlMode(ControlMode.PercentVbus);
		yaw.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		yaw.setPID(0, 0.04, 0);
		yaw.reverseOutput(false);
		yaw.enableControl();

		elevation = new CANTalon(extendMotorChannel);
		elevation.changeControlMode(ControlMode.PercentVbus);
		elevation.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		elevation.setPID(0, 0.1, 0);
		elevation.reverseOutput(true);
		elevation.enableControl();
	}

	@Override 
	public void initDefaultCommand() {
		setDefaultCommand(new DocOcManual());
	}
	
	public void setControlMethod(ControlMode mode) {
		elevation.changeControlMode(mode);
		yaw.changeControlMode(mode);
	}
	
	public void setControlEnabled(boolean enable) {
		if (enable) {
			elevation.enableControl();
			yaw.enableControl();
		} else {
			elevation.disableControl();
			yaw.disableControl();
		}
	}

	public void setYawSpeed(double speed) {
		yaw.set(speed);
	}

	public void setElevationSpeed(double position) {
		elevation.set(position);
	}

	public void setClaw(ClawPosition position) {
		if (position == ClawPosition.OPEN) {
			claw.set(DoubleSolenoid.Value.kReverse);
		} else {
			claw.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	public boolean isElevationOnTarget(DocOcArmPosition position) {
		return Math.abs(elevation.getPosition() - position.getElevation()) < 10;
	}
	
	public boolean isYawOnTarget(DocOcArmPosition position) {
		return Math.abs(yaw.getPosition() - position.getYaw()) < 1;
	}
	
	public double getYawPosition() {
		SmartDashboard.putNumber("Yaw Voltage", yaw.getAnalogInRaw());
		return yaw.getPosition();
	}
	
	public double getElevationPosition() {
		return elevation.getPosition();
	}
	
	public double getYawSpeed() {
		return yaw.getSpeed();
	}
	
	public double getElevationSpeed() {
		return elevation.getSpeed();
	}
}



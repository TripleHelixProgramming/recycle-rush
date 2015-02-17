package org.usfirst.frc.team2363.robot.subsystems;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.commands.elevator.ElevateManually;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.RobotMap.*;

/**
 *
 */
public class ToteElevator extends Subsystem {

	public enum ElevatorPosition {
		GROUND("Ground/Score", 0),
		CARRY("Carrying", 5),
		STEP_CARRY("Carry for Step", 10.0),
		STEP_PLACE("Place on Step", 8.2),
		ONE_TOTE_CARRY("Carry for one tote", 17.5),
		ONE_TOTE_PLACE("Place on one tote", 15.5),
		TWO_TOTE_CARRY("Carry for two totes", 27),
		TWO_TOTE_PLACE("Place on two totes", 24);

		private String displayName;
		private double height;

		private ElevatorPosition(String displayName, double height) {
			this.height = height;
			this.displayName = displayName;
		}

		public double getHeight() {
			return height;
		}

		public String getName() {
			return displayName;
		}
	}

	public enum BrakePosition {
		ENGAGED,
		OFF;
	}

	private Encoder encoder = new Encoder(TOTE_ELEVATOR_ENCODER_CHANNEL_A, TOTE_ELEVATOR_ENCODER_CHANNEL_B, true, EncodingType.k4X);
	private SpeedController motor = new Talon(TOTE_ELEVATOR_TALON_CHANNEL_A);
	private SpeedController motor2 = new Talon(TOTE_ELEVATOR_TALON_CHANNEL_B);
	private Solenoid brake = new Solenoid(1, BRAKE_SOLENOID_CHANNEL);
	private DigitalInput bottomLimit = new DigitalInput(ELEVATOR_BOTTOM_LIMIT_CHANNEL);

	public ToteElevator() {
		encoder.setDistancePerPulse(TOTE_ELEVATOR_DISTANCE_PER_PULSE);
//		encoder.setDistancePerPulse(1);
		encoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
		encoder.setSamplesToAverage(12);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ElevateManually());
	}

	public void setPower(double power) {
		motor.set(power);
		motor2.set(power);
	}

	public double getPosition() {
		return encoder.getDistance();
	}

	public double getElevatorCurrent() {
		return Math.max(Robot.pdp.getCurrent(ELEVATOR_PDP_CHANNEL_A), Robot.pdp.getCurrent(ELEVATOR_PDP_CHANNEL_B));
	}
	
	public void resetAtTop() {
		
	}

	public void resetAtBottom() {
		encoder.reset();
	}

	public void setBrake(BrakePosition position) {
		if (position == BrakePosition.ENGAGED) {
			brake.set(true);
			SmartDashboard.putBoolean("Brake", true);
		} else {
			brake.set(false);
			SmartDashboard.putBoolean("Brake", false);
		}
	}

//	public boolean isAtTopLimit() {
//		return topLimit.get();
//	}
	
	public boolean isAtBottomLimit() {
		return bottomLimit.get();
	}
	
	public double getElevatorSpeed() {
		return encoder.getRate();
	}
}


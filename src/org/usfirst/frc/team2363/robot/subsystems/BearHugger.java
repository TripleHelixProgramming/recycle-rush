package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BearHugger extends Subsystem {
	
	public enum TiltPosition {
		TILT,
		UNTILT;
	}

	private DoubleSolenoid claw = new DoubleSolenoid(1, BEAR_HUGGER_OPEN_CHANNEL, BEAR_HUGGER_CLOSE_CHANNEL);
	private DoubleSolenoid tilt = new DoubleSolenoid(BEAR_HUGGER_TILT_CHANNEL, BEAR_HUGGER_UNTILT_CHANNEL);

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void openClaw(ClawPosition position) {
		if (position == ClawPosition.OPEN) {
			claw.set(DoubleSolenoid.Value.kReverse);
		} else {
			claw.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	public void tilt(TiltPosition position) {
		if (position == TiltPosition.TILT) {
			tilt.set(DoubleSolenoid.Value.kForward);
		} else {
			tilt.set(DoubleSolenoid.Value.kReverse);
		}
	}
}


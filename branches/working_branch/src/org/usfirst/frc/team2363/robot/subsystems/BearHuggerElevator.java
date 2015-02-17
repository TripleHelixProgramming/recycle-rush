package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.RobotMap;
import org.usfirst.frc.team2363.robot.commands.elevator.StopBearHuggerElevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BearHuggerElevator extends Subsystem {
	
	public static final double CAN_HAND_OFF = 6;
    
    private SpeedController elevator = new Talon(BEAR_HUGGER_ELEVATOR_TALON_CHANNEL);
    private Encoder encoder = new Encoder(BEAR_HUGGER_ELEVATOR_CHANNEL_A, BEAR_HUGGER_ELEVATOR_CHANNEL_B, true, EncodingType.k4X);

    public BearHuggerElevator() {
    	encoder.setDistancePerPulse(BEAR_HUGGER_ELEVATOR_DISTANCE_PER_PULSE);
		encoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new StopBearHuggerElevator());
    }
    
    public void drive(double power) {
    	elevator.set(power);
    }
    
    public double getPosition() {
		return encoder.getDistance();
	}
    
    public boolean isAtTopLimit() {
    	return encoder.getDistance() > RobotMap.BEAR_HUGGER_ELEVATOR_TOP_LIMIT;
    }

	public boolean isBetweenAdjustHeights() {
		return encoder.getDistance() < 10;
	}

	public boolean isElevatorMoving() {
		return !encoder.getStopped();
	}
}


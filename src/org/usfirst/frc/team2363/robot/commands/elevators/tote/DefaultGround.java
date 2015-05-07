package org.usfirst.frc.team2363.robot.commands.elevators.tote;

import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DefaultGround extends CommandGroup {

	public  DefaultGround() {
		addSequential(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN), 0.5);
		addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
	}
}

package org.usfirst.frc.team2363.robot.commands.elevators.tote;

import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToGround extends CommandGroup {

	public  GoToGround() {
		addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
		addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
		//    	addSequential(new RollerGripperCommand(0, ClawPosition.CLOSE));
	}
}

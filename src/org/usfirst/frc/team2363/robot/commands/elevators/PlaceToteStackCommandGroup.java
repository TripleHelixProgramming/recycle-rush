package org.usfirst.frc.team2363.robot.commands.elevators;

import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.DriveBearHuggerToClearLimit;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.DriveBearHuggerToTopLimit;
import org.usfirst.frc.team2363.robot.commands.grippers.bearhugger.BearHuggerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceToteStackCommandGroup extends CommandGroup {

	public  PlaceToteStackCommandGroup() {
		addSequential(new ElevateBothAtSpeed(ElevatorPosition.GROUND, 10.0));
		addParallel(new BearHuggerGripperCommand(ClawPosition.OPEN));
		addSequential(new DriveBearHuggerToTopLimit(), 0.1);
		addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN), 0.1);
		
		

//		addSequential(new DriveStraightAtSpeed(12, -30));
	}
}

package org.usfirst.frc.team2363.robot.commands;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveStraightAtSpeed;
import org.usfirst.frc.team2363.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team2363.robot.commands.elevator.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.elevator.SimpleBearHuggerElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.BearHuggerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class PlaceToteStackCommandGroup extends CommandGroup {

	public  PlaceToteStackCommandGroup() {
		addSequential(new BearHuggerGripperCommand(ClawPosition.OPEN));
		addSequential(new WaitCommand(1));
		addParallel(new RollerGripperCommand(0, ClawPosition.CLOSE));
		addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
		addParallel(new RollerGripperCommand(-0.5, ClawPosition.CLOSE));
		addSequential(new DriveStraightAtSpeed(-48, -30));
		addParallel(new JoystickDrive());
		addParallel(new SimpleBearHuggerElevatorCommand(-0.5));
		addSequential(new WaitCommand(3));
		addParallel(new SimpleBearHuggerElevatorCommand(0));
	}
}

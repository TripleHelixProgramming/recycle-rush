package org.usfirst.frc.team2363.robot.commands;

import org.usfirst.frc.team2363.robot.commands.elevator.SimpleElevateBearHuggerToHandoff;
import org.usfirst.frc.team2363.robot.commands.elevator.SimpleBearHuggerElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.BearHuggerGripperCommand;
import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;
import org.usfirst.frc.team2363.robot.commands.grippers.DocOcArmGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.RotateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class LeftCanHandoffCommandGroup extends CommandGroup {

	public LeftCanHandoffCommandGroup() {
		addSequential(new RotateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_HANDOFF, 1));
		addSequential(new ElevateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_HANDOFF, 5));
		addSequential(new SimpleElevateBearHuggerToHandoff());
		addSequential(new BearHuggerGripperCommand(ClawPosition.CLOSE));
		addSequential(new WaitCommand(1));
		addSequential(new DocOcArmGripperCommand(leftDocOcArm, ClawPosition.CLOSE));
		addSequential(new WaitCommand(1));
		addSequential(new SimpleBearHuggerElevatorCommand(-0.5));
		addSequential(new WaitCommand(1));
		addSequential(new SimpleBearHuggerElevatorCommand(0));
		addSequential(new RotateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_STOWED, 1));
		addSequential(new ElevateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_STOWED, 5));
	}
}

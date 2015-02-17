package org.usfirst.frc.team2363.robot.commands;

import org.usfirst.frc.team2363.robot.commands.elevator.SimpleElevateBearHuggerToHandoff;
import org.usfirst.frc.team2363.robot.commands.elevator.SimpleBearHuggerElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.BearHuggerGripperCommand;
import static org.usfirst.frc.team2363.robot.Robot.rightDocOcArm;
import org.usfirst.frc.team2363.robot.commands.grippers.DocOcArmGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.RotateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RightCanHandoffCommandGroup extends CommandGroup {

	public RightCanHandoffCommandGroup() {
		addSequential(new RotateDocOcArmToCommand(rightDocOcArm, DocOcArmPosition.RIGHT_HANDOFF, 1));
		addSequential(new ElevateDocOcArmToCommand(rightDocOcArm, DocOcArmPosition.RIGHT_HANDOFF, 5));
		addSequential(new SimpleElevateBearHuggerToHandoff());
		addSequential(new BearHuggerGripperCommand(ClawPosition.CLOSE));
		addSequential(new WaitCommand(1));
		addSequential(new DocOcArmGripperCommand(rightDocOcArm, ClawPosition.CLOSE));
		addSequential(new WaitCommand(1));
		addSequential(new SimpleBearHuggerElevatorCommand(-0.5));
		addSequential(new WaitCommand(1));
		addSequential(new SimpleBearHuggerElevatorCommand(0));
		addSequential(new RotateDocOcArmToCommand(rightDocOcArm, DocOcArmPosition.RIGHT_STOWED, 1));
		addSequential(new ElevateDocOcArmToCommand(rightDocOcArm, DocOcArmPosition.RIGHT_STOWED, 5));
	}
}

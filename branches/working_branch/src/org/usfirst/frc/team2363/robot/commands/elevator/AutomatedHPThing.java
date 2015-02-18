package org.usfirst.frc.team2363.robot.commands.elevator;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.RobotMap;
import org.usfirst.frc.team2363.robot.commands.grippers.AdjustBearHuggerCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.BearHuggerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.FinishingRollerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.WaitForToteCommand;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.BrakePosition;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutomatedHPThing extends CommandGroup {

	public  AutomatedHPThing() {
		addParallel(new RollerGripperCommand(RobotMap.ROLLER_POWER, ClawPosition.CLOSE));
		addParallel(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
		addSequential(new WaitForToteCommand());
		addParallel(new RollerGripperCommand(0, ClawPosition.CLOSE));
		addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
//		addParallel(new AdjustBearHuggerCommand());
		addSequential(new ElevateAtSpeed(ElevatorPosition.TWO_TOTE_CARRY));
		addParallel(new FinishingRollerGripperCommand(0, ClawPosition.OPEN));
		addParallel(new BearHuggerGripperCommand(ClawPosition.CLOSE));
	}
	
	public boolean isFinished() {
		return super.isFinished() || Robot.bearHuggerElevator.isAtTopLimit();
	}
	
	public void end() {
		Robot.toteElevator.setBrake(BrakePosition.ENGAGED);
	}
}

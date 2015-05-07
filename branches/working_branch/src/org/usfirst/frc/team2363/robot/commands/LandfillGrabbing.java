package org.usfirst.frc.team2363.robot.commands;

import static org.usfirst.frc.team2363.robot.Robot.toteElevator;
import static org.usfirst.frc.team2363.robot.Robot.bearHuggerElevator;
import static org.usfirst.frc.team2363.robot.Robot.rollerGripper;

import org.usfirst.frc.team2363.robot.commands.elevators.ElevateBothAtSpeed;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.WaitForToteCommand;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.BrakePosition;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LandfillGrabbing extends CommandGroup {
	
	private boolean atMaxHeight;
	
    public LandfillGrabbing() {
    	addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
		addParallel(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
		addSequential(new WaitForToteCommand());
		addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN), 0.5);
		addSequential(new ElevateBothAtSpeed(ElevatorPosition.GROUND));
        addSequential(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
    }
    
    @Override
    protected void initialize() {
    	atMaxHeight = false;
    }
    
    @Override
    protected boolean isFinished() {
    	atMaxHeight = atMaxHeight || bearHuggerElevator.isAtTopLimit();
    	if (atMaxHeight) {
    		return false;
    	}
    	return super.isFinished();
    }
    
    @Override
    protected void end() {
    	toteElevator.setPower(0);
    	toteElevator.setBrake(BrakePosition.ENGAGED);
    	rollerGripper.setRollerDirection(RollerGripperDirection.OFF);
    	rollerGripper.setGripper(ClawPosition.OPEN);
    }
    
    @Override
    protected void interrupted() {
    	end();
    }
}

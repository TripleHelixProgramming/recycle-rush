package org.usfirst.frc.team2363.robot.commands.elevators.tote;

import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.DriveBearHuggerToClearLimit;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.SimpleBearHuggerElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class UpAndOpen extends CommandGroup {
    
    public  UpAndOpen() {
    	addSequential(new MoveArmAndWait());
        addParallel(new ElevateAtSpeed(ElevatorPosition.TWO_TOTE_CARRY, 30));
        addParallel(new DriveBearHuggerToClearLimit());
        addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN));
    }
    
    private class MoveArmAndWait extends CommandGroup {
    	
    	public MoveArmAndWait() {
    		addParallel(new WaitCommand(0.5));
    		addSequential(new SimpleBearHuggerElevatorCommand(-BearHuggerElevator.BEAR_HUGGER_ELEVATOR_POWER), 0.25);
    	    addSequential(new DocOcArmYawCommand(0.2, DocOcArmPosition.LEFT_SECOND_CAN));
    	}
    }
}

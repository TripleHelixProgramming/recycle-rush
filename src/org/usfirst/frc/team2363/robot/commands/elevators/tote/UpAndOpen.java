package org.usfirst.frc.team2363.robot.commands.elevators.tote;

import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.SimpleElevateBearHuggerToHandoff;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
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
    	addSequential(new WaitCommand(1));
        addParallel(new ElevateAtSpeed(ElevatorPosition.TWO_TOTE_CARRY));
//        addParallel(new SimpleElevateBearHuggerToHandoff());
        addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN));
    }
}

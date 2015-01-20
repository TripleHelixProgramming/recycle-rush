package org.usfirst.frc.team2363.robot.commands.elevator;

import org.usfirst.frc.team2363.robot.subsystems.Elevator.ElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutomatedHumanPlayerStacking extends CommandGroup {
    
    public  AutomatedHumanPlayerStacking() {
    	addSequential(new ElevatorCommand(ElevatorPosition.CARRY));
        addSequential(new ElevatorCommand(ElevatorPosition.STACK_THREE_CARRY));
        }
}

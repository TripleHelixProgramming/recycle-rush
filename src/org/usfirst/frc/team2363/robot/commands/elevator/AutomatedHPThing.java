package org.usfirst.frc.team2363.robot.commands.elevator;

import org.usfirst.frc.team2363.robot.subsystems.Elevator.ElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutomatedHPThing extends CommandGroup {
    
    public  AutomatedHPThing() {
        addSequential(new GoToGround());
        addSequential(new GoToPosition(ElevatorPosition.STACK_THREE_CARRY));
    }
}

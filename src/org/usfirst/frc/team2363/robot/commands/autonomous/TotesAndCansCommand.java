package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveAtSpeedToTote;
import org.usfirst.frc.team2363.robot.commands.elevator.ElevatorCommand;
import org.usfirst.frc.team2363.robot.subsystems.Elevator.ElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TotesAndCansCommand extends CommandGroup {
    
    public  TotesAndCansCommand() {
        addParallel(new ElevatorCommand(ElevatorPosition.STACK_TWO_CARRY));
        addSequential(new DriveAtSpeedToTote(36, 0.04, 81));
        addSequential(new ElevatorCommand(ElevatorPosition.GROUND));
        addParallel(new ElevatorCommand(ElevatorPosition.STACK_TWO_CARRY));
        addSequential(new DriveAtSpeedToTote(48, 0.04, 81));
        addSequential(new ElevatorCommand(ElevatorPosition.GROUND));
        addParallel(new ElevatorCommand(ElevatorPosition.CARRY));
    }
}

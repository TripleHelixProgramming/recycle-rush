package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveAtSpeedToTote;
import org.usfirst.frc.team2363.robot.commands.elevator.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.grippers.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TotesAndCansCommand extends CommandGroup {
    
    public  TotesAndCansCommand() {
    	addParallel(new RollerGripperCommand(0, ClawPosition.OPEN));
        addParallel(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
        addSequential(new DriveAtSpeedToTote(36, 0.04, 81));
        addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
        addParallel(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
        addSequential(new DriveAtSpeedToTote(48, 0.04, 81));
        addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
        addParallel(new ElevateAtSpeed(ElevatorPosition.CARRY));
    }
}

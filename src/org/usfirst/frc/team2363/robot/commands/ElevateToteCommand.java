package org.usfirst.frc.team2363.robot.commands;

import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevateToteCommand extends CommandGroup {
    
    public  ElevateToteCommand(ElevatorPosition position) {
//    	addParallel(new CanTiltCommand(TiltPosition.UNTILT));
    	addSequential(new ElevateAtSpeed(position));
    }
}

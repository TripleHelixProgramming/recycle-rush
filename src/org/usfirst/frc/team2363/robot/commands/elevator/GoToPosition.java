package org.usfirst.frc.team2363.robot.commands.elevator;

import org.usfirst.frc.team2363.robot.commands.grippers.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.Elevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;
import org.usfirst.frc.team2363.robot.util.NoneCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToPosition extends CommandGroup {
    private ElevatorPosition position;
    
    public  GoToPosition(ElevatorPosition toPosition) {
    	this.position = toPosition;
    	if (position == ElevatorPosition.GROUND) {
    		addSequential(new NoneCommand());
    	} else {
    	addSequential(new ElevatorCommand(position));
    	addParallel(new RollerGripperCommand(0, ClawPosition.OPEN));
    	}
    }
}

package org.usfirst.frc.team2363.robot.commands.elevator;

import org.usfirst.frc.team2363.robot.RobotMap;
import org.usfirst.frc.team2363.robot.commands.grippers.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.Elevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToGround extends CommandGroup {
    
    public  GoToGround() {
    	addSequential(new RollerGripperCommand(RobotMap.ROLLER_POWER, ClawPosition.CLOSE));
    	addSequential(new ElevatorCommand(ElevatorPosition.GROUND));
//    	addSequential(new RollerGripperCommand(0, ClawPosition.CLOSE));
    }
}

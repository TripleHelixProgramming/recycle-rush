package org.usfirst.frc.team2363.robot.commands.elevators;

import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.WaitForToteCommand;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FinishStack extends CommandGroup {
    
    public  FinishStack() {
    	addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
    	addSequential(new WaitForToteCommand());
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE));
        addSequential(new ElevateBothAtSpeed(ElevatorPosition.GROUND, 20));
//        addSequential(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN), 0.5);
    }
}

package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DrivePowerStraightAtTote;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveStraightAtSpeedToTote;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHugger;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndGetFirstCan extends CommandGroup {
    
    public  MoveAndGetFirstCan() {
    	addParallel(new FromFloorToBearHugger());
    	addSequential(new ElevateAtSpeed(ElevatorPosition.STEP_CARRY, 20));
    	addSequential(new DrivePowerStraightAtTote(0.6, 46));
//    	addSequential(new DriveStraightAtSpeedToTote(18, 46, 0.4));
    }
}

package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveAtSpeedToTote;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerSameDirection;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeTotesNoCans extends CommandGroup {
    
    public  ThreeTotesNoCans() {
    	super("Totes and Cans");
//    	addParallel(new RollerGripperCommand(0, ClawPosition.CLOSE));
    	addParallel(new RollerSameDirection());
        addParallel(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
        addSequential(new DriveAtSpeedToTote(36, 0.04, 81));
        addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
        addParallel(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
        addSequential(new DriveAtSpeedToTote(48, 0.04, 81));
        addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN));
        addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
        addParallel(new ElevateAtSpeed(ElevatorPosition.CARRY));
    }
}

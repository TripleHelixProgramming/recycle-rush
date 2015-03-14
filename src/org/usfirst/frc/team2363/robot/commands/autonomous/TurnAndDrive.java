package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DrivePowerStraightAtTote;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.DriveBearHuggerToTopLimit;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnAndDrive extends CommandGroup {
    
    public  TurnAndDrive() {
    	addParallel(new DriveBearHuggerToTopLimit());
    	addSequential(new TurnToAngle(90, 0));
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OUT, ClawPosition.CLOSE));
    	addSequential(new DrivePowerStraightAtTote(1, 20));
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OUT, ClawPosition.OPEN), 0.5);
    }
}

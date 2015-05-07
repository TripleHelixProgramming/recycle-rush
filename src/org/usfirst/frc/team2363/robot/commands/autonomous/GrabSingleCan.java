package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DrivePowerStraightAtTote;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveStraightAtSpeed;
import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHuggerAndClear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabSingleCan extends CommandGroup {
    
    public  GrabSingleCan() {
    	
        addSequential(new FromFloorToBearHuggerAndClear());
//        addSequential(new DriveStraightAtSpeed(18, 80));
    }
}

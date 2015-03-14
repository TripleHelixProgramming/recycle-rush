package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.DriveBearHuggerToTopLimit;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ClearCan extends CommandGroup {

    public ClearCan() {
    	addSequential(new DocOcArmYawCommand(0.15, DocOcArmPosition.LEFT_CLEAR_CAN));
    	addParallel(new DriveBearHuggerToTopLimit());
    }
}

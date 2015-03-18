package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SecondAutoCanForTwoTote extends CommandGroup {
    
    public  SecondAutoCanForTwoTote() {
    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_OFF_FLOOR, 1));
    	addSequential(new DocOcArmYawCommand(0.2, DocOcArmPosition.LEFT_SECOND_CAN));
    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_STOWED, 1));

    }
}

package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SecondAutoCan extends CommandGroup {
    
    public  SecondAutoCan() {
    	addSequential(new DocOcArmYawCommand(0.2, DocOcArmPosition.LEFT_SECOND_CAN));
    	addParallel(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_OFF_FLOOR, 1));

    }
}

package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveStraightAtSpeed;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHuggerAndClear;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.MoveCanToStow;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.WaitForCan;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TwoCansNoTotes extends CommandGroup {
    
    public  TwoCansNoTotes() {
    	addSequential(new FromFloorToBearHuggerAndClear());
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveStraightAtSpeed(36, 33));
    	addSequential(new DocOcArmYawCommand(0.2, DocOcArmPosition.LEFT_SECOND_CAN));
    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_FLOOR, 1));
    	addSequential(new ActuateDocOcGripper(ClawPosition.OPEN), 0.5);
		addSequential(new WaitForCan());
    	addSequential(new MoveCanToStow(1));
    	addSequential(new TurnToAngle(110, 0.5));
    	addSequential(new DriveStraightAtSpeed(36, 81));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHuggerAndClear;
import org.usfirst.frc.team2363.robot.commands.grippers.bearhugger.CanTiltCommand;
import org.usfirst.frc.team2363.robot.subsystems.BearHugger.TiltPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneCanReadyForTeleOp extends CommandGroup {
    
    public  OneCanReadyForTeleOp() {
    	addSequential(new CanTiltCommand(TiltPosition.UNTILT));
    	addSequential(new FromFloorToBearHuggerAndClear());
    	addSequential(new CanTiltCommand(TiltPosition.TILT));
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

package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DrivePowerStraightAtTote;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveStraightAtSpeed;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.DriveBearHuggerToClearLimit;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.HomeToteElevator;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.SecondAutoCan;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.SecondAutoCanForTwoTote;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TwoCanTwoTotesEject extends CommandGroup {
    
    public  TwoCanTwoTotesEject() {
    	addSequential(new HomeToteElevator());
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE));
    	addSequential(new MoveAndGetFirstCan());
    	addParallel(new DocOcArmYawCommand(0.2, DocOcArmPosition.LEFT_CLEAR_CAN));
    	addSequential(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY, 20));
    	addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
    	addParallel(new DriveBearHuggerToClearLimit());
    	addSequential(new DrivePowerStraightAtTote(0.55, 23));
    	addParallel(new ElevateAtSpeed(ElevatorPosition.GROUND, 30));
    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_SECOND_CAN, 1));
    	addSequential(new DocOcArmYawCommand(0.15, DocOcArmPosition.LEFT_FLOOR));
    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_FLOOR, 1));
    	addParallel(new ActuateDocOcGripper(ClawPosition.OPEN));
    	addParallel(new DriveBearHuggerToClearLimit());
    	addSequential(new WaitCommand(0.20));
    	addParallel(new SecondAutoCanForTwoTote());
    	addSequential(new WaitCommand(0.20));
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OUT, ClawPosition.CLOSE));
    	addSequential(new DriveStraightAtSpeed(36, -83));
    	addSequential(new TurnToAngle(45, 0.65));
    	
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

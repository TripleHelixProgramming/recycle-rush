package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveToDistanceCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.DriveBearHuggerToClearLimit;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.HomeToteElevator;
import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHugger;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneCanOneTote extends CommandGroup {
    
    public  OneCanOneTote() {
    	addSequential(new HomeToteElevator());
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE));
//    	addSequential(new MoveAndGetFirstCan());
    	addSequential(new FromFloorToBearHugger());
//    	addSequential(new ElevateAtSpeed(ElevatorPosition.STEP_CARRY, 20));
    	addParallel(new DocOcArmYawCommand(0.2, DocOcArmPosition.LEFT_CLEAR_CAN));
    	addSequential(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY, 20));
    	addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
//    	addParallel(new DriveBearHuggerToClearLimit());
    	addSequential(new TurnToAngle(90, .7));
    	addSequential(new DriveToDistanceCommand(0.7, 80));
    	// 4. Drive forward
    }
}

package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DrivePowerStraightAtTote;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveStraightAtSpeed;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveStraightAtSpeedToTote;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.DriveBearHuggerToClearLimit;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.HomeToteElevator;
import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHugger;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.SecondAutoCan;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.WaitForCan;
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
public class TotesAndCansCommand extends CommandGroup {
    
    public  TotesAndCansCommand() {
    	super("Totes and Cans");
    	addSequential(new HomeToteElevator());
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE));
    	addSequential(new MoveAndGetFirstCan());
    	addParallel(new DocOcArmYawCommand(0.2, DocOcArmPosition.LEFT_CLEAR_CAN));
    	addSequential(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY, 20));
    	addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
    	addParallel(new DriveBearHuggerToClearLimit());
    	addSequential(new DrivePowerStraightAtTote(0.7, 23));
//    	addSequential(new DriveStraightAtSpeed(24, 23));
    	addParallel(new ElevateAtSpeed(ElevatorPosition.GROUND, 30));
    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_SECOND_CAN, 1));
    	addSequential(new DocOcArmYawCommand(0.15, DocOcArmPosition.LEFT_FLOOR));
    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_FLOOR, 1));
    	addParallel(new ActuateDocOcGripper(ClawPosition.OPEN));
    	addParallel(new DriveBearHuggerToClearLimit());
    	addSequential(new WaitCommand(0.25));
    	addSequential(new WaitForCan());
    	addSequential(new SecondAutoCan());
//    	addSequential(new WaitCommand(0.5));
    	addParallel(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY, 20));
    	addSequential(new DrivePowerStraightAtTote(0.8, 75));
//    	addSequential(new DriveStraightAtSpeed(36, 81));
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE));
    	addParallel(new ElevateAtSpeed(ElevatorPosition.GROUND, 30));
    	addSequential(new TurnToAngle(90, 0.7));
    	addSequential(new DrivePowerStraightAtTote(0.8, 70));
//    	addSequential(new DriveStraightAtSpeed(48, 70));
    	addParallel(new DriveBearHuggerToClearLimit());
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OUT, ClawPosition.CLOSE));
    }
}

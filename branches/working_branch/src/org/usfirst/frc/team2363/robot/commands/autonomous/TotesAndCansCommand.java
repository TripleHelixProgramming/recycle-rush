package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DrivePowerStraightAtTote;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHugger;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.SecondAutoCan;
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
    	addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE));
    	addSequential(new FromFloorToBearHugger());
    	addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
    	addParallel(new ClearCan());
    	addSequential(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY, 20));
    	addSequential(new DrivePowerStraightAtTote(0.7, 72));
    	addParallel(new ElevateAtSpeed(ElevatorPosition.GROUND, 25));
    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_SECOND_CAN, 1));
    	addSequential(new DocOcArmYawCommand(0.15, DocOcArmPosition.LEFT_FLOOR));
    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_FLOOR, 0.75));
    	addParallel(new ActuateDocOcGripper(ClawPosition.OPEN));
    	addSequential(new WaitCommand(0.25));
    	addParallel(new SecondAutoCan());
    	addSequential(new WaitCommand(0.5));
    	addParallel(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY, 25));
    	addSequential(new DrivePowerStraightAtTote(0.7, 81));
    	addParallel(new ElevateAtSpeed(ElevatorPosition.GROUND, 25));
    	addSequential(new TurnAndDrive());
    }
}

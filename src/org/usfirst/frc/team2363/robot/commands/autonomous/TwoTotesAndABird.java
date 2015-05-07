package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveStraightAtSpeed;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team2363.robot.commands.drivetrain.WaitForDistance;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.DriveBearHuggerToClearLimit;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHugger;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.MoveCanToStow;
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
public class TwoTotesAndABird extends CommandGroup {
    
    public  TwoTotesAndABird() {
    	addSequential(new FirstMoveAndCan());
    	addSequential(new MoveAndPositionForPickup());
    	addSequential(new GrabSecondCan());
    	addSequential(new MoveAndStowCan());
//    	addSequential(new AquireThirdTote());
//    	addSequential(new TurnAndMoveToAutoZone());
//    	addSequential(new PlaceTotesAndCan());
    	
    }
    
    private class FirstMoveAndCan extends CommandGroup {
    	public FirstMoveAndCan() {
    		addParallel(new FirstMove());
    		addSequential(new FromFloorToBearHugger());
    		addParallel(new DocOcArmYawCommand(0.15, DocOcArmPosition.LEFT_CLEAR_CAN));
    		addSequential(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY, 30));
    	}
    }
    
    private class FirstMove extends CommandGroup {
    	public FirstMove() {
    		addSequential(new WaitCommand(1));
    		addParallel(new DriveStraightAtSpeed(22, 50));
    	}
    }
    
    private class MoveAndPositionForPickup extends CommandGroup {
    	public MoveAndPositionForPickup() {
    		addParallel(new DriveStraightAtSpeed(24, 24));
    		addParallel(new WaitForDistanceAndClose());
    		addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_SECOND_CAN, 1));
        	addSequential(new DocOcArmYawCommand(0.1, DocOcArmPosition.LEFT_FLOOR));
        	addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE), 0.1);
    	}
    }
    
    private class WaitForDistanceAndClose extends CommandGroup {
    	public WaitForDistanceAndClose() {
    		addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.OPEN));
    		addSequential(new WaitForDistance(12));
    	}
    }
    
    private class GrabSecondCan extends CommandGroup {
    	public GrabSecondCan() {
    		addParallel(new ElevateAtSpeed(ElevatorPosition.GROUND, 30));
    		addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN));
    		addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_FLOOR, 1));
    		addSequential(new ActuateDocOcGripper(ClawPosition.OPEN), 0.5);
    		addSequential(new WaitForCan());
    		addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE), 0.1);
    	}
    }
    private class MoveAndStowCan extends CommandGroup {
    	public MoveAndStowCan() {
//    		addParallel(new DriveStraightAtSpeed(36, 54));
    		addSequential(new MoveCanToStow(1));
//    		addSequential(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY, 30));
    	}
    }
    private class AquireThirdTote extends CommandGroup {
    	public AquireThirdTote() {
    		addParallel(new DriveStraightAtSpeed(24, 26));
    		addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE), 1);
    	}
    }
 
    private class TurnAndMoveToAutoZone extends CommandGroup {
    	public TurnAndMoveToAutoZone() {
    		addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
    		addSequential(new TurnToAngle(90, 0.7));
    		addSequential(new WaitCommand(0.5));
    		addParallel(new ElevateAtSpeed(ElevatorPosition.GROUND, 30));
    		addParallel(new DriveStraightAtSpeed(48, 80));
    		addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE), 0.1);
    	}
    }
    private class PlaceTotesAndCan extends CommandGroup {
    	public PlaceTotesAndCan() {
    		addParallel(new DriveBearHuggerToClearLimit());
    		addParallel(new RollerGripperCommand(RollerGripperDirection.OUT, ClawPosition.CLOSE));
    	}
    }
}

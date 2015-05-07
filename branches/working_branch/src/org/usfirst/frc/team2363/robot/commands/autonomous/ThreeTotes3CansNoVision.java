package org.usfirst.frc.team2363.robot.commands.autonomous;

import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveStraightAtSpeed;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnToAngle;
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
public class ThreeTotes3CansNoVision extends CommandGroup {
    
    public  ThreeTotes3CansNoVision() {
    	addSequential(new FirstMoveAndCan());
    	addSequential(new MoveAndPositionForPickup());
    	addSequential(new GrabSecondCan());
    	addSequential(new MoveAndStowCan());
    	addSequential(new AquireThirdTote());
    	addSequential(new AquireThirdCan());
    	addSequential(new TurnAndMoveToAutoZone());
    	addSequential(new PlaceTotesAndCan());
    	
    }
    
    private class FirstMoveAndCan extends CommandGroup {
    	public FirstMoveAndCan() {
    		addParallel(new FromFloorToBearHugger());
    		addSequential(new WaitCommand(0.5));
    		addParallel(new DriveStraightAtSpeed(24, 55));
    		addSequential(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
    	}
    }
    private class MoveAndPositionForPickup extends CommandGroup {
    	public MoveAndPositionForPickup() {
    		addParallel(new DriveStraightAtSpeed(24, 26));
    		addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
    		addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_SECOND_CAN, 1));
        	addSequential(new DocOcArmYawCommand(0.15, DocOcArmPosition.LEFT_FLOOR));
    	}
    }
    private class GrabSecondCan extends CommandGroup {
    	public GrabSecondCan() {
    		addParallel(new ElevateAtSpeed(ElevatorPosition.GROUND));
    		addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN));
    		addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_FLOOR, 1));
    		addSequential(new ActuateDocOcGripper(ClawPosition.OPEN), 0.5);
    		addSequential(new WaitForCan());
    	}
    }
    private class MoveAndStowCan extends CommandGroup {
    	public MoveAndStowCan() {
    		addParallel(new DriveStraightAtSpeed(36, 55));
    		addSequential(new MoveCanToStow(1));
    		addSequential(new ElevateAtSpeed(ElevatorPosition.ONE_TOTE_CARRY));
    	}
    }
    private class AquireThirdTote extends CommandGroup {
    	public AquireThirdTote() {
    		addParallel(new DriveStraightAtSpeed(24, 26));
    		addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
    	}
    }
    private class AquireThirdCan extends CommandGroup {
    	public AquireThirdCan() {
    		addParallel(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.OPEN));
    		addSequential(new ElevateAtSpeed(ElevatorPosition.TWO_TOTE_CARRY));
    		addParallel(new DriveStraightAtSpeed(24, 26));
    	}
    }
    private class TurnAndMoveToAutoZone extends CommandGroup {
    	public TurnAndMoveToAutoZone() {
    		addParallel(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.CLOSE));
    		addSequential(new TurnToAngle(90, 0.7));
    		addParallel(new DriveStraightAtSpeed(48, 80));
    	}
    }
    private class PlaceTotesAndCan extends CommandGroup {
    	public PlaceTotesAndCan() {
    		addParallel(new RollerGripperCommand(RollerGripperDirection.OUT, ClawPosition.CLOSE));
    		addSequential(new ElevateAtSpeed(ElevatorPosition.GROUND));
    	}
    }
}

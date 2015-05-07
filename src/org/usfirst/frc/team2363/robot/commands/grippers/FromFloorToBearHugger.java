package org.usfirst.frc.team2363.robot.commands.grippers;

import java.util.Date;

import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.ElevateBearHuggerCommand;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.SimpleBearHuggerElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.bearhugger.BearHuggerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcToYaw;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.WaitForCan;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FromFloorToBearHugger extends CommandGroup {
	
	private Date startTime;
    
    public  FromFloorToBearHugger() {
//    	addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_FLOOR, 1));
    	addParallel(new ActuateDocOcGripper(ClawPosition.OPEN));
    	addSequential(new WaitCommand(0.2));
    	addParallel(new BearHuggerGripperCommand(ClawPosition.CLOSE));
        addSequential(new ElevateDocOcArmToCommand(DocOcArmPosition.PREP_FOR_HANDOFF, 1));
        addParallel(new BearHuggerGripperCommand(ClawPosition.OPEN));
        addSequential(new ElevateDocOcToYaw(1));
        addSequential(new ElevateBearHuggerCommand(BearHuggerElevator.CAN_HAND_OFF, 1));
        addParallel(new BearHuggerGripperCommand(ClawPosition.CLOSE), 0.5);
        addSequential(new WaitCommand(0.25));
        addParallel(new ActuateDocOcGripper(ClawPosition.CLOSE), 0.5);
        addSequential(new WaitCommand(0.1));
        addSequential(new SimpleBearHuggerElevatorCommand(-BearHuggerElevator.BEAR_HUGGER_ELEVATOR_POWER), 0.25);
    }
    
    protected void initialize() {
    	startTime = new Date();
    }
    
    protected void end() {
    	SmartDashboard.putNumber("Finish Time", (new Date().getTime() - startTime.getTime()) / 1000.0);
    }
}

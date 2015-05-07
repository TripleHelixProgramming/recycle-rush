package org.usfirst.frc.team2363.robot.commands.grippers;

import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.ElevateBearHuggerCommand;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.SimpleBearHuggerElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.bearhugger.BearHuggerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcToYaw;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class StowToBearHuggerAndClear extends CommandGroup {
    
    public  StowToBearHuggerAndClear() {
    	addParallel(new BearHuggerGripperCommand(ClawPosition.OPEN));
    	addSequential(new ElevateDocOcToYaw(1));
    	addSequential(new ElevateBearHuggerCommand(BearHuggerElevator.CAN_HAND_OFF, 0.5));
    	addParallel(new BearHuggerGripperCommand(ClawPosition.CLOSE), 0.5);
        addSequential(new WaitCommand(0.25));
        addParallel(new ActuateDocOcGripper(ClawPosition.CLOSE), 0.5);
        addSequential(new WaitCommand(0.1));
        addSequential(new SimpleBearHuggerElevatorCommand(-BearHuggerElevator.BEAR_HUGGER_ELEVATOR_POWER), 0.25);
        addSequential(new DocOcArmYawCommand(0.2, DocOcArmPosition.LEFT_SECOND_CAN));
    }
}

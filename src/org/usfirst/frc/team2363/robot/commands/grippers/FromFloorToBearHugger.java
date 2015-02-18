package org.usfirst.frc.team2363.robot.commands.grippers;

import org.usfirst.frc.team2363.robot.commands.elevator.ElevateBearHuggerCommand;
import org.usfirst.frc.team2363.robot.commands.elevator.HomeBearHugger;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FromFloorToBearHugger extends CommandGroup {
    
    public  FromFloorToBearHugger() {
    	addParallel(new BearHuggerGripperCommand(ClawPosition.CLOSE));
    	addParallel(new DocOcArmYawCommand(0.1, DocOcArmPosition.LEFT_OFF_FLOOR));
        addSequential(new ElevateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_OFF_FLOOR, 10));
        addParallel(new BearHuggerGripperCommand(ClawPosition.OPEN));
        addParallel(new DocOcArmYawCommand(0.05, DocOcArmPosition.PREP_FOR_HANDOFF));
        addSequential(new ElevateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.PREP_FOR_HANDOFF, 10));
        addParallel(new ElevateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_HANDOFF, 10));
        addSequential(new DocOcArmYawCommand(0.05, DocOcArmPosition.LEFT_HANDOFF));
        addSequential(new WaitCommand(1));
        addSequential(new ElevateBearHuggerCommand(BearHuggerElevator.CAN_HAND_OFF, 0.75));
        addParallel(new BearHuggerGripperCommand(ClawPosition.CLOSE));
        addSequential(new WaitCommand(1));
        addParallel(new ActuateDocOcGripper(ClawPosition.CLOSE));
        addSequential(new WaitCommand(1));
        addParallel(new HomeBearHugger());
//        addSequential(new WaitCommand(2));
    }
}

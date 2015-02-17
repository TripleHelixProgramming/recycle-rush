package org.usfirst.frc.team2363.robot.commands.grippers;

import org.usfirst.frc.team2363.robot.commands.elevator.ElevateBearHuggerCommand;
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
    	addParallel(new RotateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_OFF_FLOOR, 10));
        addSequential(new ElevateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_OFF_FLOOR, 10));
        addParallel(new BearHuggerGripperCommand(ClawPosition.OPEN));
        addParallel(new RotateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.PREP_FOR_HANDOFF, 10));
        addSequential(new ElevateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.PREP_FOR_HANDOFF, 10));
        addParallel(new ElevateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_HANDOFF, 5));
        addSequential(new RotateDocOcArmToCommand(leftDocOcArm, DocOcArmPosition.LEFT_HANDOFF, 5));
//        addSequential(new ElevateBearHuggerCommand());
//        addParallel(new BearHuggerGripperCommand(ClawPosition.CLOSE));
//        addSequential(new WaitCommand(2));
//        addParallel(new ActuateDocOcGriper(ClawPosition.CLOSE));
//        addSequential(new WaitCommand(0.5));
//        addParallel(new SimpleBearHuggerElevatorCommand(-0.75));
//        addSequential(new WaitCommand(2));
    }
}

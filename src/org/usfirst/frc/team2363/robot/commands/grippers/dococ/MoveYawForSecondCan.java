package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveYawForSecondCan extends Command {

    public MoveYawForSecondCan() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.leftDocOcArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.leftDocOcArm.setYaw(0.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.leftDocOcArm.getYawPosition() > DocOcArmPosition.LEFT_SECOND_CAN.getYaw();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftDocOcArm.setYaw(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.leftDocOcArm.setYaw(0);
    }
}

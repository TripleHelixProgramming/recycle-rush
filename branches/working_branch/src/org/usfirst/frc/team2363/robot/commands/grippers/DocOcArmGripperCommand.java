package org.usfirst.frc.team2363.robot.commands.grippers;

import org.usfirst.frc.team2363.robot.subsystems.DocOcArm;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DocOcArmGripperCommand extends Command {

	private DocOcArm arm;
	private ClawPosition position;
	
    public DocOcArmGripperCommand(DocOcArm arm, ClawPosition position) {
        this.arm = arm;
        this.position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arm.setClaw(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

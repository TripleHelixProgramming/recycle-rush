package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

/**
 *
 */
public class MoveCanToStow extends ElevateDocOcArmToCommand {

    public MoveCanToStow(double maxPower) {
        super(DocOcArmPosition.LEFT_HANDOFF, maxPower);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.leftDocOcArm.setYaw(0.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isFinished() && Robot.leftDocOcArm.getYawPosition() > DocOcArmPosition.LEFT_SECOND_CAN.getYaw();
    }

    // Called once after isFinished returns true
    protected void end() {
    	super.end();
    	Robot.leftDocOcArm.setYaw(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
    	Robot.leftDocOcArm.setYaw(0);
    }
}

package org.usfirst.frc.team2363.robot.commands.elevators.tote;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.BrakePosition;
import org.usfirst.frc.team2363.robot.util.ClawPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HomeToteElevator extends Command {

    public HomeToteElevator() {
        requires(Robot.toteElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rollerGripper.setGripper(ClawPosition.OPEN);
    	Robot.toteElevator.setBrake(BrakePosition.OFF);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.toteElevator.setPower(-0.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.toteElevator.isAtBottomLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.toteElevator.setPower(0);
    	Robot.toteElevator.resetAtBottom();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.toteElevator.setPower(0);
    }
}

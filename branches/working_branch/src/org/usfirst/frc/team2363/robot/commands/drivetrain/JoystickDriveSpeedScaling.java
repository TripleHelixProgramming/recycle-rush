package org.usfirst.frc.team2363.robot.commands.drivetrain;

import static org.usfirst.frc.team2363.robot.Robot.drivetrain;
import static org.usfirst.frc.team2363.robot.Robot.oi;

import org.usfirst.frc.team2363.robot.OI;
import org.usfirst.frc.team2363.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickDriveSpeedScaling extends Command {

    public JoystickDriveSpeedScaling() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentSpeed = (drivetrain.getLeftSpeed() + drivetrain.getRightSpeed()) / 2;
    	double currentSpeedPercentage = currentSpeed / RobotMap.ROBOT_TOP_SPEED;
    	double turnPower = OI.getTurnScaling(currentSpeedPercentage) * oi.getTurn();
    	drivetrain.arcadeDrive(oi.getThrottle(), turnPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

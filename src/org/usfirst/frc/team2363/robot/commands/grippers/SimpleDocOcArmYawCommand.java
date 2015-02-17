package org.usfirst.frc.team2363.robot.commands.grippers;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;

/**
 *
 */
public class SimpleDocOcArmYawCommand extends Command {

	private double power;
   
	public SimpleDocOcArmYawCommand(double power) {
        this.power = power;
        requires(Robot.leftDocOcArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftDocOcArm.setControlMethod(ControlMode.PercentVbus);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftDocOcArm.setYawSpeed(power);
    	System.out.println("Rotate Arm");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	leftDocOcArm.setYawSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	leftDocOcArm.setYawSpeed(0);
    }
}

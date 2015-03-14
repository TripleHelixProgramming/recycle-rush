package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;

/**
 *
 */
public class SimpleDocOcArmElevationCommand extends Command {

	private double power;
   
	public SimpleDocOcArmElevationCommand(double power) {
        this.power = power;
        requires(Robot.leftDocOcArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftDocOcArm.setElevationControlMethod(ControlMode.PercentVbus);
//    	leftDocOcArm.setControlEnabled(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftDocOcArm.setElevation(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return leftDocOcArm.getElevationCurrent() > 70;
    }

    // Called once after isFinished returns true
    protected void end() {
    	leftDocOcArm.setElevation(0);
    	System.out.println("Elevate Arm");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	leftDocOcArm.setElevation(0);
    }
}

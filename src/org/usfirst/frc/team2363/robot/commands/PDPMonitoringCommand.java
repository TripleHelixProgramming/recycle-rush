package org.usfirst.frc.team2363.robot.commands;

import org.usfirst.frc.team2363.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class PDPMonitoringCommand extends Command {

    public PDPMonitoringCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Compressor", Robot.compressor.getCompressorCurrent());
    	SmartDashboard.putNumber("Channel 0", Robot.pdp.getCurrent(0));
    	SmartDashboard.putNumber("Channel 1", Robot.pdp.getCurrent(1));
    	SmartDashboard.putNumber("Channel 2", Robot.pdp.getCurrent(2));
    	SmartDashboard.putNumber("Channel 3", Robot.pdp.getCurrent(3));
    	SmartDashboard.putNumber("Channel 4", Robot.pdp.getCurrent(4));
    	SmartDashboard.putNumber("Channel 5", Robot.pdp.getCurrent(5));
    	SmartDashboard.putNumber("Channel 6", Robot.pdp.getCurrent(6));
    	SmartDashboard.putNumber("Channel 7", Robot.pdp.getCurrent(7));
    	SmartDashboard.putNumber("Channel 8", Robot.pdp.getCurrent(8));
    	SmartDashboard.putNumber("Channel 9", Robot.pdp.getCurrent(9));
    	SmartDashboard.putNumber("Channel 10", Robot.pdp.getCurrent(10));
    	SmartDashboard.putNumber("Channel 11", Robot.pdp.getCurrent(11));
    	SmartDashboard.putNumber("Channel 12", Robot.pdp.getCurrent(12));
    	SmartDashboard.putNumber("Channel 13", Robot.pdp.getCurrent(13));
    	SmartDashboard.putNumber("Channel 14", Robot.pdp.getCurrent(14));
    	SmartDashboard.putNumber("Channel 15", Robot.pdp.getCurrent(15));
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

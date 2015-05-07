package org.usfirst.frc.team2363.robot.commands.elevators.bearhugger;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2363.robot.Robot.bearHuggerElevator;

/**
 *
 */
public class SimpleBearHuggerElevatorCommand extends Command {

	private double power;
	
    public SimpleBearHuggerElevatorCommand(double power) {
    	this.power = power;
        requires(bearHuggerElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Start Elevate Bear Hugger");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	bearHuggerElevator.drive(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	bearHuggerElevator.drive(0);
    	System.out.println("Finish Elevate Bear Hugger");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	bearHuggerElevator.drive(0);
    }
}

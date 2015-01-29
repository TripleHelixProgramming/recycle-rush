package org.usfirst.frc.team2363.robot.commands.elevator;

import org.usfirst.frc.team2363.robot.RobotMap;
import org.usfirst.frc.team2363.robot.subsystems.Elevator.BrakePosition;
import org.usfirst.frc.team2363.robot.subsystems.Elevator.ElevatorPosition;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.Robot.elevator;

/**
 *
 */
public class ElevatorCommand extends PIDCommand {

	private ElevatorPosition position;
	
    public ElevatorCommand(ElevatorPosition position) {
    	super(1.5, 0, 1);
        requires(elevator);
        this.position = position;
        setSetpoint(position.getHeight());
        getPIDController().setAbsoluteTolerance(0.1);
        getPIDController().setOutputRange(-0.75, 1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevator.setBrake(BrakePosition.OFF);
    	getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double current = elevator.getElevatorCurrent();
    	if (current > RobotMap.ELEVATOR_MOTOR_MAX_CURRENT) {
    		elevator.setPower(0);
    		cancel();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return getPIDController().onTarget() || elevator.isAtTopLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (position != ElevatorPosition.GROUND) {
    		elevator.setBrake(BrakePosition.ENGAGED);
    	}
    	getPIDController().disable();
    	SmartDashboard.putString("Current elevator position", position.getName());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.getPIDController().disable();
    }

	@Override
	protected double returnPIDInput() {
		return elevator.getPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Elevator Power", output);
		elevator.setPower(output);
	}	
}

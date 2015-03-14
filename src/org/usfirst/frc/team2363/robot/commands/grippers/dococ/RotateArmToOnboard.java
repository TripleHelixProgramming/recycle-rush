package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;
/**
 *
 */
public class RotateArmToOnboard extends PIDCommand {
private double position;

    public RotateArmToOnboard(double position, double maxPower) {
    	super(0.002, 0, 0);
        this.position = position;
        this.getPIDController().setOutputRange(-maxPower, maxPower);
        getPIDController().setAbsoluteTolerance(5);
    }
    
    public RotateArmToOnboard(double position) {
    	this(position, 0.3);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftDocOcArm.setYawControlMethod(ControlMode.PercentVbus);
    	this.setSetpoint(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	leftDocOcArm.setYaw(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	leftDocOcArm.setYaw(0);
    }

	@Override
	protected double returnPIDInput() {
		return leftDocOcArm.getYawPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Yaw Power", output);
		SmartDashboard.putNumber("Yaw Error", this.getPIDController().getError());
		leftDocOcArm.setYaw(output);
		
	}
}

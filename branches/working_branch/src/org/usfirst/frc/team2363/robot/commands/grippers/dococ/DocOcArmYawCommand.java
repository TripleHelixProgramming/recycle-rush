package org.usfirst.frc.team2363.robot.commands.grippers.dococ;

import org.usfirst.frc.team2363.robot.Robot;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static org.usfirst.frc.team2363.robot.Robot.leftDocOcArm;

/**
 *
 */
public class DocOcArmYawCommand extends Command {

	private double power;
	private DocOcArmPosition position;
	private final double P = 0.05;
   
	public DocOcArmYawCommand(double power, DocOcArmPosition position) {
        this.power = Math.abs(power);
        this.position = position;
        requires(Robot.leftDocOcArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftDocOcArm.setYawControlMethod(ControlMode.PercentVbus);
//    	if (leftDocOcArm.getYawPosition() > position) {
//    		power = -Math.abs(power);
//    	} else {
//    		power = Math.abs(power);
//    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double distance = position.getYaw() - leftDocOcArm.getYawPosition();
    	double turnPower = distance * P;
    	SmartDashboard.putNumber("Yaw Distance", distance);
    	if (turnPower > power) {
    		leftDocOcArm.setYaw(power);
    	} else if (turnPower < -power) {
    		leftDocOcArm.setYaw(-power);
    	} else {
    		leftDocOcArm.setYaw(turnPower);
    	}
    	
    	System.out.println("Rotate Arm");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(leftDocOcArm.getYawPosition() - position.getYaw()) < 5
        		|| leftDocOcArm.getYawCurrent() > 70;      		
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
}

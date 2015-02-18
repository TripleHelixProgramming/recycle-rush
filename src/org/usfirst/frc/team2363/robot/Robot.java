
package org.usfirst.frc.team2363.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2363.robot.commands.elevator.HomeBearHugger;
import org.usfirst.frc.team2363.robot.commands.elevator.HomeToteElevator;
import org.usfirst.frc.team2363.robot.subsystems.BearHugger;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm;
import org.usfirst.frc.team2363.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper;
import org.usfirst.frc.team2363.robot.util.VisionProcessor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//	static {
	//		PropertyReader.loadProperties("RobotChannels.properties");
	//	}

	public static OI oi;
	public static PowerDistributionPanel pdp;
	public static Drivetrain drivetrain;
	public static ToteElevator toteElevator;
	public static RollerGripper rollerGripper;
	public static DocOcArm leftDocOcArm;
	public static DocOcArm rightDocOcArm;
	public static BearHuggerElevator bearHuggerElevator;
	public static BearHugger bearHugger;
	
	public static Compressor compressor;
	public static VisionProcessor visionProcessor;
	private Command autonomousCommand;

	public Robot() {
		pdp = new PowerDistributionPanel();
		drivetrain = new Drivetrain();
		toteElevator = new ToteElevator();
		rollerGripper = new RollerGripper();
		leftDocOcArm = new DocOcArm(RobotMap.LEFT_DOC_OC_ARM_OPEN_CHANNEL, 
				RobotMap.LEFT_DOC_OC_ARM_CLOSE_CHANNEL, 
				RobotMap.LEFT_DOC_OC_ARM_YAW_CHANNEL, 
				RobotMap.LEFT_DOC_OC_ARM_ELEVATE_CHANNEL);
		bearHuggerElevator = new BearHuggerElevator();
		bearHugger = new BearHugger();
		
		compressor = new Compressor();
		oi = new OI();
		visionProcessor = new VisionProcessor();
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		visionProcessor.start();
		SmartDashboard.putData(toteElevator);
	}

	public void disabledPeriodic() {
		SmartDashboard.putNumber("Heading", drivetrain.getHeading());
		
		SmartDashboard.putNumber("Tote Encoder Position", toteElevator.getPosition());
		SmartDashboard.putNumber("Tote Encoder Speed", toteElevator.getElevatorSpeed());
		
		SmartDashboard.putNumber("Bear Hugger Encoder Position", bearHuggerElevator.getPosition());
		
		SmartDashboard.putNumber("Doc Oc Arm Elevation Position", leftDocOcArm.getElevationPosition());
		SmartDashboard.putNumber("Doc Oc Arm Yaw Position", leftDocOcArm.getYawPosition());
		
		SmartDashboard.putNumber("DOA Elevation Speed", leftDocOcArm.getElevationSpeed());
		SmartDashboard.putNumber("Doc Oc Arm Yaw Speed", leftDocOcArm.getYawSpeed());
		
		SmartDashboard.putBoolean("At Bottom", toteElevator.isAtBottomLimit());
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomousCommand = oi.getAutoCommand();
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null) autonomousCommand.cancel();
		new HomeToteElevator().start();
		new HomeBearHugger().start();
	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Tote Encoder Position", toteElevator.getPosition());
		SmartDashboard.putNumber("Tote Encoder Speed", toteElevator.getElevatorSpeed());
		SmartDashboard.putNumber("Heading", drivetrain.getHeading());
		
		SmartDashboard.putNumber("Bear Hugger Encoder Position", bearHuggerElevator.getPosition());
		
		SmartDashboard.putNumber("Doc Oc Arm Elevation Position", leftDocOcArm.getElevationPosition());
		SmartDashboard.putNumber("Doc Oc Arm Yaw Position", leftDocOcArm.getYawPosition());
		
		SmartDashboard.putNumber("DOA Elevation Speed", leftDocOcArm.getElevationSpeed());
		SmartDashboard.putNumber("Doc Oc Arm Yaw Speed", leftDocOcArm.getYawSpeed());
		
		SmartDashboard.putBoolean("At Bottom", toteElevator.isAtBottomLimit());
		
		SmartDashboard.putNumber("Elevator Motor 1 Current", pdp.getCurrent(2));
		SmartDashboard.putNumber("Elevator Motor 2 Current", pdp.getCurrent(3));
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}


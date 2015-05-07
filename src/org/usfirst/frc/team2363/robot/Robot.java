
package org.usfirst.frc.team2363.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2363.robot.commands.autonomous.GrabSingleCan;
import org.usfirst.frc.team2363.robot.commands.autonomous.OneCanOneTote;
import org.usfirst.frc.team2363.robot.commands.autonomous.ThreeTotes2CansNoVision;
import org.usfirst.frc.team2363.robot.commands.autonomous.ThreeTotes3CansNoVision;
import org.usfirst.frc.team2363.robot.commands.autonomous.TotesAndCansCommand;
import org.usfirst.frc.team2363.robot.commands.autonomous.TotesAndCansCommandKnockOver;
import org.usfirst.frc.team2363.robot.commands.autonomous.TwoCanTwoTotesNoEject;
import org.usfirst.frc.team2363.robot.commands.autonomous.TwoCanTwoTotesEject;
import org.usfirst.frc.team2363.robot.commands.autonomous.TwoTotesAndABird;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.subsystems.BearHugger;
import org.usfirst.frc.team2363.robot.subsystems.BearHugger.TiltPosition;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm;
import org.usfirst.frc.team2363.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2363.robot.subsystems.Drivetrain.ShifterState;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper;
import org.usfirst.frc.team2363.robot.util.ClawPosition;
import org.usfirst.frc.team2363.robot.util.NoneCommand;
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
	private static SendableChooser autoSelector;
			
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
		autoSelector = new SendableChooser();
		
		
		
		compressor = new Compressor();
		oi = new OI();
		visionProcessor = new VisionProcessor();
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		autoSelector.addDefault("None", new NoneCommand());
//		autoSelector.addObject("Three Tote Stack", new TotesAndCansCommandKnockOver());
//		autoSelector.addObject("Three Tote, No Knock Over", new TotesAndCansCommand());
//		autoSelector.addObject("Two cans + totes, HP Station", new TwoCanTwoTotesEject());
//		autoSelector.addObject("Two cans + totes, Autozone", new TwoCanTwoTotesNoEject());
//		autoSelector.addObject("Grab Can and Pull", new GrabCanAndPull());
//		autoSelector.addObject("Three Totes, Three Cans NO VISION", new ThreeTotes3CansNoVision());
		autoSelector.addObject("Grab Single Can", new GrabSingleCan());
		autoSelector.addObject("Three Totes, Two Cans NO VISION", new ThreeTotes2CansNoVision());
		autoSelector.addObject("Two Totes and a bird NO VISION", new TwoTotesAndABird());
//		visionProcessor.start();
		SmartDashboard.putData("Auto Mode", autoSelector);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData("Auto Mode", autoSelector);
		SmartDashboard.putNumber("Heading", drivetrain.getHeading());
		
		SmartDashboard.putNumber("Tote Encoder Position", toteElevator.getPosition());
//		SmartDashboard.putNumber("Tote Encoder Speed", toteElevator.getElevatorSpeed());
		
		SmartDashboard.putNumber("Bear Hugger Encoder Position", bearHuggerElevator.getPosition());
		
		SmartDashboard.putNumber("Doc Oc Arm Elevation Position", leftDocOcArm.getElevationPosition());
		SmartDashboard.putNumber("Doc Oc Arm Yaw Position", leftDocOcArm.getYawPosition());
		SmartDashboard.putBoolean("Gyro Calibrated", drivetrain.isGyroCalibrated());
		
		SmartDashboard.putBoolean("Has Can", Robot.leftDocOcArm.hasCan());
//		SmartDashboard.putNumber("Drive train tilt", drivetrain.getTilt());
//		SmartDashboard.putNumber("DOA Elevation Speed", leftDocOcArm.getElevationSpeed());
//		SmartDashboard.putNumber("Doc Oc Arm Yaw Speed", leftDocOcArm.getYawSpeed());
		
		SmartDashboard.putBoolean("At Bottom", toteElevator.isAtBottomLimit());
		
		
//		SmartDashboard.putData(oi.getAutoCommand());
		
//		SmartDashboard.putBoolean("Is Tote In", rollerGripper.isToteIn());
		
//		SmartDashboard.putNumber("Center of Tote", visionProcessor.getCenter());
	}

	public void autonomousInit() {
		bearHugger.tilt(TiltPosition.UNTILT);
//		drivetrain.shift(ShifterState.HIGH);
		toteElevator.resetAtBottom();
//		ActuateDocOcGripper(ClawPosition.OPEN)
//		autonomousCommand = oi.getAutoCommand();
//		autonomousCommand = new ActuateDocOcGripper(ClawPosition.OPEN); 
		autonomousCommand = new GrabSingleCan(); //(Command)autoSelector.getSelected();
//		autonomousCommand = (Command)autoSelector.getSelected();
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Center of Tote", visionProcessor.getCenter());
		SmartDashboard.putNumber("Heading", drivetrain.getHeading());
		SmartDashboard.putNumber("Left DT Position", drivetrain.getLeftPosition());
		SmartDashboard.putNumber("Right DT Position", drivetrain.getRightPosition());
	}

	public void teleopInit() {
		if (autonomousCommand != null) autonomousCommand.cancel();
		Robot.rollerGripper.setGripper(ClawPosition.OPEN);
//		new HomeToteElevator().start();
//		new HomeBearHugger().start();
		drivetrain.shift(ShifterState.LOW);
	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		drivetrain.shift(ShifterState.HIGH);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Has Can", Robot.leftDocOcArm.hasCan());
//		SmartDashboard.putData(bearHuggerElevator);
		SmartDashboard.putNumber("Tote Encoder Position", toteElevator.getPosition());
//		SmartDashboard.putNumber("Tote Encoder Speed", toteElevator.getElevatorSpeed());
		SmartDashboard.putNumber("Heading", drivetrain.getHeading());
		
		SmartDashboard.putNumber("Bear Hugger Encoder Position", bearHuggerElevator.getPosition());
		
		SmartDashboard.putNumber("Doc Oc Arm Elevation Position", leftDocOcArm.getElevationPosition());
		SmartDashboard.putNumber("Doc Oc Arm Yaw Position", leftDocOcArm.getYawPosition());
		SmartDashboard.putNumber("Drive train tilt", drivetrain.getTilt());
//		SmartDashboard.putNumber("DOA Elevation Speed", leftDocOcArm.getElevationSpeed());
//		SmartDashboard.putNumber("Doc Oc Arm Yaw Speed", leftDocOcArm.getYawSpeed());
		
		SmartDashboard.putBoolean("At Bottom", toteElevator.isAtBottomLimit());
		
//		SmartDashboard.putNumber("Elevator Motor 1 Current", pdp.getCurrent(2));
//		SmartDashboard.putNumber("Elevator Motor 2 Current", pdp.getCurrent(3));
//		SmartDashboard.putBoolean("Is Tote In", rollerGripper.isToteIn());
		
//		SmartDashboard.putNumber("POV", oi.ps4Controller.getPOV());
		
		SmartDashboard.putNumber("Tote Current 1", toteElevator.getElevatorCurrent1());
		SmartDashboard.putNumber("Tote Current 2", toteElevator.getElevatorCurrent2());
		
		SmartDashboard.putNumber("Drivetrain Position", drivetrain.getLeftPosition());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}


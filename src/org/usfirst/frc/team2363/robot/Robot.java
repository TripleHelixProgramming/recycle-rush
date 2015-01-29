
package org.usfirst.frc.team2363.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

import org.usfirst.frc.team2363.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2363.robot.subsystems.Elevator;
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
	public static Elevator elevator;
	public static RollerGripper rollerGripper;
	public static Compressor compressor;
	public static AxisCamera camera;
	public static VisionProcessor visionProcessor;
    private Command autonomousCommand;
    
    public Robot() {
    	pdp = new PowerDistributionPanel();
    	drivetrain = new Drivetrain();
    	elevator = new Elevator();
    	rollerGripper = new RollerGripper();
    	compressor = new Compressor();
    	oi = new OI();
    	visionProcessor = new VisionProcessor();
//    	camera = new AxisCamera("axis-camera");
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	visionProcessor.start();
    }
	
	public void disabledPeriodic() {
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
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
//        new PDPMonitoringCommand().start();
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
        SmartDashboard.putNumber("Encoder Position", elevator.getPosition());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}


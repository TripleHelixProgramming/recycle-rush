package org.usfirst.frc.team2363.robot;

import static org.usfirst.frc.team2363.robot.RobotMap.*;
import static org.usfirst.frc.team2363.robot.subsystems.Drivetrain.ShifterState.*;

import org.usfirst.frc.team2363.robot.commands.ElevateToteCommand;
import org.usfirst.frc.team2363.robot.commands.SequentialCommandGroup;
import org.usfirst.frc.team2363.robot.commands.autonomous.TotesAndCansCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveAtSpeed;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveAtSpeedToTote;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveToToteCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team2363.robot.commands.drivetrain.ShiftCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnTowardsTote;
import org.usfirst.frc.team2363.robot.commands.elevator.AutomatedHPThing;
import org.usfirst.frc.team2363.robot.commands.elevator.DefaultGround;
import org.usfirst.frc.team2363.robot.commands.elevator.ElevateAtSpeed;
import org.usfirst.frc.team2363.robot.commands.elevator.ElevateBearHuggerCommand;
import org.usfirst.frc.team2363.robot.commands.elevator.HomeToteElevator;
import org.usfirst.frc.team2363.robot.commands.elevator.SimpleToteElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.elevator.ToteElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.elevator.GoToGround;
import org.usfirst.frc.team2363.robot.commands.elevator.GoToPosition;
import org.usfirst.frc.team2363.robot.commands.elevator.SimpleBearHuggerElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.elevator.UpAndOpen;
import org.usfirst.frc.team2363.robot.commands.grippers.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.BearHuggerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.CanTiltCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.DocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.ElevateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.ElevateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHugger;
import org.usfirst.frc.team2363.robot.commands.grippers.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.RotateArmToOnboard;
import org.usfirst.frc.team2363.robot.commands.grippers.RotateDocOcArmToCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.SimpleDocOcArmElevationCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.SimpleDocOcArmYawCommand;
import org.usfirst.frc.team2363.robot.subsystems.BearHugger.TiltPosition;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.subsystems.DocOcArm.DocOcArmPosition;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.AutonomousSelector;
import org.usfirst.frc.team2363.robot.util.ClawPosition;
import org.usfirst.frc.team2363.robot.util.RollingAverager;
import org.usfirst.frc.team2363.robot.util.SelectableCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private RollingAverager throttleAverager = new RollingAverager(12, 0);
	//Controllers
	private Joystick ps4Controller;
	private Joystick operatorControl;
	private Joystick manualOperatorControl;
	private AutonomousSelector autoSelector;

	public OI() {
		//Controllers
		ps4Controller = new Joystick(PS4_PORT);
		operatorControl = new Joystick(OPERATOR_PORT);
		manualOperatorControl = new Joystick(MANUAL_OPERATOR_PORT);

		//Operator Buttons
		JoystickButton groundButton = new JoystickButton(operatorControl, GROUND_BUTTON);
		JoystickButton carryPlaceButton = new JoystickButton(operatorControl, CARRY_PLACE_BUTTON);
		JoystickButton stepButton = new JoystickButton(operatorControl, STEP_BUTTON);
		JoystickButton oneToteButton = new JoystickButton(operatorControl, ONE_TOTE_BUTTON);
		JoystickButton twoToteButton = new JoystickButton(operatorControl, TWO_TOTE_BUTTON);
		JoystickButton humanPlayerButton = new JoystickButton(operatorControl, HUMAN_PLAYER_BUTTON);

		//OP Button Actions
		groundButton.whenPressed(new DefaultGround());

		carryPlaceButton.whenPressed(new ElevateToteCommand(ElevatorPosition.CARRY));
		carryPlaceButton.whenReleased(new DefaultGround());

		stepButton.whenPressed(new ElevateToteCommand(ElevatorPosition.STEP_CARRY));
		stepButton.whenReleased(new ElevateToteCommand(ElevatorPosition.STEP_PLACE));

		oneToteButton.whenPressed(new ElevateToteCommand(ElevatorPosition.ONE_TOTE_CARRY));
		oneToteButton.whenPressed(new ElevateToteCommand(ElevatorPosition.ONE_TOTE_PLACE));

		twoToteButton.whenPressed(new ElevateToteCommand(ElevatorPosition.TWO_TOTE_CARRY));
		twoToteButton.whenReleased(new ElevateToteCommand(ElevatorPosition.TWO_TOTE_PLACE));

		humanPlayerButton.whenPressed(new UpAndOpen());
		humanPlayerButton.whenReleased(new AutomatedHPThing());

		//Joystick Buttons
		JoystickButton throttleScalingButton = new JoystickButton(ps4Controller, THROTTLE_SCALING_BUTTON);
		JoystickButton shiftDownButton = new JoystickButton(ps4Controller, SHIFT_DOWN_BUTTON);
		JoystickButton shiftUpButton = new JoystickButton(ps4Controller, SHIFT_UP_BUTTON);
		JoystickButton scoreButton = new JoystickButton(ps4Controller, SCORE_BUTTON);
		JoystickButton carryButton = new JoystickButton(ps4Controller, CARRY_BUTTON);
		JoystickButton intakeButton = new JoystickButton(ps4Controller, INTAKE_BUTTON);
		JoystickButton ejectButton = new JoystickButton(ps4Controller, EJECT_BUTTON);

		//JS Button Actions
		shiftUpButton.whenPressed(new ShiftCommand(HIGH));
		shiftDownButton.whenPressed(new ShiftCommand(LOW));
		scoreButton.whenPressed(new ElevateToteCommand(ElevatorPosition.GROUND));
		carryButton.whenPressed(new ElevateToteCommand(ElevatorPosition.CARRY));
		intakeButton.whileHeld(new RollerGripperCommand(RobotMap.ROLLER_POWER, ClawPosition.CLOSE));
		ejectButton.whileHeld(new RollerGripperCommand(-RobotMap.ROLLER_POWER, ClawPosition.CLOSE));
		intakeButton.whenReleased(new RollerGripperCommand(0, ClawPosition.OPEN));
		ejectButton.whenReleased(new RollerGripperCommand(0, ClawPosition.OPEN));
		throttleScalingButton.whenActive(new JoystickDrive());


		//Autonomous 
		JoystickButton autoSelector1 = new JoystickButton(operatorControl, 1);
		JoystickButton autoSelector2 = new JoystickButton(operatorControl, 2);
		JoystickButton autoSelector3 = new JoystickButton(operatorControl, 3);

		autoSelector = new AutonomousSelector(autoSelector1, autoSelector2, autoSelector3);

		//Elevator commands
		SmartDashboard.putData("DriveToToteAtSpeed", new DriveAtSpeedToTote(48, 0.4, 60));
		SmartDashboard.putData(new TotesAndCansCommand());
		SmartDashboard.putData("Doc Oc Arm Up", new SimpleDocOcArmElevationCommand(0.3));
		SmartDashboard.putData("Doc Oc Arm Down", new SimpleDocOcArmElevationCommand(-0.1));
		SmartDashboard.putData("Doc Oc Arm Left", new SimpleDocOcArmYawCommand(0.1));
		SmartDashboard.putData("Doc Oc Arm Right", new SimpleDocOcArmYawCommand(-0.1));
		SmartDashboard.putData("Bear Hugger Elevator Up", new SimpleBearHuggerElevatorCommand(0.75));
		SmartDashboard.putData("Bear Hugger Elevator Down", new SimpleBearHuggerElevatorCommand(-0.75));
		SmartDashboard.putData("Doc Oc Arm Open", new ActuateDocOcGripper(ClawPosition.OPEN));
		SmartDashboard.putData("Doc Oc Arm Close", new ActuateDocOcGripper(ClawPosition.CLOSE));
		SmartDashboard.putData("Elevate to can", new ElevateBearHuggerCommand(BearHuggerElevator.CAN_HAND_OFF, 0.75));
		SmartDashboard.putData(new HomeToteElevator());
		SmartDashboard.putData("Open Bear Hugger", new BearHuggerGripperCommand(ClawPosition.OPEN));
		SmartDashboard.putData("Close Bear Hugger", new BearHuggerGripperCommand(ClawPosition.CLOSE));
		SmartDashboard.putData("Arm To Floor", new ElevateDocOcArmToCommand(Robot.leftDocOcArm, DocOcArmPosition.LEFT_FLOOR, 12));
//		SmartDashboard.putData("Arm To Step", new ElevateDocOcArmToCommand(Robot.leftDocOcArm, DocOcArmPosition.LEFT_STEP, 12));
		SmartDashboard.putData("Arm To Stow", new ElevateDocOcArmToCommand(Robot.leftDocOcArm, DocOcArmPosition.LEFT_STOWED, 12));
		SmartDashboard.putData("Rotate Arm To Stow", new DocOcArmYawCommand(0.1, DocOcArmPosition.LEFT_STOWED));
		SmartDashboard.putData("Rotate Arm To Floor", new DocOcArmYawCommand(0.1, DocOcArmPosition.LEFT_FLOOR));
		SmartDashboard.putData(new FromFloorToBearHugger());
		SmartDashboard.putData("Tilt Can", new CanTiltCommand(TiltPosition.TILT));
		SmartDashboard.putData("Un-Tilt Can", new CanTiltCommand(TiltPosition.UNTILT));
	}
	
	public SelectableCommand getAutoCommand() {
		return autoSelector.getSelectedCommand();

	}
	
	public double getThrottle() {
		//Inverted Throttle
		if (ps4Controller.getRawAxis(LEFT_TRIGGER) > 0) {
			throttleAverager.addValue(-ps4Controller.getRawAxis(LEFT_STICK_Y));	
		} else {
			//Regular Throttle
			throttleAverager.addValue(ps4Controller.getRawAxis(LEFT_STICK_Y));
		}
		return throttleAverager.getAverage();
	}

	public double getTurn() {
		return ps4Controller.getRawAxis(RIGHT_STICK_X) * getTurnScaling(getThrottle());
	}

	public static double getTurnScaling(double x) {
		return -Math.abs(LOW_SPEED_SCALING - HIGH_SPEED_SCALING) * x + LOW_SPEED_SCALING;
	}
	
	public double getElevatorPower() {
		return manualOperatorControl.getY();
	}
	
	public boolean getOpRightTrigger() {
		return manualOperatorControl.getRawButton(INTAKE_BUTTON);
	}
	
	public double getOpRightX() {
		return manualOperatorControl.getRawAxis(RobotMap.RIGHT_STICK_X);
	}
	
	public double getOpRightY() {
		return manualOperatorControl.getRawAxis(RobotMap.RIGHT_STICK_Y);
	}
}

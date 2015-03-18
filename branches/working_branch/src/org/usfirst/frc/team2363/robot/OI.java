package org.usfirst.frc.team2363.robot;

import static org.usfirst.frc.team2363.robot.RobotMap.*;
import static org.usfirst.frc.team2363.robot.subsystems.Drivetrain.ShifterState.*;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team2363.robot.commands.ElevateToteCommand;
import org.usfirst.frc.team2363.robot.commands.LandfillGrabbing;
import org.usfirst.frc.team2363.robot.commands.autonomous.TotesAndCansCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.DriveAtSpeed;
import org.usfirst.frc.team2363.robot.commands.drivetrain.ShiftCommand;
import org.usfirst.frc.team2363.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team2363.robot.commands.elevators.AutomatedHPThing;
import org.usfirst.frc.team2363.robot.commands.elevators.PlaceToteStackCommandGroup;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.HomeBearHugger;
import org.usfirst.frc.team2363.robot.commands.elevators.bearhugger.SimpleBearHuggerElevatorCommand;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.DefaultGround;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.HomeToteElevator;
import org.usfirst.frc.team2363.robot.commands.elevators.tote.UpAndOpen;
import org.usfirst.frc.team2363.robot.commands.grippers.FromFloorToBearHugger;
import org.usfirst.frc.team2363.robot.commands.grippers.StowToBearHugger;
import org.usfirst.frc.team2363.robot.commands.grippers.bearhugger.BearHuggerGripperCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.bearhugger.CanTiltCommand;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ActuateDocOcGripper;
import org.usfirst.frc.team2363.robot.commands.grippers.dococ.ElevateDocOcToYaw;
import org.usfirst.frc.team2363.robot.commands.grippers.rollergripper.RollerGripperCommand;
import org.usfirst.frc.team2363.robot.subsystems.BearHugger.TiltPosition;
import org.usfirst.frc.team2363.robot.subsystems.BearHuggerElevator;
import org.usfirst.frc.team2363.robot.subsystems.RollerGripper.RollerGripperDirection;
import org.usfirst.frc.team2363.robot.subsystems.ToteElevator.ElevatorPosition;
import org.usfirst.frc.team2363.robot.util.AutonomousSelector;
import org.usfirst.frc.team2363.robot.util.ClawPosition;
import org.usfirst.frc.team2363.robot.util.RollingAverager;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private RollingAverager throttleAverager = new RollingAverager(15, 0);
	//Controllers
	public Joystick ps4Controller;
	private Joystick operatorControl;
	private Joystick manualOperatorControl;
	private AutonomousSelector autoSelector;

	public OI() {
		//Controllers
		ps4Controller = new Joystick(PS4_PORT);
		operatorControl = new Joystick(OPERATOR_PORT);
		manualOperatorControl = new Joystick(MANUAL_OPERATOR_PORT);
		
		//Manual Buttons
		JoystickButton openDocOcButton = new JoystickButton(manualOperatorControl, DOC_OC_OPEN);
		JoystickButton closeDocOcButton = new JoystickButton(manualOperatorControl, DOC_OC_CLOSE);
		JoystickButton openBearHugger = new JoystickButton(manualOperatorControl, CLOSE_BEAR_HUGGER);
		JoystickButton closeBearHugger = new JoystickButton(manualOperatorControl, OPEN_BEAR_HUGGER);
		JoystickButton bearHuggerUp = new JoystickButton(manualOperatorControl, BEAR_HUGGER_UP);
		JoystickButton bearHuggerDown = new JoystickButton(manualOperatorControl, BEAR_HUGGER_UP);
		
		//Manual Actions
		openDocOcButton.whenPressed(new ActuateDocOcGripper(ClawPosition.OPEN));
		closeDocOcButton.whenPressed(new ActuateDocOcGripper(ClawPosition.CLOSE));
		openBearHugger.whenPressed(new BearHuggerGripperCommand(ClawPosition.OPEN));
		closeBearHugger.whenPressed(new BearHuggerGripperCommand(ClawPosition.CLOSE));
		
		bearHuggerUp.whileHeld(new SimpleBearHuggerElevatorCommand(0.5));
		bearHuggerUp.whenReleased(new SimpleBearHuggerElevatorCommand(0));
		bearHuggerDown.whileHeld(new SimpleBearHuggerElevatorCommand(-0.5));
		bearHuggerDown.whenReleased(new SimpleBearHuggerElevatorCommand(0));
		
		//Operator Buttons
		JoystickButton groundButton = new JoystickButton(operatorControl, GROUND_BUTTON);
		JoystickButton carryPlaceButton = new JoystickButton(operatorControl, CARRY_PLACE_BUTTON);
		JoystickButton stepButton = new JoystickButton(operatorControl, STEP_BUTTON);
		JoystickButton oneToteButton = new JoystickButton(operatorControl, ONE_TOTE_BUTTON);
		JoystickButton twoToteButton = new JoystickButton(operatorControl, TWO_TOTE_BUTTON);
		JoystickButton humanPlayerButton = new JoystickButton(operatorControl, HUMAN_PLAYER_BUTTON);
		JoystickButton landfillButton = new JoystickButton(operatorControl, LANDFILL_BUTTON);
		//New Stuff
		JoystickButton tiltButton = new JoystickButton(operatorControl, TILT_BUTTON);
		JoystickButton pickUpButton = new JoystickButton(operatorControl, PICK_UP_BUTTON);
		JoystickButton bearHuggerButton = new JoystickButton(operatorControl, BEAR_OPEN_CLOSE);
		JoystickButton docOcButton = new JoystickButton(operatorControl, DOC_OC_SEQUENCE_BUTTON);
		
		
		//OP Button Actions
		tiltButton.whileHeld(new ActuateDocOcGripper(ClawPosition.OPEN));
		tiltButton.whenReleased(new ActuateDocOcGripper(ClawPosition.CLOSE));
		
		bearHuggerButton.whileHeld(new BearHuggerGripperCommand(ClawPosition.OPEN));
		bearHuggerButton.whenReleased(new BearHuggerGripperCommand(ClawPosition.CLOSE));
		
		pickUpButton.whileHeld(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
		
		docOcButton.whenPressed(new StowToBearHugger());
		//End New Stuff
		
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
		
		landfillButton.whenPressed(new LandfillGrabbing());
		
		//Joystick Buttons
//		JoystickButton shiftDownButton = new JoystickButton(ps4Controller, SHIFT_DOWN_BUTTON);
		JoystickButton shiftUpButton = new JoystickButton(ps4Controller, SHIFT_UP_BUTTON);
		JoystickButton scoreButton = new JoystickButton(ps4Controller, SCORE_BUTTON);
		JoystickButton carryButton = new JoystickButton(ps4Controller, CARRY_BUTTON);
		JoystickButton intakeButton = new JoystickButton(ps4Controller, INTAKE_BUTTON);
		JoystickButton ejectButton = new JoystickButton(ps4Controller, EJECT_BUTTON);
		JoystickButton groundWithoutOpenButton = new JoystickButton(ps4Controller, GROUND_WITHOUT_OPEN);
		JoystickButton resetBearHugger = new JoystickButton(ps4Controller, BEAR_RESET_BUTTON);
		JoystickButton justIntake = new JoystickButton(ps4Controller, JUST_INTAKE_BUTTON);
		
		//JS Button Actions
		shiftUpButton.whileHeld(new ShiftCommand(HIGH));
		shiftUpButton.whenReleased(new ShiftCommand(LOW));
//		shiftDownButton.whenPressed(new ShiftCommand(LOW));
		scoreButton.whenPressed(new PlaceToteStackCommandGroup());
		carryButton.whenPressed(new ElevateToteCommand(ElevatorPosition.CARRY));
		ejectButton.whileHeld(new RollerGripperCommand(RollerGripperDirection.OUT, ClawPosition.CLOSE));
		intakeButton.whileHeld(new LandfillGrabbing());
		ejectButton.whenReleased(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN));
		intakeButton.whenReleased(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN));
		groundWithoutOpenButton.whenPressed(new ElevateToteCommand(ElevatorPosition.GROUND));
		resetBearHugger.whenPressed(new HomeBearHugger());
		justIntake.whileHeld(new RollerGripperCommand(RollerGripperDirection.IN, ClawPosition.CLOSE));
		justIntake.whenReleased(new RollerGripperCommand(RollerGripperDirection.OFF, ClawPosition.OPEN));
		

//		throttleScalingButton.whenActive(new JoystickDrive());


		//Autonomous 
		JoystickButton autoSelector1 = new JoystickButton(operatorControl, 1);
		JoystickButton autoSelector2 = new JoystickButton(operatorControl, 2);
		JoystickButton autoSelector3 = new JoystickButton(operatorControl, 3);

		autoSelector = new AutonomousSelector(autoSelector1, autoSelector2, autoSelector3);
		
		List<Command> autoCommands = new ArrayList<>();
		autoCommands.add(new TotesAndCansCommand());
		autoCommands.add(new DriveAtSpeed());
		
		autoSelector.setCommands(autoCommands);
		
		//Elevator commands
//		SmartDashboard.putData("DriveToToteAtSpeed", new DriveAtSpeedToTote(48, 0.4, 60));
//		SmartDashboard.putData(new TotesAndCansCommand());
//		SmartDashboard.putData("Doc Oc Arm Up", new SimpleDocOcArmElevationCommand(0.3));
//		SmartDashboard.putData("Doc Oc Arm Down", new SimpleDocOcArmElevationCommand(-0.1));
//		SmartDashboard.putData("Doc Oc Arm Left", new SimpleDocOcArmYawCommand(0.1));
//		SmartDashboard.putData("Doc Oc Arm Right", new SimpleDocOcArmYawCommand(-0.1));
		SmartDashboard.putData("Bear Hugger Elevator Up", new SimpleBearHuggerElevatorCommand(BearHuggerElevator.BEAR_HUGGER_ELEVATOR_POWER));
		SmartDashboard.putData("Bear Hugger Elevator Down", new SimpleBearHuggerElevatorCommand(-0.75));
		SmartDashboard.putData("Doc Oc Arm Open", new ActuateDocOcGripper(ClawPosition.OPEN));
		SmartDashboard.putData("Doc Oc Arm Close", new ActuateDocOcGripper(ClawPosition.CLOSE));
//		SmartDashboard.putData("Elevate to can", new ElevateBearHuggerCommand(BearHuggerElevator.CAN_HAND_OFF, 0.75));
		SmartDashboard.putData(new HomeToteElevator());
		SmartDashboard.putData("Open Bear Hugger", new BearHuggerGripperCommand(ClawPosition.OPEN));
		SmartDashboard.putData("Close Bear Hugger", new BearHuggerGripperCommand(ClawPosition.CLOSE));
//		SmartDashboard.putData("Arm To Floor", new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_FLOOR, 0.5));
//		SmartDashboard.putData("Arm To Step", new ElevateDocOcArmToCommand(Robot.leftDocOcArm, DocOcArmPosition.LEFT_STEP, 12));
//		SmartDashboard.putData("Arm To Stow", new ElevateDocOcArmToCommand(DocOcArmPosition.LEFT_STOWED, 0.5));
//		SmartDashboard.putData("Rotate Arm To Stow", new DocOcArmYawCommand(0.1, DocOcArmPosition.LEFT_SECOND_CAN));
//		SmartDashboard.putData("Rotate Arm To Floor", new DocOcArmYawCommand(0.1, DocOcArmPosition.LEFT_FLOOR));
		SmartDashboard.putData(new FromFloorToBearHugger());
		SmartDashboard.putData("Tilt Can", new CanTiltCommand(TiltPosition.TILT));
		SmartDashboard.putData("Un-Tilt Can", new CanTiltCommand(TiltPosition.UNTILT));
		SmartDashboard.putData(new ElevateDocOcToYaw(1));
		SmartDashboard.putData(new TurnToAngle(90, 0.75));
		SmartDashboard.putData("Stow To Bear", new StowToBearHugger());
	}
	
	public Command getAutoCommand() {
		return autoSelector.getSelectedCommand();

	}
	
	public double getThrottle() {
		//Inverted Throttle
//		if (ps4Controller.getRawAxis(LEFT_TRIGGER) > 0) {
//			throttleAverager.addValue(-ps4Controller.getRawAxis(LEFT_STICK_Y));	
//		} else {
			//Regular Throttle
			throttleAverager.addValue(ps4Controller.getRawAxis(LEFT_STICK_Y));
//		}
		return throttleAverager.getAverage();
	}

	public double getTurn() {
		return ps4Controller.getRawAxis(RIGHT_STICK_X) * getTurnScaling(getThrottle());
	}

	public static double getTurnScaling(double x) {
		return -Math.abs(LOW_SPEED_SCALING - HIGH_SPEED_SCALING) * x + LOW_SPEED_SCALING;
	}
	
	public double getElevatorPower() {
		return manualOperatorControl.getRawAxis(LEFT_STICK_Y);
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


package org.usfirst.frc.team2363.robot.subsystems;

import static org.usfirst.frc.team2363.robot.RobotMap.*;

import org.usfirst.frc.team2363.robot.commands.drivetrain.JoystickDrive;

import com.kauailabs.nav6.frc.IMU;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drivetrain extends Subsystem {
	
	public enum ShifterState {
		HIGH,
		LOW;
	}
	//Encoders
	private Encoder leftEncoder = new Encoder(DRIVETRAIN_LEFT_ENCODER_CHANNEL_A, DRIVETRAIN_LEFT_ENCODER_CHANNEL_B, true, EncodingType.k4X);
	private Encoder rightEncoder = new Encoder(DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A, DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B, true, EncodingType.k4X);

	//IMU
	IMU imu;
	
	//Motor Controllers
    private SpeedController frontLeft = new Talon(FRONT_LEFT_TALON_CHANNEL);
    private SpeedController frontRight = new Talon(FRONT_RIGHT_TALON_CHANNEL);
    private SpeedController rearLeft = new Talon(REAR_LEFT_TALON_CHANNEL);
    private SpeedController rearRight = new Talon(REAR_RIGHT_TALON_CHANNEL);
    
    //Solenoids
    private Solenoid shiftSolenoid1 = new Solenoid(SHIFT_SOLENOID_1_CHANNEL);
    private Solenoid shiftSolenoid2 = new Solenoid(SHIFT_SOLENOID_2_CHANNEL);
    
    private RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    
    public Drivetrain() {
    	leftEncoder.setDistancePerPulse(0.01636);
    	leftEncoder.setSamplesToAverage(12);
    	
    	rightEncoder.setDistancePerPulse(0.01636);
    	rightEncoder.setSamplesToAverage(12);
    	
    	SerialPort serialPort;

    	try {
    		serialPort = new SerialPort(57600, SerialPort.Port.kMXP);

    		// You can add a second parameter to modify the 
    		// update rate (in hz) from 4 to 100.  The default is 100.
    		// If you need to minimize CPU load, you can set it to a
    		// lower value, as shown here, depending upon your needs.

    		// You can also use the IMUAdvanced class for advanced
    		// features.

    		byte updateRateHz = 50;
    		imu = new IMU(serialPort, updateRateHz);
    		SmartDashboard.putData(imu);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }
    
    public void arcadeDrive(double throttle, double turn) {
    	robotDrive.arcadeDrive(throttle, turn);
    }
    
    public double getLeftPosition() {
    	return leftEncoder.getDistance();
    }
    
    public double getRightPosition() {
    	return rightEncoder.getDistance();
    }
    
    public double getLeftSpeed() {
    	return leftEncoder.getRate();
    }
    
    public double getRightSpeed() {
    	return rightEncoder.getRate();
    }
    
    public void resetEncoders() {
    	rightEncoder.reset();
    	leftEncoder.reset();
    }
    public void shift(ShifterState newState) {
    	if (newState == ShifterState.HIGH) {
    		shiftSolenoid1.set(false);
    		shiftSolenoid2.set(true);
    	} else {
    		shiftSolenoid1.set(true);
    		shiftSolenoid2.set(false);
    	}
    }
    
    public double getHeading() {
    	if (!imu.isCalibrating()) {
    		return imu.getCompassHeading();
    	}
    	return 0;
    }
}

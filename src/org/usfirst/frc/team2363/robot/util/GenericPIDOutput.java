package org.usfirst.frc.team2363.robot.util;

import edu.wpi.first.wpilibj.PIDOutput;

public class GenericPIDOutput implements PIDOutput {
	private double output;
	@Override
	public void pidWrite(double output) {
		this.output = output;
	}
	public double getPower() {
		return output;
	}
}

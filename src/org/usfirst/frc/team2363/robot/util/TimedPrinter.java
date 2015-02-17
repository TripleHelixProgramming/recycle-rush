package org.usfirst.frc.team2363.robot.util;

import java.util.Date;

public class TimedPrinter {

	private int waitTime;
	private Date lastTime = new Date(0);

	public TimedPrinter(int waitTime) {
		this.waitTime = waitTime;
	}

	public void print(String value) {
		Date now = new Date();
		long timePassed = now.getTime() - lastTime.getTime();
		if (timePassed > waitTime) {
			System.out.println(value);
			lastTime = now;
		}
	}

	public void print(double value) {
		print("" + value);
	}
}

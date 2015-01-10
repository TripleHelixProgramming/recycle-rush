package org.usfirst.frc.team2363.robot.util;

import java.util.ArrayList;
import java.util.List;

public class RollingAverager {
	private List<Double> list = new ArrayList<>();
	private int count;

	public RollingAverager(int count) {
		this.count = count;
	}
	
	public void addValue(double value) {
		list.add(value);
		if (list.size() > count) {
			list.remove(0);
		}
	}
	
	public double getAverage() {
		double sum = 0;
		for (double d : list) {
			sum += d;
		}
		return sum / list.size();
	}
}

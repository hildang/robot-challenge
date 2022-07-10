package com.ioofholidings.robotchallenge.command;

import java.util.List;

import com.ioofholidings.robotchallenge.model.Robot;
import com.ioofholidings.robotchallenge.model.Table;

public class ReportCommand extends Command {

	@Override
	public Object execute(Table table) {
		String result = "";
		List<Robot> robotList = table.getRobotList();
		Robot activeRobot = table.getActiveRobot();
		result += activeRobot.getCoordinate().getX() + "," + activeRobot.getCoordinate().getY() + "," + activeRobot.getFacing();
		if (robotList.size() > 1) {
			result += System.lineSeparator() + "Total robots: " + robotList.size();
			result += System.lineSeparator() + "Active robot: " + activeRobot.getId();
		}
		return result;
	}

}

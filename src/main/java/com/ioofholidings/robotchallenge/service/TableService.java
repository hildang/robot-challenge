package com.ioofholidings.robotchallenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ioofholidings.robotchallenge.exception.RobotChallengeError;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Coordinate;
import com.ioofholidings.robotchallenge.model.Robot;
import com.ioofholidings.robotchallenge.model.Table;

@Service
public class TableService {
	
	public void addRobot(Table table, Robot robot) {
		if (robot == null) {
			return;
		}
		if (!validateCoordinate(table, robot.getCoordinate())) {
			// robot will drop from the table, ignore
			return;
		}
		List<Robot> robotList = table.getRobotList();
		robot.setId(robotList.size() + 1);
		table.addRobot(robot);
		table.setActiveRobot(robot);
	}
	
	public void updateRobot(Table table, Robot robot, Coordinate newCoordinate) {
		if (!validateCoordinate(table, newCoordinate)) {
			// robot will drop from the table, ignore
			return;
		}
		robot.setCoordinate(newCoordinate);
	}
	
	public void setActiveRobot(Table table, int id) {
		List<Robot> robotList = table.getRobotList();
		if (id > robotList.size()) {
			throw new RobotChallengeException(RobotChallengeError.ROBOT_NUMBER_INVALID);				
		}
		Robot activeRobot = robotList.get(id - 1);
		activeRobot.setActive(true);
		table.setActiveRobot(activeRobot);
	}
	
	private boolean validateCoordinate(Table table, Coordinate newCoordinate) {
		if (newCoordinate == null || newCoordinate.getX() < 0 || newCoordinate.getY() < 0 || 
			newCoordinate.getX() > table.getWidth() || newCoordinate.getY() > table.getLength()) {
			return false;
		}
		return true;
	}
}

package com.ioofholidings.robotchallenge.service;

import java.util.List;

import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Coordinate;
import com.ioofholidings.robotchallenge.model.Robot;
import com.ioofholidings.robotchallenge.model.Table;

public class TableService {

	private static TableService _instance;
	
	private TableService() {}
	
	public static TableService getInstance() {
		if (_instance == null) {
			_instance = new TableService();
		}
		return _instance;
	}
	
	public void addRobot(Table table, Robot robot) {
		if (robot == null) {
			return;
		}
		List<Robot> robotList = table.getRobotList();
		robot.setId(robotList.size() + 1);
		table.addRobot(robot);
		table.setActiveRobot(robot);
	}
	
	public void updateRobot(Table table, Robot robot, Coordinate newCoordinate) {
		if (newCoordinate.getX() < 0 || newCoordinate.getY() < 0 || 
			newCoordinate.getX() > table.getWidth() || newCoordinate.getY() > table.getLength()) {
			// robot will drop from the table, ignore
			return;
		}
		robot.setCoordinate(newCoordinate);
	}
	
	public void setActiveRobot(Table table, int id) {
		List<Robot> robotList = table.getRobotList();
		if (id > robotList.size()) {
			throw new RobotChallengeException("Invalid Robot Id");
		}
		Robot activeRobot = robotList.get(id - 1);
		activeRobot.setActive(true);
		table.setActiveRobot(activeRobot);
	}
	
	public void print(Table table) {
		List<Robot> robotList = table.getRobotList();
		Robot activeRobot = table.getActiveRobot();
		System.out.println(activeRobot.getCoordinate().getX() + "," + activeRobot.getCoordinate().getY() + "," + activeRobot.getFacing());
		if (robotList.size() > 1) {
			System.out.println("Total robots: " + robotList.size());
			System.out.println("Active Robot: " + activeRobot.getId());
		}
	}
}

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

	private Table table; 
	
	public TableService(Table table) {
		this.table = table;
	}
	
	public Table getTable() {
		return table;
	}

	public void addRobot(Table table, Robot robot) {
		if (robot == null) {
			return;
		}
		if (table.isOccupied(robot.getCoordinate())) {
			return;
		}
		List<Robot> robotList = table.getRobotList();
		robot.setId(robotList.size() + 1);
		table.addRobot(robot);
		setActiveRobot(table, robot);
		table.setOccupied(robot.getCoordinate());
	}
	
	public void updateRobot(Table table, Robot robot, Coordinate newCoordinate) {
		if (table.isOccupied(newCoordinate)) {
			return;
		}
		table.setOccupied(robot.getCoordinate(), false);
		robot.setCoordinate(newCoordinate);
		table.setOccupied(newCoordinate);
	}
	
	public void setActiveRobot(Table table, int id) {
		List<Robot> robotList = table.getRobotList();
		if (id > robotList.size()) {
			throw new RobotChallengeException(RobotChallengeError.ROBOT_NUMBER_INVALID);				
		}
		Robot activeRobot = robotList.get(id - 1);
		this.setActiveRobot(table, activeRobot);
	}
	
	public void setActiveRobot(Table table, Robot robot) {
		if (table.getActiveRobot() != null) {
			table.getActiveRobot().setActive(false);
		}
		robot.setActive(true);
		table.setActiveRobot(robot);
	}
	
}

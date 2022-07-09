package com.ioofholidings.robotchallenge.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ioofholidings.robotchallenge.exception.RobotChallengeException;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class Table {

	private Integer width;
	private Integer length;
	
	private Robot activeRobot;
	private List<Robot> robotList = new ArrayList<>();
	
	public Table(
			@Value("${robot-challenge.table.width}") Integer width,
			@Value("${robot-challenge.table.length}") Integer length) {
		this.width = width;
		this.length = length;
	}

	public void addRobot(Robot robot) {
		if (robot == null) {
			return;
		}
		robot.setId(robotList.size() + 1);
		this.robotList.add(robot);
	}
	
	public void setActiveRobot(int id) {
		if (id > robotList.size()) {
			throw new RobotChallengeException("Invalid Robot Id");
		}
		this.activeRobot = robotList.get(id - 1);
		this.activeRobot.setActive(true);
	}
	
	public void print() {
		
	}
}

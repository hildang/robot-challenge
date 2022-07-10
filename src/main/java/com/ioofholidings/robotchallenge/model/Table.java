package com.ioofholidings.robotchallenge.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
		this.robotList.add(robot);
	}
	
}

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
	private boolean[][] isOccupied;
	
	private Robot activeRobot;
	private List<Robot> robotList = new ArrayList<>();
	
	public Table(
			@Value("${robot-challenge.table.width}") Integer width,
			@Value("${robot-challenge.table.length}") Integer length) {
		this.width = width;
		this.length = length;
		this.isOccupied = new boolean[width+1][length+1];
	}

	public void addRobot(Robot robot) {
		if (robot == null) {
			return;
		}
		this.robotList.add(robot);
	}
	
	public void setOccupied(Coordinate newCoordinate) {
		this.setOccupied(newCoordinate, true);
	}
	
	public void setOccupied(Coordinate newCoordinate, boolean isOccupied) {
		if (!validateCoordinate(newCoordinate)) {
			return;
		}
		this.isOccupied[newCoordinate.getX()][newCoordinate.getY()] = isOccupied;
	}
	
	public boolean isOccupied(Coordinate newCoordinate) {
		if (!validateCoordinate(newCoordinate)) {
			return true;
		}
		return this.isOccupied[newCoordinate.getX()][newCoordinate.getY()];
	}
	
	private boolean validateCoordinate(Coordinate newCoordinate) {
		if (newCoordinate == null || newCoordinate.getX() < 0 || newCoordinate.getY() < 0 || 
			newCoordinate.getX() > this.getWidth() || newCoordinate.getY() > this.getLength()) {
			return false;
		}
		return true;
	}
}

package com.ioofholidings.robotchallenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Robot {

	private Integer id; 
	private Coordinate coordinate; 
	private Facing facing; 
	private boolean isActive = false; 
	
	public Robot(Coordinate coordinate, Facing facing) {
		this.coordinate = coordinate;
		this.facing = facing;
	}
	
}

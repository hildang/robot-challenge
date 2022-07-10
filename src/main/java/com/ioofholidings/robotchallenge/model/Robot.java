package com.ioofholidings.robotchallenge.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Robot {

	@NonNull private Integer id; 
	@NonNull private Coordinate coordinate; 
	@NonNull private Facing facing; 
	private boolean isActive = false; 
	
}

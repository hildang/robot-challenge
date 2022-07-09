package com.ioofholidings.robotchallenge.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Robot {

	@NonNull private Integer id; 
	@NonNull private Coordinate coordinate; 
	private boolean isActive = false; 
	
}

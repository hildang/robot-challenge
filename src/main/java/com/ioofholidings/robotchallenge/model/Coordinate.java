package com.ioofholidings.robotchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinate implements Cloneable {

	int x; 
	int y; 
	
	public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}

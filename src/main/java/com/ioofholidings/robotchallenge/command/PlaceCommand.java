package com.ioofholidings.robotchallenge.command;

import com.ioofholidings.robotchallenge.exception.RobotChallengeError;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Coordinate;
import com.ioofholidings.robotchallenge.model.Facing;
import com.ioofholidings.robotchallenge.model.Robot;
import com.ioofholidings.robotchallenge.model.Table;

public class PlaceCommand extends Command {
	
	private int x; 
	private int y; 
	private Facing f; 
	
	public PlaceCommand(String[] args) {
		if (args.length < 2) {
			throw new RobotChallengeException(RobotChallengeError.PLACE_COMMAND_USAGE_ERROR);				
		}
		
		String[] parameters = args[1].split(",");
		if (parameters.length < 3) {
			throw new RobotChallengeException(RobotChallengeError.PLACE_COMMAND_USAGE_ERROR);				
		}
		try {
			this.x = Integer.parseInt(parameters[0]);
			this.y = Integer.parseInt(parameters[1]);
			this.f = Facing.valueOf(parameters[2]);
		} catch (Exception e) {
			throw new RobotChallengeException(e.getMessage(), e);
		}
	}
	
	@Override
	public Object execute(Table table) {
		Robot newRobot = new Robot(new Coordinate(this.x, this.y), this.f);
		getTableService().addRobot(table,  newRobot);
		return 1;
	}

}

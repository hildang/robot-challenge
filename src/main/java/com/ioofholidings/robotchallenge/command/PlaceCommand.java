package com.ioofholidings.robotchallenge.command;

import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Coordinate;
import com.ioofholidings.robotchallenge.model.Facing;
import com.ioofholidings.robotchallenge.model.Robot;
import com.ioofholidings.robotchallenge.model.Table;
import com.ioofholidings.robotchallenge.service.TableService;

public class PlaceCommand implements Command {
	
	private static final String USAGE_ERROR_MSG = "Usage: PLACE <x-coordinate>,<y-coordinate>,<facing>";
	private int x; 
	private int y; 
	private Facing f; 
	
	public PlaceCommand(String[] args) {
		if (args.length < 2) {
			throw new RobotChallengeException(USAGE_ERROR_MSG);
		}
		
		String[] parameters = args[1].split(",");
		if (parameters.length < 3) {
			throw new RobotChallengeException(USAGE_ERROR_MSG);
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
	public void execute(Table table) {
		Robot newRobot = new Robot(table.getRobotList().size(), new Coordinate(this.x, this.y), this.f);
		TableService.getInstance().addRobot(table,  newRobot);
	}

}

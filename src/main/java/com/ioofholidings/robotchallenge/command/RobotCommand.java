package com.ioofholidings.robotchallenge.command;

import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Table;
import com.ioofholidings.robotchallenge.service.TableService;

public class RobotCommand implements Command {

	private int number;
	public RobotCommand(String[] args) {
		if (args.length < 2) {
			throw new RobotChallengeException("Usage: ROBOT <robot id>");
		}	

		try {
			this.number = Integer.parseInt(args[1]);
		} catch (Exception e) {
			throw new RobotChallengeException(e.getMessage(), e);
		}
	}
	
	@Override
	public void execute(Table table) {
		TableService.getInstance().setActiveRobot(table, number);		
	}

}

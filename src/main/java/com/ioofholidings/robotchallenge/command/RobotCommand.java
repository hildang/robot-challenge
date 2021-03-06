package com.ioofholidings.robotchallenge.command;

import com.ioofholidings.robotchallenge.exception.RobotChallengeError;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Table;

public class RobotCommand extends Command {

	private int number;
	public RobotCommand(String[] args) {
		if (args.length < 2) {
			throw new RobotChallengeException(RobotChallengeError.ROBOT_COMMAND_USAGE_ERROR);				
		}	

		try {
			this.number = Integer.parseInt(args[1]);
		} catch (Exception e) {
			throw new RobotChallengeException(e.getMessage(), e);
		}
	}
	
	@Override
	public Object execute(Table table) {
		getTableService().setActiveRobot(table, number);	
		return 1;
	}

}

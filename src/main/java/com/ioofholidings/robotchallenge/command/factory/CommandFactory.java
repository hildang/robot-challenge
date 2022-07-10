package com.ioofholidings.robotchallenge.command.factory;

import com.ioofholidings.robotchallenge.command.Command;
import com.ioofholidings.robotchallenge.command.LeftCommand;
import com.ioofholidings.robotchallenge.command.MoveCommand;
import com.ioofholidings.robotchallenge.command.PlaceCommand;
import com.ioofholidings.robotchallenge.command.ReportCommand;
import com.ioofholidings.robotchallenge.command.RightCommand;
import com.ioofholidings.robotchallenge.command.RobotCommand;
import com.ioofholidings.robotchallenge.exception.RobotChallengeError;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;

public class CommandFactory {

	public static Command createCommand(String commandLine) {
		String[] args = commandLine.split(" ");
		if (args.length == 0) {
			throw new RobotChallengeException(RobotChallengeError.NO_COMMAND_PROVIDED_ERROR);				
		}
		switch (args[0]) {
			case "PLACE":
				return new PlaceCommand(args);
			case "LEFT":
				return new LeftCommand();
			case "RIGHT":
				return new RightCommand();
			case "MOVE":
				return new MoveCommand();
			case "ROBOT":
				return new RobotCommand(args);
			case "REPORT":
				return new ReportCommand();
			default: 
				throw new RobotChallengeException(RobotChallengeError.COMMAND_NOT_FOUDN_ERROR);				
		}
	}
	
}

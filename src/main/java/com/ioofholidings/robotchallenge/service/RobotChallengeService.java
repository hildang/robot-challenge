package com.ioofholidings.robotchallenge.service;

import org.springframework.stereotype.Service;

import com.ioofholidings.robotchallenge.command.Command;
import com.ioofholidings.robotchallenge.command.PlaceCommand;
import com.ioofholidings.robotchallenge.command.factory.CommandFactory;
import com.ioofholidings.robotchallenge.exception.RobotChallengeError;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;

@Service
public class RobotChallengeService {
	
	private boolean isFirstCommand = true;
	
	private TableService tableService; 
	
	public RobotChallengeService(TableService tableService) {
		this.tableService = tableService;
	}
	
    public void setFirstCommand(boolean isFirstCommand) {
		this.isFirstCommand = isFirstCommand;
	}

	public Object executeCommand(String line) {
        Command command = CommandFactory.createCommand(line);
		if (isFirstCommand && !(command instanceof PlaceCommand)) {
			throw new RobotChallengeException(RobotChallengeError.FIRST_COMMAND_ERROR);				
		}
		
		command.setTableService(this.tableService);
		Object result = command.execute(this.tableService.getTable());
		isFirstCommand = false;
		return result;
    }
}

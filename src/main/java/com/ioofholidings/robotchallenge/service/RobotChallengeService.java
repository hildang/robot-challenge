package com.ioofholidings.robotchallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ioofholidings.robotchallenge.command.Command;
import com.ioofholidings.robotchallenge.command.PlaceCommand;
import com.ioofholidings.robotchallenge.command.factory.CommandFactory;
import com.ioofholidings.robotchallenge.exception.RobotChallengeError;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Table;

@Service
public class RobotChallengeService {
	
	private Table table; 
	private boolean isFirstCommand = true;
	
	@Autowired
	private TableService tableService; 
	
	public RobotChallengeService(Table table) {
		this.table = table;
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
		Object result = command.execute(this.table);
		isFirstCommand = false;
		return result;
    }
}

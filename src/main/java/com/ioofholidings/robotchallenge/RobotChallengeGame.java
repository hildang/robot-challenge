package com.ioofholidings.robotchallenge;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ioofholidings.robotchallenge.command.Command;
import com.ioofholidings.robotchallenge.command.PlaceCommand;
import com.ioofholidings.robotchallenge.command.ReportCommand;
import com.ioofholidings.robotchallenge.command.factory.CommandFactory;
import com.ioofholidings.robotchallenge.exception.RobotChallengeError;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Table;

@Component
public class RobotChallengeGame implements CommandLineRunner {
	
	private Table table; 
	private boolean isFirstCommand = true;
	
	public RobotChallengeGame(Table table) {
		this.table = table;
	}
	
	@Override
    public void run(String... args) throws Exception {
        System.out.println("Enter Command:");
        
        try (Scanner scanner = new Scanner(System.in)) {
	        while (scanner.hasNext()) {
	        	try {
		            String line = scanner.nextLine();
		            Command command = CommandFactory.createCommand(line);
		            if (isFirstCommand && !(command instanceof PlaceCommand)) {
						throw new RobotChallengeException(RobotChallengeError.FIRST_COMMAND_ERROR);				
		            }
		            
		            command.execute(this.table);
		            if (command instanceof ReportCommand) {
		            	break;
		            }
	            
		            isFirstCommand = false;
	        	} catch (Exception e) {
	        		System.out.println(e.getMessage());
	        	}
	        }
        }
     }
}

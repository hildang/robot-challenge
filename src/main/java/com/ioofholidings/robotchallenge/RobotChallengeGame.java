package com.ioofholidings.robotchallenge;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ioofholidings.robotchallenge.model.Table;

@Component
public class RobotChallengeGame implements CommandLineRunner {
	
	private Table table; 
	
	public RobotChallengeGame(Table table) {
		this.table = table;
	}
	
	@Override
    public void run(String... args) throws Exception {
        System.out.println("Enter Command:");
        
        try (Scanner scanner = new Scanner(System.in)) {
	        while (scanner.hasNext()) {
	            String line = scanner.nextLine();
	            if (line.equals("REPORT")) {
	            	break;
	            }
	        }
        }
     }
}

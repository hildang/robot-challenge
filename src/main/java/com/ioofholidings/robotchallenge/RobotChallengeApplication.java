package com.ioofholidings.robotchallenge;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ioofholidings.robotchallenge.service.RobotChallengeService;

@SpringBootApplication
public class RobotChallengeApplication implements CommandLineRunner {

	@Autowired
	private RobotChallengeService robotChallengeService; 
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(RobotChallengeApplication.class)
	        .web(WebApplicationType.NONE)
	        .bannerMode(Mode.OFF)
	        .run(args);
	}

	@Override
    public void run(String... args) throws Exception {
        System.out.println("Enter Command:");
        
        try (Scanner scanner = new Scanner(System.in)) {
	        while (scanner.hasNext()) {
	        	try {
		            String line = scanner.nextLine();
		            Object result = robotChallengeService.executeCommand(line);
		            if (result instanceof String) {
		            	System.out.println(result);
		            	break;
		            }
	            
	        	} catch (Exception e) {
	        		System.out.println(e.getMessage());
	        	}
	        }
        }
     }
	
}

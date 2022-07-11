package com.ioofholidings.robotchallenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ioofholidings.robotchallenge.RobotChallengeApplication;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Table;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RobotChallengeApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class RobotChallengeServiceTests {
	
	@Autowired
	private RobotChallengeService robotChallengeService;
	
	@Autowired
	private Table table; 
	
    @AfterEach
    void resetTable() {
    	robotChallengeService.setFirstCommand(true);
    	table.setActiveRobot(null);
    	table.setRobotList(new ArrayList<>());
    	table.setIsOccupied(new boolean[table.getWidth()+1][table.getLength()+1]);
    }
    
    @Test
    public void shouldValidateCommands() {
    	Object result;
    	RobotChallengeException thrown = assertThrows(RobotChallengeException.class, () -> {
    		robotChallengeService.executeCommand("MOVE");
    	});
    	assertEquals("The first command must be a PLACE command", thrown.getMessage());
    	
    	thrown = assertThrows(RobotChallengeException.class, () -> {
    		robotChallengeService.executeCommand("PLACE 0,NORTH");
    	});
    	assertEquals("Usage: PLACE <x-coordinate>,<y-coordinate>,<facing>", thrown.getMessage());
    	
    	result = robotChallengeService.executeCommand("PLACE 0,0,NORTH");
    	assertEquals(1, result);
    	
    	thrown = assertThrows(RobotChallengeException.class, () -> {
    		robotChallengeService.executeCommand("ROBOT");
    	});
    	assertEquals("Usage: ROBOT <robot id>", thrown.getMessage());
    }
    
    @Test
    public void shouldReportCorrectFacing() {
    	Object result;
    	result = robotChallengeService.executeCommand("PLACE 0,0,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("LEFT");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("REPORT");
    	assertEquals("0,0,WEST", result);
    }
    
    @Test
    public void shouldReportNewCoordinates() {
    	Object result;
    	result = robotChallengeService.executeCommand("PLACE 0,1,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("REPORT");
    	assertEquals("0,2,NORTH", result);
    }
    

    @Test
    public void shouldReportCorrectDirectionAndCoordinates() {
    	Object result;
    	result = robotChallengeService.executeCommand("PLACE 1,2,EAST");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("RIGHT");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("REPORT");
    	assertEquals("1,0,SOUTH", result);
    }
    
    @Test
    public void shouldIgnoreIfRobotWillDropOffLowerBound() {
    	Object result;
    	result = robotChallengeService.executeCommand("PLACE 0,0,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("LEFT");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("REPORT");
    	assertEquals("0,0,WEST", result);
    }
    
    @Test
    public void shouldIgnoreIfRobotWillDropOffUpperBound() {
    	Object result;
    	result = robotChallengeService.executeCommand("PLACE 5,5,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("REPORT");
    	assertEquals("5,5,NORTH", result);
    }

    @Test
    public void shouldReportMultipleRobots() {
    	Object result;
    	result = robotChallengeService.executeCommand("PLACE 0,0,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("PLACE 0,0,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("REPORT");
    	assertEquals("0,0,NORTH" + System.lineSeparator() 
    		+ "Total robots: 2" + System.lineSeparator() 
    		+ "Active robot: 2", result);
    }
	
    @Test
    public void shouldSetActiveRobot() {
    	Object result;
    	result = robotChallengeService.executeCommand("PLACE 0,0,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("PLACE 0,0,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("ROBOT 1");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("RIGHT");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("REPORT");
    	assertEquals("0,2,EAST" + System.lineSeparator() 
    		+ "Total robots: 2" + System.lineSeparator() 
    		+ "Active robot: 1", result);
    }
    

    @Test
    public void shouldAvoidRobotsCollision() {
    	Object result;
    	result = robotChallengeService.executeCommand("PLACE 0,0,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("PLACE 0,0,NORTH");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("MOVE");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("RIGHT");
    	assertEquals(1, result);
    	result = robotChallengeService.executeCommand("REPORT");
    	assertEquals("0,0,EAST" + System.lineSeparator() 
    		+ "Total robots: 2" + System.lineSeparator() 
    		+ "Active robot: 2", result);
    }
}

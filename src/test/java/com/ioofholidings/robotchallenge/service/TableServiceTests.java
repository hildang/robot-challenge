package com.ioofholidings.robotchallenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ioofholidings.robotchallenge.RobotChallengeApplication;
import com.ioofholidings.robotchallenge.model.Coordinate;
import com.ioofholidings.robotchallenge.model.Facing;
import com.ioofholidings.robotchallenge.model.Robot;
import com.ioofholidings.robotchallenge.model.Table;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RobotChallengeApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class TableServiceTests {
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private Table table;
	
    @AfterEach
    void resetTable() {
    	table.setActiveRobot(null);
    	table.setRobotList(new ArrayList<>());
    	table.setIsOccupied(new boolean[table.getWidth()+1][table.getLength()+1]);
    }
    
    @Test
    public void shouldAddRobot() {
    	Coordinate newCoord = new Coordinate(0,0);
    	Robot newRobot = new Robot(newCoord, Facing.NORTH);
    	
    	tableService.addRobot(table, newRobot);
    	assertEquals(1, table.getRobotList().size());
    	assertEquals(1, newRobot.getId());
    	assertEquals(true, newRobot.isActive());
    	assertEquals(newRobot, table.getActiveRobot());
    	assertEquals(true, table.getIsOccupied()[0][0]);
    	
    	newCoord = new Coordinate(3,3);
    	newRobot = new Robot(newCoord, Facing.EAST);
    	tableService.addRobot(table, newRobot);
    	assertEquals(2, table.getRobotList().size());
    	assertEquals(2, newRobot.getId());
    	assertEquals(true, newRobot.isActive());
    	assertEquals(newRobot, table.getActiveRobot());
    	assertEquals(true, table.getIsOccupied()[3][3]);
    }
    
    @Test
    public void shouldNotAddRobotIfCollides() {
    	Coordinate newCoord = new Coordinate(0,0);
    	Robot newRobot = new Robot(newCoord, Facing.NORTH);
    	
    	tableService.addRobot(table, newRobot);
    	assertEquals(1, table.getRobotList().size());
    	assertEquals(1, newRobot.getId());
    	assertEquals(true, newRobot.isActive());
    	assertEquals(newRobot, table.getActiveRobot());
    	assertEquals(true, table.getIsOccupied()[0][0]);
    	
    	newCoord = new Coordinate(0,0);
    	newRobot = new Robot(newCoord, Facing.EAST);
    	tableService.addRobot(table, newRobot);
    	assertEquals(1, table.getRobotList().size());
    	assertEquals(false, newRobot.isActive());
    	assertNotEquals(newRobot, table.getActiveRobot());
    	assertEquals(true, table.getIsOccupied()[0][0]);
    }
    

    @Test
    public void shouldUpdateRobot() throws Exception {
    	Coordinate newCoord = new Coordinate(0,0);
    	Robot newRobot = new Robot(newCoord, Facing.NORTH);
    	
    	tableService.addRobot(table, newRobot);
    	assertEquals(1, table.getRobotList().size());
    	assertEquals(1, newRobot.getId());
    	assertEquals(true, newRobot.isActive());
    	assertEquals(newRobot, table.getActiveRobot());
    	assertEquals(true, table.getIsOccupied()[0][0]);
    	
    	Coordinate updateCoord = (Coordinate) newCoord.clone();
    	updateCoord.setX(1);
    	tableService.updateRobot(table, newRobot, updateCoord);
    	assertEquals(1, table.getRobotList().size());
    	assertEquals(updateCoord, newRobot.getCoordinate());
    	assertEquals(false, table.getIsOccupied()[0][0]);
    	assertEquals(true, table.getIsOccupied()[1][0]);
    }

    @Test
    public void shouldNotUpdateRobotIfCollides() throws Exception {
    	Coordinate newCoord = new Coordinate(0,1);
    	Robot newRobot = new Robot(newCoord, Facing.NORTH);
    	
    	tableService.addRobot(table, newRobot);
    	assertEquals(1, table.getRobotList().size());
    	assertEquals(1, newRobot.getId());
    	assertEquals(true, newRobot.isActive());
    	assertEquals(newRobot, table.getActiveRobot());
    	assertEquals(true, table.getIsOccupied()[0][1]);
    	
    	newCoord = new Coordinate(0,0);
    	newRobot = new Robot(newCoord, Facing.NORTH);
    	tableService.addRobot(table, newRobot);
    	assertEquals(2, table.getRobotList().size());
    	assertEquals(2, newRobot.getId());
    	assertEquals(true, newRobot.isActive());
    	assertEquals(newRobot, table.getActiveRobot());
    	assertEquals(true, table.getIsOccupied()[0][0]);
    	
    	Coordinate updateCoord = (Coordinate) newCoord.clone();
    	updateCoord.setY(1);
    	tableService.updateRobot(table, newRobot, updateCoord);
    	assertEquals(2, table.getRobotList().size());
    	assertNotEquals(updateCoord, newRobot.getCoordinate());
    	assertEquals(true, table.getIsOccupied()[0][0]);
    	assertEquals(true, table.getIsOccupied()[0][1]);
    }
    

    @Test
    public void shouldSetRobotActive() throws Exception {
    	Coordinate newCoord = new Coordinate(0,0);
    	Robot robot1 = new Robot(newCoord, Facing.NORTH);
    	tableService.addRobot(table, robot1);
    	assertEquals(1, table.getRobotList().size());
    	assertEquals(1, robot1.getId());
    	assertEquals(true, robot1.isActive());
    	assertEquals(robot1, table.getActiveRobot());
    	assertEquals(true, table.getIsOccupied()[0][0]);

    	newCoord = new Coordinate(3,3);
    	Robot robot2 = new Robot(newCoord, Facing.EAST);
    	tableService.addRobot(table, robot2);
    	assertEquals(2, table.getRobotList().size());
    	assertEquals(2, robot2.getId());
    	assertEquals(true, robot2.isActive());
    	assertEquals(robot2, table.getActiveRobot());
    	assertEquals(true, table.getIsOccupied()[3][3]);
    	
    	tableService.setActiveRobot(table, 1);
    	assertEquals(2, table.getRobotList().size());
    	assertEquals(true, robot1.isActive());
    	assertEquals(robot1, table.getActiveRobot());
    	
    }
}

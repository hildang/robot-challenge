package com.ioofholidings.robotchallenge.command;

import com.ioofholidings.robotchallenge.exception.RobotChallengeError;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Coordinate;
import com.ioofholidings.robotchallenge.model.Robot;
import com.ioofholidings.robotchallenge.model.Table;
import com.ioofholidings.robotchallenge.service.TableService;

public class MoveCommand implements Command {

	@Override
	public void execute(Table table) {
		Robot activeRobot = table.getActiveRobot();
		if (activeRobot == null) {
			// No active robot is set, ignore
			return;
		}
		
		try {
			Coordinate newCoordinate = (Coordinate) activeRobot.getCoordinate().clone();

			switch (activeRobot.getFacing()) {
				case NORTH:
					newCoordinate.setY(newCoordinate.getY() + 1);
					break;
				case EAST:
					newCoordinate.setX(newCoordinate.getX() + 1);
					break;
				case SOUTH:
					newCoordinate.setY(newCoordinate.getY() - 1);
					break;
				case WEST:
					newCoordinate.setX(newCoordinate.getX() - 1);
					break;
				default:
					throw new RobotChallengeException(RobotChallengeError.ACTIVE_ROBOT_FACING_ERROR);				
			}
			
			TableService.getInstance().updateRobot(table, activeRobot, newCoordinate);
		} catch (CloneNotSupportedException e) {
			throw new RobotChallengeException(e.getMessage(), e);
		}
	}

}

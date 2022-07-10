package com.ioofholidings.robotchallenge.command;

import com.ioofholidings.robotchallenge.exception.RobotChallengeError;
import com.ioofholidings.robotchallenge.exception.RobotChallengeException;
import com.ioofholidings.robotchallenge.model.Facing;
import com.ioofholidings.robotchallenge.model.Robot;
import com.ioofholidings.robotchallenge.model.Table;

public class LeftCommand implements Command {

	@Override
	public void execute(Table table) {
		Robot activeRobot = table.getActiveRobot();
		if (activeRobot == null) {
			// No active robot is set, ignore
			return;
		}
		
		Facing newFacing;
		switch (activeRobot.getFacing()) {
			case NORTH:
				newFacing = Facing.WEST;
				break;
			case EAST:
				newFacing = Facing.NORTH;
				break;
			case SOUTH:
				newFacing = Facing.EAST;
				break;
			case WEST:
				newFacing = Facing.SOUTH;
				break;
			default:
				throw new RobotChallengeException(RobotChallengeError.ACTIVE_ROBOT_FACING_ERROR);				
		}
		
		activeRobot.setFacing(newFacing);
		table.setActiveRobot(activeRobot);
	}

}

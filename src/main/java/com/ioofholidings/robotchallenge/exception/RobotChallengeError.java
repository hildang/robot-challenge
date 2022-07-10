package com.ioofholidings.robotchallenge.exception;

public enum RobotChallengeError {

	FIRST_COMMAND_ERROR("The first command must be a PLACE command"),
	NO_COMMAND_PROVIDED_ERROR("No command provided"),
	COMMAND_NOT_FOUDN_ERROR("No matched command found"),
	PLACE_COMMAND_USAGE_ERROR("Usage: PLACE <x-coordinate>,<y-coordinate>,<facing>"),
	ROBOT_COMMAND_USAGE_ERROR("Usage: ROBOT <robot id>"),
	ROBOT_NUMBER_INVALID("Invalid Robot Number"),
	ACTIVE_ROBOT_FACING_ERROR("Error with robot facing");
	
	private String errorMessage;
	
	RobotChallengeError(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
}

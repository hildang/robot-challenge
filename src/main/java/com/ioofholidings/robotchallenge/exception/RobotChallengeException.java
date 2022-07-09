package com.ioofholidings.robotchallenge.exception;

public class RobotChallengeException extends RuntimeException {

	private static final long serialVersionUID = -8803852703788298645L;

	public RobotChallengeException(String errorMessage) {
        super(errorMessage);
    }
	
	public RobotChallengeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
	
}

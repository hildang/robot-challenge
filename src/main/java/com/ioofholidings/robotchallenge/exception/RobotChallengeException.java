package com.ioofholidings.robotchallenge.exception;

public class RobotChallengeException extends RuntimeException {

	private static final long serialVersionUID = -8803852703788298645L;

	public RobotChallengeException(RobotChallengeError error) {
        super(error.getErrorMessage());
    }
	
	public RobotChallengeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
	
}

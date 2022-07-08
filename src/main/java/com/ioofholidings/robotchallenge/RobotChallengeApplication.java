package com.ioofholidings.robotchallenge;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RobotChallengeApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(RobotChallengeApplication.class)
	        .web(WebApplicationType.NONE)
	        .bannerMode(Mode.OFF)
	        .run(args);
	}

}

package br.com.sintechs.stufa.drools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsActionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(DroolsActionHandler.class);
	
	public static void addAlert(String message) {
		LOGGER.info(message);
	}

}

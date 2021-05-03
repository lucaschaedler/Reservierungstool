package ch.backyardcoders.mgmt.logger;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoggerClass {

	public static final String LOGGER_NAME = LoggerClass.class.getSimpleName();
	private Logger logger = null; // The logger object for this class

	//write logs on a textfile in xml an din the console
	
	public LoggerClass() {
		logger = Logger.getLogger(LOGGER_NAME);
		logger.setLevel(Level.INFO);

		try {
			Handler logHandler = new FileHandler("%t/" + LOGGER_NAME + "_%u" + "_%g" + ".log", 100000, 2);//oder h
			logHandler.setLevel(Level.FINE);
			logger.addHandler(logHandler);
		} catch (Exception e) {
			throw new RuntimeException("Unable to initialize log files: " + e.toString());
		}
	}

	public Logger getLogger() {
		return logger;
	}

}

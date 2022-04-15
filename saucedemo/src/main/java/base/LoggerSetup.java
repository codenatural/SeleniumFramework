package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerSetup {
	
	/*
	 * Dependancy Needed 
	 * log4j-api-<version>.jar 
	 * log4j-core-<version>.jar
	 */
	
	/*
	 * We can configure Log4j2 with our application using a configuration file
	 * written in XML, JSON, YAML, or properties format. We can do it
	 * programmatically as well
	 */
	
	/*
	 * Log4j has the ability to automatically configure itself during
	 * initialization. It has an order to look for the configuration file in the
	 * application. Log4j will provide a default configuration if it cannot locate a
	 * configuration file.
	 */
	

	/*
	 * Log levels are a mechanism to categorise logs. Levels used for identifying
	 * the severity of an event. We can easily configure levels to specify which log
	 * details we want to see. Log4j provides below levels:-
	 */
	
	
	/*
	 * ALL – To log all events. DEBUG – A general debugging event. ERROR – An error
	 * in the application, possibly recoverable. FATAL – A severe error that will
	 * prevent the application from continuing. INFO – An event for informational
	 * purposes. TRACE – A fine-grained debug message, typically capturing the flow
	 * through the application. WARN – An event that might possibly lead to an
	 * error. OFF – No events will be logged.
	 */
	
	/*
	 * Log4j follows order as below: 
	 * ALL < TRACE < DEBUG < INFO < WARN < ERROR <FATAL
	 */
	
	/*
	 * If we mention log level as INFO then all INFO, WARN, ERROR and FATAL events
	 * will be logged. If we mention log level as WARN then all WARN, ERROR and
	 * FATAL events will be logged.
	 */
	
	
	/*
	 * Appenders
	 * 
	 * We can specify destinations to keep event logs. We may want to print those
	 * logs in the console or any external file. Appenders usually are only
	 * responsible for writing the event data to the target destination. We may use
	 * multiple appenders.
	 */
	
	/*
	 * Appenders – Where to print logs. We can have multiple appenders. Loggers –
	 * From where to log and what to log
	 */
	
	public static Logger log = LogManager.getLogger(LoggerSetup.class);
	
}

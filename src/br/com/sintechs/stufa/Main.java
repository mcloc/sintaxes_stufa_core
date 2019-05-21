package br.com.sintechs.stufa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.drools.ExpertSystemHandler;
import br.com.sintechs.stufa.ipc.IPCHandler;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;

public class Main {

//	static Logger _log = Logger.getGlobal();
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	static GlobalProperties globalProperties = new GlobalProperties();
	static IPCWriteInterrupt writeInterrupt = new IPCWriteInterrupt();

	static public void main(String[] args) throws Exception {
		LOGGER.info("Starting Arduino Serial Reader...");
		
		//Start IPCHandler Thread
//		IPCHandler ipc = new IPCHandler(globalProperties, writeInterrupt);
//		ipc.start();
		
		//Start Drools Thread
		ExpertSystemHandler es = new ExpertSystemHandler(globalProperties, writeInterrupt);
		es.run();
		
		Thread.sleep(200);
		
		IPCHandler ipc = new IPCHandler(globalProperties, writeInterrupt);
		
		
		//Start SerialCommunicationHnalder Thread
//		SerialCommunicationHandler serial = new SerialCommunicationHandler(globalProperties, writeInterrupt, es);
//		serial.start();
		

	}
	

}

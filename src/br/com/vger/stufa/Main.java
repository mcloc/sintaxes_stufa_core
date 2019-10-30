package br.com.vger.stufa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.vger.stufa.drools.ExpertSystemHandler;
import br.com.vger.stufa.ipc.IPCWriteInterrupt;
import br.com.vger.stufa.modules.ModulesHandler;

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
		
//		IPCHandler ipc = new IPCHandler(globalProperties, writeInterrupt);
		
		ModulesHandler mh = new ModulesHandler(globalProperties, writeInterrupt, es);
		mh.start();
		
		//Start SerialCommunicationHnalder Thread
//		SerialCommunicationHandler serial = new SerialCommunicationHandler(globalProperties, writeInterrupt, es);
//		serial.start();
		

	}
	

}

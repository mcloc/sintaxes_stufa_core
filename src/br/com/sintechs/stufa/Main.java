package br.com.sintechs.stufa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.drools.ExpertSystemHandler;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;
import br.com.sintechs.stufa.serial.SerialCommunicationHandler;

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
		es.start();
		
		// :: TO DROOLS COMMUNITY :: 
		// THIS SERIALCOMM THREAD IN THE REAL EMBEDDED APP IS AIM TO GET DATA FROM ARDUINO ON SERIAL PORT
		// AND HIDRATE SINTECHSSAMPLING POJO AND ADD IT AS EVENT ON DROOLS CEP ENTRYPOINT
		// AS YOU'LL SEE IN THIS SERIALCOMM CLASS WE'VE CHANGED THE REAL SERIALREAD AND IMPLEMENTED A 
		// EVENT TEST OF INSERTING INTO ENTRYPOINT AN EVENT WITH A RANDOM NUMBER
		//
		// THE PROBLEM: WE HAVING AN EXCEPTION CAUGHT IN DROOLSACTIONHANDLER CLASS DURING INSERTING ANY KIND OF EVENT
		// AFTER THE FIREFUNTILHAL() IS CALLED;
		//
		// THE DRL FILE IS ALSON CHANGED FOR TESTING PROPOUSE WITH AND  eval(1 == 1) SO IT COULD ALWAYS FIRE WHEN 
		// A NEW EVENT IS INSERTED. BUT THE RULE IS NOT FIREDED, AND THE entryPoint.insert(random_number) CAUSES THE
		// EXCEPTION.
		//
		// THE EXCEPTION: HAS NO MESSAGE OR STACKTRACE [[  ExpertSystemHandler.addEvent(random_int) ]]
		//
		// 
		//
		//Start SerialCommunicationHnalder Thread
		SerialCommunicationHandler serial = new SerialCommunicationHandler(globalProperties, writeInterrupt, es);
		serial.start();
		

	}
	

}

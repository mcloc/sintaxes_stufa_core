package br.com.sintechs.stufa.serial;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.Main;
import br.com.sintechs.stufa.drools.ExpertSystemHandler;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;

public class SerialCommunicationHandler extends Thread{

	private GlobalProperties globalProperties;
	private static final Logger LOGGER = LoggerFactory.getLogger(SerialCommunicationHandler.class);
	private IPCWriteInterrupt writeInterrupt;
	private ExpertSystemHandler drools;

	public SerialCommunicationHandler(GlobalProperties globalProperties, IPCWriteInterrupt writeInterrupt, ExpertSystemHandler es) {
		this.globalProperties = globalProperties;
		this.writeInterrupt = writeInterrupt;
		this.drools = es;
	}
	
	public void run() {
		SerialComm serial = new SerialComm(globalProperties, writeInterrupt, drools);
		
		//Never returns, this is embedded system. Auto crash recovery
		while(true) {
		
			try {
				//This is commented for TEST reason asked by Tirelli's to check drools package bad behavior 
				serial.startSerialCommunication();
				
				
				//TEST: this call is a test since the serial communication can not be done outside of the arduino's board site
//				serial.testDrools();
				
				//DEBUG: this will be removed after TESTING
				Thread.sleep(1000);
				
				
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

}

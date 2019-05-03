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
		
		while(true) {
		//Never returns
			try {
				serial.startSerialCommunication();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

}

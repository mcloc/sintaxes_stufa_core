package br.com.sintechs.stufa;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.sintechs.stufa.ipc.IPCHandler;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;
import br.com.sintechs.stufa.serial.SerialCommunicationHandler;

public class Main {

	static Logger _log = Logger.getGlobal();
	static GlobalProperties globalProperties = new GlobalProperties();
	static IPCWriteInterrupt writeInterrupt = new IPCWriteInterrupt();

	static public void main(String[] args) throws Exception {
		_log.log(Level.INFO, "Starting Arduino Serial Reader...");
		
		//Start IPCHandler Thread
		IPCHandler ipc = new IPCHandler(globalProperties, _log,writeInterrupt);
		ipc.start();
		
		//Start SerialCommunicationHnalder Thread
		SerialCommunicationHandler serial = new SerialCommunicationHandler(globalProperties, _log,writeInterrupt);
		serial.start();
	}
	

}

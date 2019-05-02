package br.com.sintechs.stufa.serial;

import java.util.logging.Logger;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;

public class SerialCommunicationHandler extends Thread{

	private GlobalProperties globalProperties;
	private Logger _log;
	private IPCWriteInterrupt writeInterrupt;

	public SerialCommunicationHandler(GlobalProperties globalProperties, Logger _log,
			IPCWriteInterrupt writeInterrupt) {
		this.globalProperties = globalProperties;
		this._log = _log;
		this.writeInterrupt = writeInterrupt;
	}
	
	public void run() {
		SerialComm serial = new SerialComm(globalProperties,_log, writeInterrupt);
		
		while(true) {
		//Never returns
			try {
				serial.startSerialCommunication();
			} catch (Exception e) {
				_log.severe(e.getMessage());
			}
		}
	}

}

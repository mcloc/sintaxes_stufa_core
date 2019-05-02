package br.com.sintechs.stufa.ipc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.com.sintechs.stufa.GlobalProperties;

public class IPCHandler extends Thread {
	
	
	protected GlobalProperties globalProperties;
	protected IPCWriteInterrupt ipcWriteInterrupt;
	private List<IPCMessage> messagesList = new ArrayList<IPCMessage>();
	private Logger _log;
	
	
	public IPCHandler(GlobalProperties globalProperties, Logger _log, IPCWriteInterrupt writeInterrupt) {
		this.globalProperties = globalProperties;
		this.ipcWriteInterrupt = writeInterrupt;
		this._log = _log;
	}

	public void run() {
		IPCServer ipcServer = new IPCServer(); 
		try {
			ipcServer.start(globalProperties.getIPC_SERVER_PORT(), messagesList);
		} catch (IOException e) {
			_log.severe(e.getMessage());
		}
		_log.info("Thread IPCHandler started...");
	}

}

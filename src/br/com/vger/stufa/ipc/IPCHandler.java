package br.com.vger.stufa.ipc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.vger.stufa.GlobalProperties;

public class IPCHandler extends Thread {
	
	
	protected GlobalProperties globalProperties;
	protected IPCWriteInterrupt ipcWriteInterrupt;
	private List<IPCMessage> messagesList = new ArrayList<IPCMessage>();
    private static final Logger LOGGER = LoggerFactory.getLogger(IPCHandler.class);

	public IPCHandler(GlobalProperties globalProperties, IPCWriteInterrupt writeInterrupt) {
		this.globalProperties = globalProperties;
		this.ipcWriteInterrupt = writeInterrupt;
	}

	public void run() {
		
		try {
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		/*new Thread() {

			@Override
			public void run() {
				IPCServer ipcServer = new IPCServer(); 
				try {
					ipcServer.start(globalProperties.getIPC_SERVER_PORT(), messagesList);
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}.start();*/

		LOGGER.info("Thread IPCHandler started...");
	}

}

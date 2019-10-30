package br.com.vger.stufa.ipc;

import java.util.ArrayList;
import java.util.List;


/**
 * IPCWriteInterrupt: Class to hold Serial Messages to Arduino. 
 * This Messages must be already on Arduino json communication protocol. 
 * @author mcloc
 *
 */
public class IPCWriteInterrupt {
	
	/**
	 * List of Json Arduino Protocol Messages for sending to Serial 
	 */
	private List<String> messages = new ArrayList<String>();
	
	/**
	 * Returns the total of messages to send to Arduino
	 * @return
	 */
	public int getMessagesCount() {
		return messages.size();
	}
	
	/**
	 * Returns and remove from the current list the First Message from stack (FIFO)
	 * @return
	 */
	public String getMessage() {
		return messages.remove(0);
	}

}

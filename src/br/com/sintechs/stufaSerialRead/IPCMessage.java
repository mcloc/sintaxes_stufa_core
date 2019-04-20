package br.com.sintechs.stufaSerialRead;

import java.util.HashMap;
import java.util.List;

public class IPCMessage {
	
	private String commandMethod;
	private String raw;
	protected List<HashMap<String,String>> commandArgs;
	
	public IPCMessage(String raw_message) {
		this.raw = raw_message;
		decodeMessage();
	}
	
	private void decodeMessage() {
		// TODO Auto-generated method stub
		
	}

	public String getCommandMethod() {
		return commandMethod;
	}
	public void setCommandMethod(String commandMethod) {
		this.commandMethod = commandMethod;
	}
	public List<HashMap<String, String>> getCommandArgs() {
		return commandArgs;
	}
	public void setCommandArgs(List<HashMap<String, String>> commandArgs) {
		this.commandArgs = commandArgs;
	}
	
	
	
	

}

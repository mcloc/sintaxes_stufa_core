package br.com.sintechs.stufaSerialRead;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * IPCMessage raw example:
		{"commandMethod":"wrtitePort", "commandArgs":[{"LED#1":"true"}]}
 * @author mcloc
 *
 */
public class IPCMessage {
	
	private String commandMethod;
	private String raw;
	protected List<Map<String,String>> commandArgs;
	
	public IPCMessage(String raw_message) {
		this.raw = raw_message;
		decodeMessage();
	}
	
	private void decodeMessage() {
//		JsonParser parser = new JsonParser();
//		JsonObject object = (JsonObject) parser.parse(raw);
		Gson gson = new Gson();
		IPCMessage x = gson.fromJson(raw, this.getClass());
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

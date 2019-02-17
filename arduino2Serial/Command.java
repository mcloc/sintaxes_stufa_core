package br.com.neuraltec.arduino2Serial;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import arduino.Arduino;



public class Command implements CommandInterface {
	static Logger _log = Logger.getGlobal();
	protected String cmd_string;
	protected String name;
	protected JsonArray jsonArgsObject;
	protected JsonObject jsonCommandObject;
	protected Command cmdExecutionOject;
	protected List<CommandArguments> commandArguments = new ArrayList<CommandArguments>();
	
	public Command(String _cmd_string) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		cmd_string = _cmd_string;
		jsonCommandObject = new JsonParser().parse(cmd_string).getAsJsonObject();
		name = jsonCommandObject.get("command").getAsString();
//		cmdExecutionOject = prepareExecution();
//		CommandArguments.get
	}
	
	public synchronized Command prepareExecution() throws Exception {
		Constructor<?> c = Class.forName("br.com.neuraltec.arduino2Serial.Command"+name).getConstructor(String.class);
		Command tmpCommandObject = (Command) c.newInstance(cmd_string);
		tmpCommandObject.jsonCommandObject = new JsonParser().parse(cmd_string).getAsJsonObject();
//		tmpCommandObject.name = jsonCommandObject.get("command").getAsString();
		tmpCommandObject.jsonArgsObject = jsonCommandObject.get("args").getAsJsonArray();
		tmpCommandObject.commandInitialize();
		return tmpCommandObject;
	}

	@Override
	public Command commandInitialize() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command commandPrepare() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(Arduino arduinoSerial) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
//	protected Command commandPrepare(Command _commandClass) throws Exception {
//		//TODO:implement parse on Command Specific Class
//		throw new Exception("You must implement on your own Command Subclass");
//	}

//	public void execute() throws Exception {
//		//TODO:implement parse on Command Specific Class
//		throw new Exception("You must implement on your own execute on Command Subclass");
//	}
	
//	private List<CommandArguments> getArgs() throws Exception {
//		//TODO:implement parse on Command Specific Class
//		throw new Exception("You must implement on your own getArgs on Command Subclass");
//	}
	
//	public Command commandInitialize() throws Exception {
//		//TODO:implement parse on Command Specific Class
//		throw new Exception("You must implement on your own commandInitialize on Command Subclass");
//	}
}

interface CommandInterface{
	public Command commandInitialize() throws Exception;
	public Command commandPrepare() throws Exception;
	public void execute(Arduino arduionoSerial) throws Exception;
	
}

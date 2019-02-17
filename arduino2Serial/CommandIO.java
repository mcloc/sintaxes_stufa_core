package br.com.neuraltec.arduino2Serial;

import java.lang.reflect.InvocationTargetException;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import arduino.Arduino;

public class CommandIO extends Command{
	
	/**
	 * super()
	 * 	protected String cmd_string;
	 *	protected String name;
	 *	protected JsonObject jsonArgsObject;
	 *	protected JsonObject jsonCommandObject;
	 *	protected Command cmdExecutionOject;
	 *	protected Map<String, String> argsMap;
	 *	protected List<CommandArguments> commandArguments;
	 * 
	 * 
	 * @param _cmd_string
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	
	public CommandIO(String _cmd_string) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		super(_cmd_string);
		
	}

	@Override
	public CommandIO commandInitialize() {
		JsonArray arr = jsonArgsObject.getAsJsonArray();
		for(JsonElement e : arr) {
			JsonObject x = e.getAsJsonObject();
			Set<Entry<String, JsonElement>> argsSet = x.entrySet();
			CommandArguments ca = new CommandIOArgs(this);
			for (Entry<String, JsonElement> entry : argsSet) {
				ca.name = entry.getKey();
				ca.value = entry.getValue().getAsString();
			}
			
			this.commandArguments.add(ca);
		}
		
		return this;
		
	}

	@Override
	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * @see br.com.neuraltec.arduino2Serial.Command#execute()
	 */
	public void execute(Arduino arduinoSerial) throws Exception {
		String CommandIOAmountArguments = "3";
		String BOARD_PIN = null;
		String IN_OUT = null;
		String LOW_HI = null;
		
		for(CommandArguments arg : commandArguments) {
			switch(arg.name) {
				case "BOARD_PIN":
					BOARD_PIN = arg.getValue();
					break;
				case "IN_OUT":
					IN_OUT = arg.getValue();
					break;
				case "LOW_HI":
					LOW_HI = arg.getValue();
					break;
				default: 
					throw new Exception("argument: "+arg.getName()+" for command: "+name+" is not implemented" );
			}
		}
		
		if(BOARD_PIN == null || BOARD_PIN.isEmpty())
			throw new Exception("argument BOARD_PIN is empty and mandatory.: ");

		if(IN_OUT == null || IN_OUT.isEmpty())
			throw new Exception("argument IN_OUT is empty and mandatory.: ");
		
		if(LOW_HI == null || LOW_HI.isEmpty())
			throw new Exception("argument LOW_HI is empty and mandatory.: ");
		
		//set Amount of arguments
//		arduinoSerial.serialWrite(CommandIOAmountArguments);
		
		//set BOARD PIN 
		String command = "{\"BOARD_PIN\":\""+BOARD_PIN+"\",\"IN_OUT\":\""+IN_OUT+"\",\"LOW_HI\":\""+LOW_HI+"\"}";
		_log.info(command);
		arduinoSerial.serialWrite(command);
		
//		_log.info("resposta" + arduinoSerial.serialRead());
		Thread.sleep(2250);
		
	}

}

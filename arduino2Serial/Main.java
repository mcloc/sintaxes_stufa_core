package br.com.neuraltec.arduino2Serial;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import arduino.Arduino;

public class Main {
	
	static Logger _log = Logger.getGlobal();
	static final String SHM_ADDRESS_READ = "/dev/shm/serial2arduinoRead";
	static final String SHM_ADDRESS_WRITE = "/dev/shm/serial2arduinoWrite";
	static final String SHM_ADDRESS_READ_LOCK = "/dev/shm/serial2arduinoReadLock";
	static final String SHM_ADDRESS_WRITE_LOCK = "/dev/shm/serial2arduinoWriteLock";
	
	public static void main(String args[]) throws Exception {
		_log.info("Starting messageReader...");
		checkSHMFiles();
		
		
		MessageQueue msgQueue = new MessageQueue(new ArrayList<String>());
		Arduino arduinoSerial = new Arduino("/dev/ttyUSB0", 9600);
		arduinoSerial.openConnection();
		MessageReaderThread reader = new MessageReaderThread(SHM_ADDRESS_READ, SHM_ADDRESS_READ_LOCK, SHM_ADDRESS_WRITE_LOCK, msgQueue );
		Runtime.getRuntime().addShutdownHook(reader);
		reader.start();
		
		
		
		while(true) {
			
			/////////////// TO READ FROM NET AND SEND TO ARDUINO ////////////////
			/*try {
			String cmd_string = msgQueue.getMessage();
			if(cmd_string == null) {
				Thread.sleep(40);
				continue;
			}
			
			if(cmd_string.isEmpty())
				throw new Exception("command NULL aborting...");
				
			JsonObject jsonCommandObject = new JsonParser().parse(cmd_string).getAsJsonObject();	
			System.out.println(cmd_string);*/
			/*String command_str = jsonCommandObject.get("command").getAsString();
			Constructor<?> c = Class.forName("br.com.neuraltec.arduino2Serial.Command"+command_str).getConstructor(String.class);
			Command command =  (Command) c.newInstance(cmd_string);
			
			command = command.prepareExecution();
			command.execute(arduinoSerial);*/
			
//		    _log.info("----READED FROM SHM -----"+cmd_string);
			/*} catch(Exception e) {
				 arduinoSerial.closeConnection();
				_log.severe(e.getMessage());
				e.printStackTrace();
			} finally {
				Thread.sleep(100);
			}*/
			////////////////////////////////////////////////////////////////////////////
			
			
			
			
			
			////////////////////// TO READ FROM ARDUINO AND SEND TO PHONE //////////////
			
			String readed = arduinoSerial.serialRead();
			System.out.println("cmd: " + readed);
			Thread.sleep(40);
			////////////////////////////////////////////////////////////////////////////
			
			
		} // END of While(true)
		
		
	}

	private static void checkSHMFiles() throws Exception {
		File bufferSerial = new File(SHM_ADDRESS_READ);
		boolean exists = bufferSerial.exists();
		if (!exists)
			try {
				bufferSerial.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		bufferSerial = new File(SHM_ADDRESS_WRITE);
		exists = bufferSerial.exists();
		if (!exists)
			try {
				bufferSerial.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e);
			}
		bufferSerial = new File(SHM_ADDRESS_READ_LOCK);
		exists = bufferSerial.exists();
		if (!exists)
			try {
				bufferSerial.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e);
			}
		bufferSerial = new File(SHM_ADDRESS_WRITE_LOCK);
		exists = bufferSerial.exists();
		if (!exists)
			try {
				bufferSerial.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e);
			}
		
	}

}

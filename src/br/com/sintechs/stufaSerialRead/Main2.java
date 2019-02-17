package br.com.sintechs.stufaSerialRead;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import arduino.Arduino;

public class Main2 {
	
	static Logger _log = Logger.getGlobal();
	static final String SHM_ADDRESS_READ = "/dev/shm/serial2arduinoRead";
	static final String SHM_ADDRESS_WRITE = "/dev/shm/serial2arduinoWrite";
	static final String SHM_ADDRESS_READ_LOCK = "/dev/shm/serial2arduinoReadLock";
	static final String SHM_ADDRESS_WRITE_LOCK = "/dev/shm/serial2arduinoWriteLock";
	
	public static void main(String args[]) throws Exception {
		_log.info("Starting messageReader...");
		checkSHMFiles();
		
		
//		MessageQueue msgQueue = new MessageQueue(new ArrayList<String>());
//		MessageReaderThread writer = new MessageReaderThread(SHM_ADDRESS_READ, SHM_ADDRESS_READ_LOCK, SHM_ADDRESS_WRITE_LOCK, msgQueue );
//		Runtime.getRuntime().addShutdownHook(writer);
//		writer.start();
//		
		
		Arduino arduinoSerial = new Arduino("/dev/ttyUSB0", 500000);
		arduinoSerial.openConnection();
		while(true) {
			////////////////////// TO READ FROM ARDUINO AND SEND TO PHONE //////////////
			try {
			String readed = arduinoSerial.serialRead();
			System.out.println(readed);
			Thread.sleep(50);
			} catch (Exception e) {
				arduinoSerial.closeConnection();
			}
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

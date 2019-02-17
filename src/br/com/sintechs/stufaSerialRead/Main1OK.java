package br.com.sintechs.stufaSerialRead;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import com.fazecast.jSerialComm.SerialPort;

public class Main1OK {
	
	static Logger _log = Logger.getGlobal();
	static final String SHM_ADDRESS_READ = "/dev/shm/serial2arduinoRead";
	static final String SHM_ADDRESS_WRITE = "/dev/shm/serial2arduinoWrite";
	static final String SHM_ADDRESS_READ_LOCK = "/dev/shm/serial2arduinoReadLock";
	static final String SHM_ADDRESS_WRITE_LOCK = "/dev/shm/serial2arduinoWriteLock";
	
	public static void main(String args[]) throws Exception {
		_log.info("Starting messageReader...");
		checkSHMFiles();
		
		SerialPort comPort = SerialPort.getCommPorts()[0];
		comPort.setBaudRate(500000);
		comPort.openPort();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		InputStream in = comPort.getInputStream();
		try
		{
		   for (int j = 0; j < 56; ++j)
		      System.out.print((char)in.read());
		   in.close();
		} catch (Exception e) { e.printStackTrace(); }
		
		comPort.closePort();
		
		
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

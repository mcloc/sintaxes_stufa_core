package br.com.sintechs.stufaSerialRead;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fazecast.jSerialComm.SerialPort;

public class Main {

	static Logger _log = Logger.getGlobal();
	static final String SHM_ADDRESS_READ = "/dev/shm/serial2arduinoRead";
	static final String SHM_ADDRESS_WRITE = "/dev/shm/serial2arduinoWrite";
	static final String SHM_ADDRESS_READ_LOCK = "/dev/shm/serial2arduinoReadLock";
	static final String SHM_ADDRESS_WRITE_LOCK = "/dev/shm/serial2arduinoWriteLock";
	static boolean firstRun = true;
	
	static public void main(String[] args) throws Exception {
		_log.log(Level.INFO, "Starting Arduino Serial Reader...");
		
		StringBuilder data = new StringBuilder();
		SerialPort comPort;
		comPort = waitForPortConnection();
		comPort.openPort();
		comPort.setBaudRate(115200);
		_log.log(Level.INFO, "Communications started...");
		while(true) {
			try {
				readDataFromPort(comPort, data);
			} catch (ArrayIndexOutOfBoundsException e) {
				_log.log(Level.SEVERE, e.getMessage());
				comPort.closePort();
			}
			while (comPort.bytesAvailable() == -1) {
				if (!comPort.isOpen()) {
					comPort.closePort();
					comPort.openPort();
					comPort.setBaudRate(115200);
					_log.log(Level.INFO, "Communications restarted...");
				} else {
					comPort.closePort();
					_log.log(Level.SEVERE, "Communication lost, maybe device was disconected, waiting for port ttyUSB0 show up again...");
					comPort = waitForPortConnection();
//					throw new Exception("port is closed, check TIMEOUT, ha, there is no Timeout on this lib");
				}
			}
			Thread.sleep(20);
		} // END While(TRUE)
	}

	private static void readDataFromPort(SerialPort comPort, StringBuilder data) throws Exception {
			while (comPort.bytesAvailable() > 0) {
				byte[] readBuffer = new byte[comPort.bytesAvailable()];
				int totalReaded = comPort.readBytes(readBuffer, comPort.bytesAvailable());
				if (totalReaded != readBuffer.length)
					throw new Exception("index out of bound on readBytes (-1) returned, it's a comm problem");
				
				String s = new String(readBuffer, "UTF-8");
				if(s.contains("\n") || s.contains("\r") || s.contains("\t"))  {
					s.replace("\n", "");
					s.replace("\r", "");
					s.replace("\t", "");
					s.replaceAll("\\r$", "");
					data.append(s); 
					writeInSHMFile(data.toString());
					data.setLength(0);
					data = new StringBuilder();
					Thread.sleep(20);
					return;
				}
				data.append(s); 
			} // END of while(communication in)
			if(data.length() == 0)
				data = new StringBuilder();
	}

	private static SerialPort waitForPortConnection() throws InterruptedException {
		SerialPort[] conn = null;
		SerialPort comPort = null;
		boolean searchingPorts = true;
		
		while(searchingPorts) {
			
			conn = SerialPort.getCommPorts();
			if(conn == null || conn.length <= 0 ) {
				if(firstRun) {
					_log.log(Level.WARNING, "No ports were found. Check if the device is connected...");
					firstRun = false;
				}
				else
					_log.log(Level.FINER, "No ports were found. Check if the device is connected...");
				
				Thread.sleep(2000);
				continue;
			}
			
			for(int i=0;i< conn.length;i++) {
				comPort = SerialPort.getCommPorts()[i];
				if(comPort.getSystemPortName().equals("ttyUSB0")) {
					_log.log(Level.INFO, "Device connected and found on: " + comPort.getSystemPortName());
					searchingPorts = false;
					break;
				}
			}

			if(!searchingPorts)
				break;
			
			if(firstRun) {
				_log.log(Level.WARNING, "USB Device connected found but not ttyUSB0: " + comPort.getSystemPortName());
				firstRun = false;
			} else 
				_log.log(Level.FINEST, "USB Device connected found but not ttyUSB0: " + comPort.getSystemPortName());
			
			Thread.sleep(2000);
		}
		
		return comPort;
	}


	private static void writeInSHMFile(String s) {
		try (FileOutputStream file = new FileOutputStream(SHM_ADDRESS_READ, false)) {
//			file.write(("").getBytes());
			file.getChannel().position(0);
			s.replace("\n", "");
			s.replace("\r", "");
			s.replace("\t", "");
			s.replaceAll("\\r$", "");
			byte[] bytes = s.getBytes();
			// will trim the last 2 bytes because it is ^M
			byte[] tmp = new byte[bytes.length-2];
			System.arraycopy(bytes, 0, tmp, 0, bytes.length-2);
			file.write(tmp);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

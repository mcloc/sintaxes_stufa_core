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

	static public void main(String[] args) throws Exception {
		_log.log(Level.INFO, "Starting Arduino Serial Reader...");
		
		SerialPort[] conn = SerialPort.getCommPorts();
		
		if(conn == null || conn.length == 0 ) {
			throw new Exception("No serial port found...");
		}
		
		SerialPort comPort = SerialPort.getCommPorts()[0];
		comPort.openPort();
		comPort.setBaudRate(115200);

		while (comPort.bytesAvailable() == -1) {
			if (!comPort.isOpen()) {
				comPort.closePort();
				comPort.openPort();
				comPort.setBaudRate(115200);
			} else {
				throw new Exception("port is closed, check TIMEOUT, ha, there is no Timeout on this lib");
			}
			break;
		}
		_log.log(Level.INFO, "Communications started...");
		try {
			StringBuilder data = new StringBuilder();
			while (true) {
				while (comPort.bytesAvailable() > 0) {
					byte[] readBuffer = new byte[comPort.bytesAvailable()];
					try {
						int totalReaded = comPort.readBytes(readBuffer, comPort.bytesAvailable());
						if (totalReaded != readBuffer.length)
							throw new Exception("total readed diferen of avaliable");
					} catch (ArrayIndexOutOfBoundsException e) {
						_log.warning("index out of bound on readBytes (-1) returned, it's a comm problem");
						break;
					}
//					 System.out.println("total buffer: " + totalReaded);
					
					String s = new String(readBuffer, "UTF-8");
					
					if(s.contains("\n") || s.contains("\r") || s.contains("\t"))  {
						s.replace("\n", "");
						s.replace("\r", "");
						s.replace("\t", "");
						s.replaceAll("\\r$", "");
						data.append(s); 
//						System.out.println("string: " + data);
						writeInSHMFile(data.toString());
						data.setLength(0);
						data = new StringBuilder();
						break;
						//throw new Exception("tem \\n ");
					}

					data.append(s); 
					
				} // END of while(communication in)
				if(data.length() == 0)
					data = new StringBuilder();
				
				Thread.sleep(20);
			} // END of while(true)
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		comPort.closePort();
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

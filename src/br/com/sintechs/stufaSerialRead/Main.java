package br.com.sintechs.stufaSerialRead;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.fazecast.jSerialComm.SerialPort;

public class Main {

	static Logger _log = Logger.getGlobal();
	static final String SHM_ADDRESS_READ = "/dev/shm/serial2arduinoRead";
	static final String SHM_ADDRESS_WRITE = "/dev/shm/serial2arduinoWrite";
	static final String SHM_ADDRESS_READ_LOCK = "/dev/shm/serial2arduinoReadLock";
	static final String SHM_ADDRESS_WRITE_LOCK = "/dev/shm/serial2arduinoWriteLock";

	static public void main(String[] args) throws Exception {
		SerialPort comPort = SerialPort.getCommPorts()[0];
		comPort.openPort();
		comPort.setBaudRate(57600);

		while (comPort.bytesAvailable() == -1) {
			if (!comPort.isOpen()) {
				comPort.closePort();
				comPort.openPort();
				comPort.setBaudRate(57600);
			} else {
				throw new Exception("port is closed, check TIMEOUT");
			}
			break;
		}

		try {
			StringBuilder data = new StringBuilder();
			while (true) {
				
				while (comPort.bytesAvailable() > 0) {
					byte[] readBuffer = new byte[comPort.bytesAvailable()];
					int totalReaded = comPort.readBytes(readBuffer, comPort.bytesAvailable());
//					 System.out.println("total buffer: " + totalReaded);
					if (totalReaded != readBuffer.length)
						throw new Exception("total lido diferente do disponivel");
					String s = new String(readBuffer, "UTF-8");
					
					if(s.contains("\n"))  {
						data.append(s); 
						System.out.println("string: " + data);
						writeInSHMFile(data.toString());
						data = new StringBuilder();
						break;
						//throw new Exception("tem \\n ");
					}
//					s.replace("\n", "");
//					s.replace("\r", "");
//					s.replace("\t", "");
					data.append(s); 
					
				}
				if(data.length() == 0)
					data = new StringBuilder();
				else {
//					System.out.println(data);
//					writeInSHMFile(data.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		comPort.closePort();
	}

	private static void writeInSHMFile(String s) {
		 try {
			 try (FileChannel outChan = new FileOutputStream(SHM_ADDRESS_READ, true).getChannel()) {
			      outChan.truncate(0);
			    }
	            Files.write(Paths.get(SHM_ADDRESS_READ), s.getBytes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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

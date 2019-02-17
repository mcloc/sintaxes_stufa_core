package br.com.sintechs.stufaSerialRead;

import java.io.File;
import java.io.IOException;
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
		comPort.setBaudRate(19200);

		while (comPort.bytesAvailable() == -1) {
			if (!comPort.isOpen()) {
				comPort.closePort();
				comPort.openPort();
				comPort.setBaudRate(19200);
			} else {
				throw new Exception("port is closed, check TIMEOUT");
			}
			break;
		}

		try {
			while (true) {
				if (comPort.bytesAvailable() == 0)
					continue;
//				System.out.println("avaliable: " + comPort.bytesAvailable());
				byte[] readBuffer = new byte[comPort.bytesAvailable()];
				int totalReaded = comPort.readBytes(readBuffer, comPort.bytesAvailable());

				if (totalReaded != readBuffer.length)
					throw new Exception("total lido diferente do disponivel");
//				for (int i = 0; i < readBuffer.length; i++) {
					String s = new String(readBuffer, "UTF-8");
					System.out.println(s );
//				}
				// if(!s.contains("\n"))
				// continue;
				// if(s.length() > 0)
				// System.out.println("ZERO");
				// System.out.println("string: " + s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

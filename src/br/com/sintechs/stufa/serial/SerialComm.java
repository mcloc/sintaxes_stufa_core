package br.com.sintechs.stufa.serial;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fazecast.jSerialComm.SerialPort;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.drools.ExpertSystemHandler;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;
import br.com.sintechs.stufa.models.SintechsSampling;
import br.com.sintechs.stufa.rest.RESTClient;

public class SerialComm {
	private static final Logger LOGGER = LoggerFactory.getLogger(SerialComm.class);
	private GlobalProperties globalProperties;
	private IPCWriteInterrupt writeInterrupt;
	private ExpertSystemHandler drools;
	static boolean firstRun = true;
	
	public SerialComm(GlobalProperties globalProperties, IPCWriteInterrupt writeInterrupt, ExpertSystemHandler drools) {
		this.globalProperties = globalProperties;
		this.writeInterrupt = writeInterrupt;
		this.drools = drools;
	}

	protected void startSerialCommunication() throws Exception {
		StringBuilder data_readed = new StringBuilder();
		SerialPort comPort;
		comPort = waitForPortConnection();
		comPort.openPort();
		comPort.setBaudRate(globalProperties.getBAUD_RATE());
		LOGGER.info("Communications started...");
		
		while(true) {
			try {
				//Check if there is any messages to write TODO: refactor to event listener
				if(writeInterrupt.getMessagesCount() > 0 )
					wrtiteDataToPort(comPort,writeInterrupt.getMessage());
				
				// READ Data from SerialPort TODO: refactor this to event listener
				readDataFromPort(comPort, data_readed);
			} catch (ArrayIndexOutOfBoundsException e) {
				LOGGER.error(e.getMessage());
				comPort.closePort();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				comPort.closePort();
			}
			
			//bytesAvaliable() == -1 when nothing available or the communication has ceased (like unplugged device)
			while (comPort.bytesAvailable() == -1) {
				//If Port is closed, or defunct connection Reset it
				if (!comPort.isOpen()) {
					comPort.closePort();
					comPort.openPort();
					comPort.setBaudRate(globalProperties.getBAUD_RATE());
					LOGGER.debug("Communications restarted...");
				} else {
					comPort.closePort();
					LOGGER.error("Communication lost, maybe device was disconected, waiting for port ttyUSB0 show up again...");
					comPort = waitForPortConnection();
				}
			}
			Thread.sleep(20);
		} // END While(TRUE)
	}

	//TODO: refactor to event listener
	private  void wrtiteDataToPort(SerialPort comPort, String message) throws Exception {
		byte[] writeBuffer = message.getBytes("UTF-8");
		if(writeBuffer.length <= 0)
			throw new Exception("IPCWriteInterrupt message has lenght 0");
		
		comPort.writeBytes(writeBuffer, writeBuffer.length);
		
		//TODO: POG to waiting for response
		Thread.sleep(200);
	}


	//TODO: refactor to event listener
	private  void readDataFromPort(SerialPort comPort, StringBuilder data) throws Exception {
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
					RESTClient client = new RESTClient(data.toString(), globalProperties);
					BigInteger sampling_id = client.postSampling();
					if(sampling_id != null) {
						SintechsSampling sampling = client.getSampling(sampling_id);
						drools.addSampling(sampling);
					}
						
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

	/**
	 * waitForPortConnection(): loop for waiting ttyUSB0 be available (connected)
	 * @return
	 * @throws InterruptedException
	 */
	private  SerialPort waitForPortConnection() throws InterruptedException {
		SerialPort[] conn = null;
		SerialPort comPort = null;
		boolean searchingPorts = true;
		
		//While ttyUSB0 doesn't exists, keep looking forward
		while(searchingPorts) {
			conn = SerialPort.getCommPorts();
			if(conn == null || conn.length <= 0 ) {
				if(firstRun) {
					LOGGER.warn("No ports were found. Check if the device is connected...");
					firstRun = false;
				}
				else
					LOGGER.debug("No ports were found. Check if the device is connected...");
				
				Thread.sleep(500);
				continue;
			}
			
			for(int i=0;i< conn.length;i++) {
				comPort = SerialPort.getCommPorts()[i];
				
				//On Raspberry PI There are serial ports available besides ttyUSB0
				//So keep looking for ttyUSB0 since we plugging arduino with pi by USB Cable
				if(comPort.getSystemPortName().equals("ttyUSB0")) {
					LOGGER.info("Device connected and found on: " + comPort.getSystemPortName());
					searchingPorts = false;
					break;
				}
			}

			//Port found, break out the While LOOP
			if(!searchingPorts)
				break;
			
			if(firstRun) {
				LOGGER.warn("USB Device connected found but not ttyUSB0: " + comPort.getSystemPortName());
				firstRun = false;
			} else 
				LOGGER.debug("USB Device connected found but not ttyUSB0: " + comPort.getSystemPortName());
			
			Thread.sleep(500);
		}
		return comPort;
	}


	private  void writeInSHMFile(String s) throws Exception {
		try (FileOutputStream file = new FileOutputStream(globalProperties.getSHM_ADDRESS_READ(), false)) {
//			file.write(("").getBytes());
			file.getChannel().position(0);
			s.replace("\n", "");
			s.replace("\r", "");
			s.replace("\t", "");
			s.replaceAll("\\r$", "");
			byte[] bytes = s.getBytes();
			
			if(bytes.length <= 2)
				throw new Exception("message is corrupted: " + s);
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

package br.com.sintechs.stufa.serial;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.Main;
import br.com.sintechs.stufa.drools.ExpertSystemHandler;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;
import br.com.sintechs.stufa.models.SintechsSampling;

public class SerialCommunicationHandler extends Thread{

	private GlobalProperties globalProperties;
	private static final Logger LOGGER = LoggerFactory.getLogger(SerialCommunicationHandler.class);
	private IPCWriteInterrupt writeInterrupt;
	private ExpertSystemHandler drools;

	public SerialCommunicationHandler(GlobalProperties globalProperties, IPCWriteInterrupt writeInterrupt, ExpertSystemHandler es) {
		this.globalProperties = globalProperties;
		this.writeInterrupt = writeInterrupt;
		this.drools = es;
	}
	
	public void run() {
		SerialComm serial = new SerialComm(globalProperties, writeInterrupt, drools);
		
		//Never returns, this is embedded system. Auto crash recovery
		while(true) {
		
			try {
				//This is commented for TEST reason asked by Tirelli's to check drools package bad behavior 
//				serial.startSerialCommunication();
//				String json = "{\"module_name\":\"arduino_board#1\",\"data\":{\"sensors\":[{\"uuid\":\"DHT11#1\",\"value\":[{" + 
//						"\"humidity\":80},{\"temperature\":23.4},{\"heat_index\":23.8842}]}],\"actuators\":[{\"uui" + 
//						"d\":\"solenoid#1\",\"value\":{\"active\":\"false\",\"activated_time\":1}}]},\"status\":\"OK\"," + 
//						"uptime\":0,\"error_code\":\"\",\"error_msg\":\"\"}";
				
				
				String json = "{\"module_name\":\"arduino_climatization_board#1\",\"data\":{\"sensors\":[{\"uuid\":\"DHT11#1\",\"value\":[{\"humidity\":80},{\"temperature\":23.4},{\"heat_index\":23.8842}]}],\"actuators\":[{\"uuid\":\"solenoid#1\",\"value\":{\"active\":\"false\",\"activated_time\":1}}]},\"status\":\"OK\",\"uptime\":0,\"error_code\":\"\",\"error_msg\":\"\"}";

				
				
				if(json == null)
					throw new Exception("String json is null");
				JSONObject json_obj = new JSONObject(json);
				//Create Sampling with data readed from arduino_board 
				SintechsSampling sampling = new SintechsSampling(json_obj, globalProperties);
				drools.addSampling(sampling);
				
				Thread.sleep(800);
				continue;
				
				//TEST: this call is a test since the serial communication can not be done outside of the arduino's board site
//				serial.testDrools();
				
				//DEBUG: this will be removed after TESTING
//				Thread.sleep(1000);
				
				
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

}

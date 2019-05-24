package br.com.sintechs.stufa;

import java.util.HashMap;
import java.util.Map;

public class GlobalProperties {

	//TODO: getAllFrom database with rest request to web_admin
	private String SHM_ADDRESS_READ = "/dev/shm/serial2arduinoRead";
	private String SHM_ADDRESS_WRITE = "/dev/shm/serial2arduinoWrite";
	private String SHM_ADDRESS_READ_LOCK = "/dev/shm/serial2arduinoReadLock";
	private String SHM_ADDRESS_WRITE_LOCK = "/dev/shm/serial2arduinoWriteLock";
	private Integer BAUD_RATE = 115200;
	private Integer IPC_SERVER_PORT = 1932;
	private String REST_API_SERVER = "http://admin.sintechs.com.br:888";
	private String REST_API_STORE_SAMPLING_URL = REST_API_SERVER + "/api/storeSampling";
	private String REST_API_STORE_RULE_EVENT_URL = REST_API_SERVER + "/api/storeRuleEvent";
	private String REST_API_GET_SAMPLING_URL = REST_API_SERVER + "/api/getSampling";
	private String REST_API_GET_LAST_SENSOR_EVENT_URL = REST_API_SERVER + "/api/getLastSensorEvent";
	private String REST_API_GET_MODULE_ID_URL = REST_API_SERVER + "/api/getModuleId";
	private String REST_API_GET_ACTIVE_MODULES_URL = REST_API_SERVER + "/api/getActiveModules";
	private String REST_API_GET_SENSOR_ID_URL = REST_API_SERVER + "/api/getSensorId";
	private String REST_API_GET_SENSOR_BY_UUID_URL = REST_API_SERVER + "/api/getSensorByUUID";
	private String REST_API_GET_ACTUATOR_BY_UUID_URL = REST_API_SERVER + "/api/getActuatorByUUID";
	
	
	/**
	 * REST for Modules MOCK DATA 
	 */
	private String REST_API_GET_MODULE_SAPMLING_URL = REST_API_SERVER + "/api/getMockModuleSampling";
	
	
	
	/*
	 * DRL CONSTANTS
	 */
	private Map<String, Map<String, String>> DRL_CONSTANTS = new HashMap<String, Map<String,String>>(); 
	private Map<String, String> MAX_HEAT_INDEX = new HashMap<String, String>();
	private Map<String, String> MAX_TEMPERATURE = new HashMap<String, String>();
	private Map<String, String> MIN_HUMIDITY = new HashMap<String, String>();

	public GlobalProperties() {
		this.MAX_HEAT_INDEX.put("DHT11#1", "27");
		this.MAX_TEMPERATURE.put("DHT11#1", "26");
		this.MIN_HUMIDITY.put("DHT11#1", "77");
		this.MAX_HEAT_INDEX.put("DHT11#2", "27");
		this.MAX_TEMPERATURE.put("DHT11#2", "26");
		this.MIN_HUMIDITY.put("DHT11#2", "77");
		this.MAX_HEAT_INDEX.put("DHT11#3", "27");
		this.MAX_TEMPERATURE.put("DHT11#3", "26");
		this.MIN_HUMIDITY.put("DHT11#3", "77");
		this.MAX_HEAT_INDEX.put("DHT11#4", "27");
		this.MAX_TEMPERATURE.put("DHT11#4", "26");
		this.MIN_HUMIDITY.put("DHT11#4", "77");
		this.MAX_HEAT_INDEX.put("DHT11#5", "27");
		this.MAX_TEMPERATURE.put("DHT11#5", "26");
		this.MIN_HUMIDITY.put("DHT11#5", "77");
		this.MAX_HEAT_INDEX.put("DHT11#6", "27");
		this.MAX_TEMPERATURE.put("DHT11#6", "26");
		this.MIN_HUMIDITY.put("DHT11#6", "77");
		this.MAX_HEAT_INDEX.put("DHT11#7", "27");
		this.MAX_TEMPERATURE.put("DHT11#7", "26");
		this.MIN_HUMIDITY.put("DHT11#7", "77");
		this.MAX_HEAT_INDEX.put("DHT11#8", "27");
		this.MAX_TEMPERATURE.put("DHT11#8", "26");
		this.MIN_HUMIDITY.put("DHT11#8", "77");
		this.MAX_HEAT_INDEX.put("DHT11#9", "27");
		this.MAX_TEMPERATURE.put("DHT11#9", "26");
		this.MIN_HUMIDITY.put("DHT11#9", "77");
		
		this.MAX_HEAT_INDEX.put("DHT21#1", "27");
		this.MAX_TEMPERATURE.put("DHT21#1", "26");
		this.MIN_HUMIDITY.put("DHT21#1", "77");
		
		this.MIN_HUMIDITY.put("LM393#1", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#1", "23");
		this.MIN_HUMIDITY.put("LM393#2", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#2", "23");
		this.MIN_HUMIDITY.put("LM393#3", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#3", "23");
		this.MIN_HUMIDITY.put("LM393#4", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#4", "23");
		this.MIN_HUMIDITY.put("LM393#5", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#5", "23");
		this.MIN_HUMIDITY.put("LM393#6", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#6", "23");
		this.MIN_HUMIDITY.put("LM393#7", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#7", "23");
		this.MIN_HUMIDITY.put("LM393#8", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#8", "23");
		this.MIN_HUMIDITY.put("LM393#9", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#9", "23");
		this.MIN_HUMIDITY.put("LM393#10", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#10", "23");
		this.MIN_HUMIDITY.put("LM393#11", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#11", "23");
		this.MIN_HUMIDITY.put("LM393#11", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#11", "23");
		this.MIN_HUMIDITY.put("LM393#12", "86");
		this.MAX_TEMPERATURE.put("Ds18b20#12", "23");		
		
		
		this.DRL_CONSTANTS.put("MAX_HEAT_INDEX", MAX_HEAT_INDEX);
		this.DRL_CONSTANTS.put("MAX_TEMPERATURE", MAX_TEMPERATURE);
		this.DRL_CONSTANTS.put("MIN_HUMIDITY", MIN_HUMIDITY);
	}

	public String getREST_API_SERVER() {
		return REST_API_SERVER;
	}

	public void setREST_API_SERVER(String rEST_API_SERVER) {
		REST_API_SERVER = rEST_API_SERVER;
	}

	public String getREST_API_STORE_SAMPLING_URL() {
		return REST_API_STORE_SAMPLING_URL;
	}

	public void setREST_API_STORE_SAMPLING_URL(String rEST_API_STORE_SAMPLING_URL) {
		REST_API_STORE_SAMPLING_URL = rEST_API_STORE_SAMPLING_URL;
	}

	public String getSHM_ADDRESS_READ() {
		return SHM_ADDRESS_READ;
	}

	public void setSHM_ADDRESS_READ(String sHM_ADDRESS_READ) {
		SHM_ADDRESS_READ = sHM_ADDRESS_READ;
	}

	public String getSHM_ADDRESS_WRITE() {
		return SHM_ADDRESS_WRITE;
	}

	public void setSHM_ADDRESS_WRITE(String sHM_ADDRESS_WRITE) {
		SHM_ADDRESS_WRITE = sHM_ADDRESS_WRITE;
	}

	public String getSHM_ADDRESS_READ_LOCK() {
		return SHM_ADDRESS_READ_LOCK;
	}

	public void setSHM_ADDRESS_READ_LOCK(String sHM_ADDRESS_READ_LOCK) {
		SHM_ADDRESS_READ_LOCK = sHM_ADDRESS_READ_LOCK;
	}

	public String getSHM_ADDRESS_WRITE_LOCK() {
		return SHM_ADDRESS_WRITE_LOCK;
	}

	public void setSHM_ADDRESS_WRITE_LOCK(String sHM_ADDRESS_WRITE_LOCK) {
		SHM_ADDRESS_WRITE_LOCK = sHM_ADDRESS_WRITE_LOCK;
	}

	public Integer getBAUD_RATE() {
		return BAUD_RATE;
	}

	public void setBAUD_RATE(Integer bAUD_RATE) {
		BAUD_RATE = bAUD_RATE;
	}

	public Integer getIPC_SERVER_PORT() {
		return IPC_SERVER_PORT;
	}

	public void setIPC_SERVER_PORT(Integer iPC_SERVER_PORT) {
		IPC_SERVER_PORT = iPC_SERVER_PORT;
	}

	public String getREST_API_GET_SAMPLING_URL() {
		return REST_API_GET_SAMPLING_URL;
	}

	public void setREST_API_GET_SAMPLING_URL(String rEST_API_GET_SAMPLING_URL) {
		REST_API_GET_SAMPLING_URL = rEST_API_GET_SAMPLING_URL;
	}

	public Map<String, String> getMAX_HEAT_INDEX() {
		return MAX_HEAT_INDEX;
	}

	public void setMAX_HEAT_INDEX(Map<String, String> mAX_HEAT_INDEX) {
		MAX_HEAT_INDEX = mAX_HEAT_INDEX;
	}

	public Map<String, String> getMAX_TEMPERATURE() {
		return MAX_TEMPERATURE;
	}

	public void setMAX_TEMPERATURE(Map<String, String> mAX_TEMPERATURE) {
		MAX_TEMPERATURE = mAX_TEMPERATURE;
	}

	public Map<String, String> getMIN_HUMIDITY() {
		return MIN_HUMIDITY;
	}

	public void setMIN_HUMIDITY(Map<String, String> mIN_HUMIDITY) {
		MIN_HUMIDITY = mIN_HUMIDITY;
	}

	public Map<String, Map<String, String>> getDRL_CONSTANTS() {
		return DRL_CONSTANTS;
	}

	public void setDRL_CONSTANTS(Map<String, Map<String, String>> dRL_CONSTANTS) {
		DRL_CONSTANTS = dRL_CONSTANTS;
	}

	public String getREST_API_STORE_RULE_EVENT_URL() {
		return REST_API_STORE_RULE_EVENT_URL;
	}

	public void setREST_API_STORE_RULE_EVENT_URL(String rEST_API_STORE_RULE_EVENT_URL) {
		REST_API_STORE_RULE_EVENT_URL = rEST_API_STORE_RULE_EVENT_URL;
	}

	public String getREST_API_GET_LAST_SENSOR_EVENT_URL() {
		return REST_API_GET_LAST_SENSOR_EVENT_URL;
	}

	public void setREST_API_GET_LAST_SENSOR_EVENT_URL(String rEST_API_GET_LAST_SENSOR_EVENT_URL) {
		REST_API_GET_LAST_SENSOR_EVENT_URL = rEST_API_GET_LAST_SENSOR_EVENT_URL;
	}

	public String getREST_API_GET_MODULE_ID_URL() {
		return REST_API_GET_MODULE_ID_URL;
	}

	public void setREST_API_GET_MODULE_ID_URL(String rEST_API_GET_MODULE_ID_URL) {
		REST_API_GET_MODULE_ID_URL = rEST_API_GET_MODULE_ID_URL;
	}

	public String getREST_API_GET_SENSOR_ID_URL() {
		return REST_API_GET_SENSOR_ID_URL;
	}

	public void setREST_API_GET_SENSOR_ID_URL(String rEST_API_GET_SENSOR_ID_URL) {
		REST_API_GET_SENSOR_ID_URL = rEST_API_GET_SENSOR_ID_URL;
	}

	public String getREST_API_GET_SENSOR_BY_UUID_URL() {
		return REST_API_GET_SENSOR_BY_UUID_URL;
	}

	public void setREST_API_GET_SENSOR_BY_UUID_URL(String rEST_API_GET_SENSOR_BY_UUID_URL) {
		REST_API_GET_SENSOR_BY_UUID_URL = rEST_API_GET_SENSOR_BY_UUID_URL;
	}

	public String getREST_API_GET_ACTUATOR_BY_UUID_URL() {
		return REST_API_GET_ACTUATOR_BY_UUID_URL;
	}

	public void setREST_API_GET_ACTUATOR_BY_UUID_URL(String rEST_API_GET_ACTUATOR_BY_UUID_URL) {
		REST_API_GET_ACTUATOR_BY_UUID_URL = rEST_API_GET_ACTUATOR_BY_UUID_URL;
	}

	public String getREST_API_GET_ACTIVE_MODULES_URL() {
		return REST_API_GET_ACTIVE_MODULES_URL;
	}

	public void setREST_API_GET_ACTIVE_MODULES_URL(String rEST_API_GET_ACTIVE_MODULES_URL) {
		REST_API_GET_ACTIVE_MODULES_URL = rEST_API_GET_ACTIVE_MODULES_URL;
	}

	public String getREST_API_GET_MODULE_SAPMLING_URL() {
		return REST_API_GET_MODULE_SAPMLING_URL;
	}

	public void setREST_API_GET_MODULE_SAPMLING_URL(String rEST_API_GET_MODULE_SAPMLING_URL) {
		REST_API_GET_MODULE_SAPMLING_URL = rEST_API_GET_MODULE_SAPMLING_URL;
	}

	

}

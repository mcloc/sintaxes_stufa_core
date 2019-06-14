package br.com.sintechs.stufa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalProperties {

	// TODO: getAllFrom database with rest request to web_admin
	private String SHM_ADDRESS_READ = "/dev/shm/serial2arduinoRead";
	private String SHM_ADDRESS_WRITE = "/dev/shm/serial2arduinoWrite";
	private String SHM_ADDRESS_READ_LOCK = "/dev/shm/serial2arduinoReadLock";
	private String SHM_ADDRESS_WRITE_LOCK = "/dev/shm/serial2arduinoWriteLock";
	private Integer BAUD_RATE = 115200;
	private Integer IPC_SERVER_PORT = 1932;
	private Integer REST_RESPONSE_SLEEP = 600;
	private Integer REST_429_SLEEP = 4500;
	private Integer REST_MODULE_SLEEP = 2500;
	private Integer TOTAL_CLIMATIZATION_BOARDS = 3;
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
	private Map<String, Map<String, String>> DRL_CONSTANTS = new HashMap<String, Map<String, String>>();
	private Map<String, String> MAX_HEAT_INDEX = new HashMap<String, String>();
	private Map<String, String> MAX_TEMPERATURE = new HashMap<String, String>();
	private Map<String, String> MIN_HUMIDITY = new HashMap<String, String>();
	private List<String> CLIMATIZATION_MODULES = new ArrayList<String>();
	private List<String> SOIL_MODULES = new ArrayList<String>();
	private Map<String, List<String>> CLIMATIZATION_MODULES_SENSORS = new HashMap<String, List<String>>();
	private Map<String, List<String>> CLIMATIZATION_MODULES_ACTUATORS = new HashMap<String, List<String>>();
	private Map<String, List<String>> SOIL_MODULES_SENSORS = new HashMap<String, List<String>>();
	private Map<String, List<String>> CLIMATIZATION_SENSORS_MEASURE_TYPES = new HashMap<String, List<String>>();
	private Map<String, List<String>> SOIL_SENSORS_MEASURE_TYPES = new HashMap<String, List<String>>();

	public GlobalProperties() {
		this.CLIMATIZATION_MODULES.add("arduino_climatization_board#1");
//		this.CLIMATIZATION_MODULES.add("arduino_climatization_board#2");
//		this.CLIMATIZATION_MODULES.add("arduino_climatization_board#3");

		this.SOIL_MODULES.add("arduino_soil_board#1");
		this.SOIL_MODULES.add("arduino_soil_board#2");
		this.SOIL_MODULES.add("arduino_soil_board#3");
		this.SOIL_MODULES.add("arduino_soil_board#4");

		List<String> tmp_list = new ArrayList<String>();
		tmp_list.add("DHT11#1");
		tmp_list.add("DHT11#2");
		tmp_list.add("DHT11#3");
		this.CLIMATIZATION_MODULES_SENSORS.put("arduino_climatization_board#1", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("DHT11#4");
		tmp_list.add("DHT11#5");
		tmp_list.add("DHT11#6");
//		tmp_list.add("DHT11#7");
		this.CLIMATIZATION_MODULES_SENSORS.put("arduino_climatization_board#2", tmp_list);
//		tmp_list = new ArrayList<String>();
//		tmp_list.add("DHT11#7");
//		tmp_list.add("DHT11#8");
//		tmp_list.add("DHT11#9");
//		this.CLIMATIZATION_MODULES_SENSORS.put("arduino_climatization_board#3", tmp_list);
		// this.CLIMATIZATION_MODULES_SENSORS.put("arduino_external_climatization_board#1",
		// "DHT21#1");
		tmp_list = new ArrayList<String>();
		tmp_list.add("DN20#1");
		this.CLIMATIZATION_MODULES_ACTUATORS.put("arduino_climatization_board#1", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("DN20#2");
		this.CLIMATIZATION_MODULES_ACTUATORS.put("arduino_climatization_board#2", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("DN20#3");
		this.CLIMATIZATION_MODULES_ACTUATORS.put("arduino_climatization_board#3", tmp_list);
		

		tmp_list = new ArrayList<String>();
		tmp_list.add("LM393#1");
		tmp_list.add("Ds18b20#1");
		this.SOIL_MODULES_SENSORS.put("arduino_soil_board#1", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("LM393#2");
		tmp_list.add("Ds18b20#2");
		this.SOIL_MODULES_SENSORS.put("arduino_soil_board#2", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("LM393#3");
		tmp_list.add("Ds18b20#3");
		this.SOIL_MODULES_SENSORS.put("arduino_soil_board#3", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("LM393#4");
		tmp_list.add("Ds18b20#4");
		this.SOIL_MODULES_SENSORS.put("arduino_soil_board#4", tmp_list);
		
		
		
		
		tmp_list = new ArrayList<String>();
		tmp_list.add("heat_index");
		tmp_list.add("temperature");
		tmp_list.add("humidity");
		this.CLIMATIZATION_SENSORS_MEASURE_TYPES.put("DHT11#1", tmp_list);
		this.CLIMATIZATION_SENSORS_MEASURE_TYPES.put("DHT11#2", tmp_list);
		this.CLIMATIZATION_SENSORS_MEASURE_TYPES.put("DHT11#3", tmp_list);
		this.CLIMATIZATION_SENSORS_MEASURE_TYPES.put("DHT11#4", tmp_list);
		this.CLIMATIZATION_SENSORS_MEASURE_TYPES.put("DHT11#5", tmp_list);
		this.CLIMATIZATION_SENSORS_MEASURE_TYPES.put("DHT11#6", tmp_list);
//		this.CLIMATIZATION_SENSORS_MEASURE_TYPES.put("DHT11#7", tmp_list);
//		this.CLIMATIZATION_SENSORS_MEASURE_TYPES.put("DHT11#8", tmp_list);
//		this.CLIMATIZATION_SENSORS_MEASURE_TYPES.put("DHT11#9", tmp_list);

		
		
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#1", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#1", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#2", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#2", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#3", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#3", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#4", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#4", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#5", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#5", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#6", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#6", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#7", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#7", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#8", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#8", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#9", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#9", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#10", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#10", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#11", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#11", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("humidity");
		this.SOIL_SENSORS_MEASURE_TYPES.put("LM393#12", tmp_list);
		tmp_list = new ArrayList<String>();
		tmp_list.add("temperature");
		this.SOIL_SENSORS_MEASURE_TYPES.put("Ds18b20#12", tmp_list);

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

	public Integer getREST_RESPONSE_SLEEP() {
		return REST_RESPONSE_SLEEP;
	}

	public void setREST_RESPONSE_SLEEP(Integer rEST_RESPONSE_SLEEP) {
		REST_RESPONSE_SLEEP = rEST_RESPONSE_SLEEP;
	}

	public Integer getREST_429_SLEEP() {
		return REST_429_SLEEP;
	}

	public void setREST_429_SLEEP(Integer rEST_409_SLEEP) {
		REST_429_SLEEP = rEST_409_SLEEP;
	}

	public Integer getREST_MODULE_SLEEP() {
		return REST_MODULE_SLEEP;
	}

	public void setREST_MODULE_SLEEP(Integer rEST_MODULE_SLEEP) {
		REST_MODULE_SLEEP = rEST_MODULE_SLEEP;
	}

	public List<String> getCLIMATIZATION_MODULES() {
		return CLIMATIZATION_MODULES;
	}

	public void setCLIMATIZATION_MODULES(List<String> cLIMATIZATION_MODULES) {
		CLIMATIZATION_MODULES = cLIMATIZATION_MODULES;
	}

	public List<String> getSOIL_MODULES() {
		return SOIL_MODULES;
	}

	public void setSOIL_MODULES(List<String> sOIL_MODULES) {
		SOIL_MODULES = sOIL_MODULES;
	}

	public Map<String, List<String>> getCLIMATIZATION_MODULES_SENSORS() {
		return CLIMATIZATION_MODULES_SENSORS;
	}

	public void setCLIMATIZATION_MODULES_SENSORS(Map<String, List<String>> cLIMATIZATION_MODULES_SENSORS) {
		CLIMATIZATION_MODULES_SENSORS = cLIMATIZATION_MODULES_SENSORS;
	}

	public Map<String, List<String>> getSOIL_MODULES_SENSORS() {
		return SOIL_MODULES_SENSORS;
	}

	public void setSOIL_MODULES_SENSORS(Map<String, List<String>> sOIL_MODULES_SENSORS) {
		SOIL_MODULES_SENSORS = sOIL_MODULES_SENSORS;
	}

	public Map<String, List<String>> getCLIMATIZATION_SENSORS_MEASURE_TYPES() {
		return CLIMATIZATION_SENSORS_MEASURE_TYPES;
	}

	public void setCLIMATIZATION_SENSORS_MEASURE_TYPES(Map<String, List<String>> cLIMATIZATION_SENSORS_MEASURE_TYPES) {
		CLIMATIZATION_SENSORS_MEASURE_TYPES = cLIMATIZATION_SENSORS_MEASURE_TYPES;
	}

	public Map<String, List<String>> getSOIL_SENSORS_MEASURE_TYPES() {
		return SOIL_SENSORS_MEASURE_TYPES;
	}

	public void setSOIL_SENSORS_MEASURE_TYPES(Map<String, List<String>> sOIL_SENSORS_MEASURE_TYPES) {
		SOIL_SENSORS_MEASURE_TYPES = sOIL_SENSORS_MEASURE_TYPES;
	}

	public Map<String, List<String>> getCLIMATIZATION_MODULES_ACTUATORS() {
		return CLIMATIZATION_MODULES_ACTUATORS;
	}

	public void setCLIMATIZATION_MODULES_ACTUATORS(Map<String, List<String>> cLIMATIZATION_MODULES_ACTUATORS) {
		CLIMATIZATION_MODULES_ACTUATORS = cLIMATIZATION_MODULES_ACTUATORS;
	}


}

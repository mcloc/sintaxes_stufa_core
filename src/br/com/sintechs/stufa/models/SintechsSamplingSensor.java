package br.com.sintechs.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;

public class SintechsSamplingSensor  implements Serializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(SintechsSamplingSensor.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 3262367160946774959L;
	private BigInteger sampling_id;
//	private BigInteger sensor_id;
	private SintechsSensor sensor;
	private String measure_type;
	private Float value;
	private Timestamp created_at;
	private Timestamp updated_at;
	private Float rule_condition;
	
	private GlobalProperties globalProperties;
	
	
	public SintechsSamplingSensor(JSONObject sampling_sensor_obj, GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
		boolean NEW_OBJECT = false;
		try {
			this.sampling_id = sampling_sensor_obj.getBigInteger("sampling_id");
		} catch (Exception e) {
			NEW_OBJECT = true;
			LOGGER.debug("new object, it ins't on DB yet");
		}
		
		
		this.measure_type = sampling_sensor_obj.getString("measure_type");
		this.value = sampling_sensor_obj.getFloat("value");
		this.created_at = Timestamp.valueOf(sampling_sensor_obj.getString("created_at"));
		this.updated_at = Timestamp.valueOf(sampling_sensor_obj.getString("updated_at"));
		
		JSONArray sensor_arr = sampling_sensor_obj.getJSONArray("sensor");
		JSONObject sensor = (JSONObject) sensor_arr.get(0);
		
		this.sensor = new SintechsSensor(sensor);
		switch(this.measure_type) {
		case "heat_index":
			this.rule_condition = Float.parseFloat(globalProperties.getDRL_CONSTANTS().get("MAX_HEAT_INDEX").get(this.sensor.getUuid()));
			break;
		case "temperature":
			this.rule_condition = Float.parseFloat(globalProperties.getDRL_CONSTANTS().get("MAX_TEMPERATURE").get(this.sensor.getUuid()));
			break;
		case "humidity":
			this.rule_condition = Float.parseFloat(globalProperties.getDRL_CONSTANTS().get("MIN_HUMIDITY").get(this.sensor.getUuid()));
			break;
		}
		
		
	}
	
	public SintechsSamplingSensor( GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}
	
	
	public BigInteger getSampling_id() {
		return sampling_id;
	}
	public void setSampling_id(BigInteger sampling_id) {
		this.sampling_id = sampling_id;
	}
	public SintechsSensor getSensor() {
		return sensor;
	}
	public void setSensor(SintechsSensor sensor) {
		this.sensor = sensor;
	}
	public String getMeasure_type() {
		return measure_type;
	}
	public void setMeasure_type(String measure_type) {
		this.measure_type = measure_type;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Float getRule_condition() {
		return rule_condition;
	}
	public void setRule_condition(Float rule_condition) {
		this.rule_condition = rule_condition;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
//	public BigInteger getSensor_id() {
//		return sensor_id;
//	}
//	public void setSensor_id(BigInteger sensor_id) {
//		this.sensor_id = sensor_id;
//	}

	public void setRule_condition(String measure_type) {
		switch(measure_type) {
		case "heat_index":
			this.rule_condition = Float.parseFloat(globalProperties.getDRL_CONSTANTS().get("MAX_HEAT_INDEX").get(this.sensor.getUuid()));
			break;
		case "temperature":
			this.rule_condition = Float.parseFloat(globalProperties.getDRL_CONSTANTS().get("MAX_TEMPERATURE").get(this.sensor.getUuid()));
			break;
		case "humidity":
			this.rule_condition = Float.parseFloat(globalProperties.getDRL_CONSTANTS().get("MIN_HUMIDITY").get(this.sensor.getUuid()));
			break;
		}
		
	}
	
	
	
}

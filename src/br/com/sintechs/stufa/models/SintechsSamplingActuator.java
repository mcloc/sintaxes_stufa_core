package br.com.sintechs.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.rest.RESTClient;

public class SintechsSamplingActuator  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3962325801608052778L;
	private BigInteger sampling_id;
	private SintechsActuator actuator;
	private boolean active;
	private BigInteger activated_time;
	private Timestamp created_at;
	private Timestamp updated_at;
	private GlobalProperties globalProperties;
	
	/**
	 * Hidrate SamplingActuator from json retrived from database
	 * @param sampling_actuator_obj
	 */
//	public SintechsSamplingActuator(JSONObject sampling_actuator_obj) {
//		this.sampling_id = sampling_actuator_obj.getBigInteger("sampling_id");
//		this.active = "1".equals(sampling_actuator_obj.getString("active"));
//		this.activated_time = sampling_actuator_obj.getBigInteger("activated_time");
//		this.created_at = Timestamp.valueOf(sampling_actuator_obj.getString("created_at"));
//		this.updated_at = Timestamp.valueOf(sampling_actuator_obj.getString("updated_at"));
//		
//		
//		JSONArray actuator_arr = sampling_actuator_obj.getJSONArray("actuator");
//		JSONObject actuator = (JSONObject) actuator_arr.get(0);
//		this.actuator = new SintechsActuator(actuator);
//	}
	
	/**
	 * Hidrate new SamplingActuator from Event 
	 * @param actuator_uuid
	 * @param command_value
	 * @param globalProperties 
	 * @param timestamp 
	 */
//	public SintechsSamplingActuator(String actuator_uuid, boolean command_value, Timestamp created_at, GlobalProperties globalProperties) {
//		this.active = command_value;
//		this.activated_time = new BigInteger("0");
//		this.created_at = created_at;
//		this.updated_at = created_at;
//		
//		RESTClient client = new RESTClient(globalProperties);
//		JSONObject actuator_json_obj = client.getActuatorByUUID(actuator_uuid);
//		
//		//TODO: check for errors
//		SintechsActuator sintechs_actuator = new SintechsActuator(actuator_json_obj.getJSONObject("data"));
//		this.actuator = sintechs_actuator;
//	}

	public SintechsSamplingActuator(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	public BigInteger getSampling_id() {
		return sampling_id;
	}
	public void setSampling_id(BigInteger sampling_id) {
		this.sampling_id = sampling_id;
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
	public SintechsActuator getActuator() {
		return actuator;
	}
	public void setActuator(SintechsActuator actuator) {
		this.actuator = actuator;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public BigInteger getActivated_time() {
		return activated_time;
	}
	public void setActivated_time(BigInteger activated_time) {
		this.activated_time = activated_time;
	}

	
	
}

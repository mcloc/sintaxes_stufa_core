package br.com.sintechs.stufa.models;

import java.math.BigInteger;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;

public class SintechsSamplingActuator {
	
	private BigInteger sampling_id;
	private SintechsActuator actuator;
	private boolean active;
	private BigInteger activated_time;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	
	public SintechsSamplingActuator(JSONObject sampling_actuator_obj) {
		this.sampling_id = sampling_actuator_obj.getBigInteger("sampling_id");
		this.active = "1".equals(sampling_actuator_obj.getString("active"));
		this.activated_time = sampling_actuator_obj.getBigInteger("activated_time");
		this.created_at = Timestamp.valueOf(sampling_actuator_obj.getString("created_at"));
		this.updated_at = Timestamp.valueOf(sampling_actuator_obj.getString("updated_at"));
		
		
		JSONArray actuator_arr = sampling_actuator_obj.getJSONArray("actuator");
		JSONObject actuator = (JSONObject) actuator_arr.get(0);
		this.actuator = new SintechsActuator(actuator);
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

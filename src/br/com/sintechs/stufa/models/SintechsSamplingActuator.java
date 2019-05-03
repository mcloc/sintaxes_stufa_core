package br.com.sintechs.stufa.models;

import java.math.BigInteger;
import java.sql.Timestamp;

public class SintechsSamplingActuator {
	
	private BigInteger sampling_id;
	private SintechsActuator actuator;
	private boolean active;
	private BigInteger activated_time;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	
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

package br.com.sintechs.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.rest.RESTClient;

public class SintechsSamplingActuator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3962325801608052778L;
	private BigInteger sampling_id;
	private SintechsModule module;
	private SintechsActuator actuator;
	private boolean active;
	private BigInteger activated_time;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	private GlobalProperties globalProperties;

	/**
	 * Hidrate SamplingActuator from json retrived from database
	 * 
	 * @param sampling_actuator_obj
	 */
	// public SintechsSamplingActuator(JSONObject sampling_actuator_obj) {
	// this.sampling_id = sampling_actuator_obj.getBigInteger("sampling_id");
	// this.active = "1".equals(sampling_actuator_obj.getString("active"));
	// this.activated_time = sampling_actuator_obj.getBigInteger("activated_time");
	// this.created_at =
	// Timestamp.valueOf(sampling_actuator_obj.getString("created_at"));
	// this.updated_at =
	// Timestamp.valueOf(sampling_actuator_obj.getString("updated_at"));
	//
	//
	// JSONArray actuator_arr = sampling_actuator_obj.getJSONArray("actuator");
	// JSONObject actuator = (JSONObject) actuator_arr.get(0);
	// this.actuator = new SintechsActuator(actuator);
	// }

	/**
	 * Hidrate new SamplingActuator from Event
	 * 
	 * @param actuator_uuid
	 * @param command_value
	 * @param globalProperties
	 * @param timestamp
	 */
	// public SintechsSamplingActuator(String actuator_uuid, boolean command_value,
	// Timestamp created_at, GlobalProperties globalProperties) {
	// this.active = command_value;
	// this.activated_time = new BigInteger("0");
	// this.created_at = created_at;
	// this.updated_at = created_at;
	//
	// RESTClient client = new RESTClient(globalProperties);
	// JSONObject actuator_json_obj = client.getActuatorByUUID(actuator_uuid);
	//
	// //TODO: check for errors
	// SintechsActuator sintechs_actuator = new
	// SintechsActuator(actuator_json_obj.getJSONObject("data"));
	// this.actuator = sintechs_actuator;
	// }

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

	public SintechsModule getModule() {
		return module;
	}

	public void setModule(SintechsModule module) {
		this.module = module;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activated_time == null) ? 0 : activated_time.hashCode());
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((actuator == null) ? 0 : actuator.hashCode());
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((sampling_id == null) ? 0 : sampling_id.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SintechsSamplingActuator other = (SintechsSamplingActuator) obj;
		if (activated_time == null) {
			if (other.activated_time != null)
				return false;
		} else if (!activated_time.equals(other.activated_time))
			return false;
		if (active != other.active)
			return false;
		if (actuator == null) {
			if (other.actuator != null)
				return false;
		} else if (!actuator.equals(other.actuator))
			return false;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (sampling_id == null) {
			if (other.sampling_id != null)
				return false;
		} else if (!sampling_id.equals(other.sampling_id))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		return true;
	}

}

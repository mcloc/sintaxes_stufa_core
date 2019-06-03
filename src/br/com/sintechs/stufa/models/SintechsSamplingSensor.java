package br.com.sintechs.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;

public class SintechsSamplingSensor implements Serializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(SintechsSamplingSensor.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 3262367160946774959L;
	private Timestamp sampling_uuid;
	private SintechsSensor sensor;
	private SintechsModule module;
	private String measure_type;
	private Float value;
	private Timestamp created_at;
	private Timestamp updated_at;
	private Float rule_condition;

	private GlobalProperties globalProperties;

	public SintechsSamplingSensor(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	public void setRule_condition(String measure_type) {
		switch (measure_type) {
		case "heat_index":
			this.rule_condition = Float
					.parseFloat(globalProperties.getDRL_CONSTANTS().get("MAX_HEAT_INDEX").get(this.sensor.getUuid()));
			break;
		case "temperature":
			this.rule_condition = Float
					.parseFloat(globalProperties.getDRL_CONSTANTS().get("MAX_TEMPERATURE").get(this.sensor.getUuid()));
			break;
		case "humidity":
			this.rule_condition = Float
					.parseFloat(globalProperties.getDRL_CONSTANTS().get("MIN_HUMIDITY").get(this.sensor.getUuid()));
			break;
		}

	}

	public Timestamp getSampling_uuid() {
		return sampling_uuid;
	}

	public void setSampling_uuid(Timestamp sampling_uuid) {
		this.sampling_uuid = sampling_uuid;
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
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((measure_type == null) ? 0 : measure_type.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((rule_condition == null) ? 0 : rule_condition.hashCode());
		result = prime * result + ((sampling_uuid == null) ? 0 : sampling_uuid.hashCode());
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		SintechsSamplingSensor other = (SintechsSamplingSensor) obj;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (measure_type == null) {
			if (other.measure_type != null)
				return false;
		} else if (!measure_type.equals(other.measure_type))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (rule_condition == null) {
			if (other.rule_condition != null)
				return false;
		} else if (!rule_condition.equals(other.rule_condition))
			return false;
		if (sampling_uuid == null) {
			if (other.sampling_uuid != null)
				return false;
		} else if (!sampling_uuid.equals(other.sampling_uuid))
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}

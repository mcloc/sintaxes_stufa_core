package br.com.vger.stufa.models;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClimatizationEvent {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClimatizationEvent.class);

	private VgerModule module;
	private VgerSampling sampling;
	private String sensor_uuid;
	private String actuator_uuid;
	private Boolean actuator_active;
	private BigInteger actuator_activated_time;
	private Float heat_index;
	private Float temperature;
	private Float humidity;
	private Float rule_condition_heat_index;
	private Float rule_condition_temperature;
	private Float rule_condition_humidity;
	private String tmp_measure_type;

	public ClimatizationEvent(VgerModule module, VgerSampling sampling, String sensor_uuid, String measure_type,
			Float value, Float rule_condition) {
		this.module = module;
		this.sampling = sampling;
		this.sensor_uuid = sensor_uuid;
		this.actuator_uuid = null;
		this.tmp_measure_type = measure_type;
		switch (measure_type) {
		case "heat_index":
			this.heat_index = value;
			this.rule_condition_heat_index = rule_condition;
			break;
		case "temperature":
			this.temperature = value;
			this.rule_condition_temperature = rule_condition;
			break;
		case "humidity":
			this.humidity = value;
			this.rule_condition_humidity = rule_condition;
			break;
		}
	}
	
	public ClimatizationEvent(VgerModule module, VgerSampling sampling, String actuator_uuid, Boolean active, 
			BigInteger activated_time) {
		this.module = module;
		this.sampling = sampling;
		this.sensor_uuid = null;
		this.actuator_uuid = actuator_uuid;
		this.actuator_active = active;
		this.actuator_activated_time = activated_time;
		
	}

	public VgerModule getModule() {
		return module;
	}

	public void setModule(VgerModule module) {
		this.module = module;
	}

	public VgerSampling getSampling() {
		return sampling;
	}

	public void setSampling(VgerSampling sampling) {
		this.sampling = sampling;
	}

	public String getSensor_uuid() {
		return sensor_uuid;
	}

	public void setSensor_uuid(String sensor_uuid) {
		this.sensor_uuid = sensor_uuid;
	}

	public Float getHeat_index() {
		return heat_index;
	}

	public void setHeat_index(Float heat_index) {
		this.heat_index = heat_index;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Float getHumidity() {
		return humidity;
	}

	public void setHumidity(Float humidity) {
		this.humidity = humidity;
	}

	public String getTmp_measure_type() {
		return tmp_measure_type;
	}

	public void setTmp_measure_type(String tmp_measure_type) {
		this.tmp_measure_type = tmp_measure_type;
	}

	public Float getRule_condition_heat_index() {
		return rule_condition_heat_index;
	}

	public void setRule_condition_heat_index(Float rule_condition_heat_index) {
		this.rule_condition_heat_index = rule_condition_heat_index;
	}

	public Float getRule_condition_temperature() {
		return rule_condition_temperature;
	}

	public void setRule_condition_temperature(Float rule_condition_temperature) {
		this.rule_condition_temperature = rule_condition_temperature;
	}

	public Float getRule_condition_humidity() {
		return rule_condition_humidity;
	}

	public void setRule_condition_humidity(Float rule_condition_humidity) {
		this.rule_condition_humidity = rule_condition_humidity;
	}

	public String getActuator_uuid() {
		return actuator_uuid;
	}

	public void setActuator_uuid(String actuator_uuid) {
		this.actuator_uuid = actuator_uuid;
	}

	public Boolean getActuator_active() {
		return actuator_active;
	}

	public void setActuator_active(Boolean actuator_active) {
		this.actuator_active = actuator_active;
	}

	public BigInteger getActuator_activated_time() {
		return actuator_activated_time;
	}

	public void setActuator_activated_time(BigInteger actuator_activated_time) {
		this.actuator_activated_time = actuator_activated_time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actuator_activated_time == null) ? 0 : actuator_activated_time.hashCode());
		result = prime * result + ((actuator_active == null) ? 0 : actuator_active.hashCode());
		result = prime * result + ((actuator_uuid == null) ? 0 : actuator_uuid.hashCode());
		result = prime * result + ((heat_index == null) ? 0 : heat_index.hashCode());
		result = prime * result + ((humidity == null) ? 0 : humidity.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((rule_condition_heat_index == null) ? 0 : rule_condition_heat_index.hashCode());
		result = prime * result + ((rule_condition_humidity == null) ? 0 : rule_condition_humidity.hashCode());
		result = prime * result + ((rule_condition_temperature == null) ? 0 : rule_condition_temperature.hashCode());
		result = prime * result + ((sampling == null) ? 0 : sampling.hashCode());
		result = prime * result + ((sensor_uuid == null) ? 0 : sensor_uuid.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
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
		ClimatizationEvent other = (ClimatizationEvent) obj;
		if (actuator_activated_time == null) {
			if (other.actuator_activated_time != null)
				return false;
		} else if (!actuator_activated_time.equals(other.actuator_activated_time))
			return false;
		if (actuator_active == null) {
			if (other.actuator_active != null)
				return false;
		} else if (!actuator_active.equals(other.actuator_active))
			return false;
		if (actuator_uuid == null) {
			if (other.actuator_uuid != null)
				return false;
		} else if (!actuator_uuid.equals(other.actuator_uuid))
			return false;
		if (heat_index == null) {
			if (other.heat_index != null)
				return false;
		} else if (!heat_index.equals(other.heat_index))
			return false;
		if (humidity == null) {
			if (other.humidity != null)
				return false;
		} else if (!humidity.equals(other.humidity))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (rule_condition_heat_index == null) {
			if (other.rule_condition_heat_index != null)
				return false;
		} else if (!rule_condition_heat_index.equals(other.rule_condition_heat_index))
			return false;
		if (rule_condition_humidity == null) {
			if (other.rule_condition_humidity != null)
				return false;
		} else if (!rule_condition_humidity.equals(other.rule_condition_humidity))
			return false;
		if (rule_condition_temperature == null) {
			if (other.rule_condition_temperature != null)
				return false;
		} else if (!rule_condition_temperature.equals(other.rule_condition_temperature))
			return false;
		if (sampling == null) {
			if (other.sampling != null)
				return false;
		} else if (!sampling.equals(other.sampling))
			return false;
		if (sensor_uuid == null) {
			if (other.sensor_uuid != null)
				return false;
		} else if (!sensor_uuid.equals(other.sensor_uuid))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		return true;
	}


}

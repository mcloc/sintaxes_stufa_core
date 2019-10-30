package br.com.vger.stufa.customer_models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.vger.stufa.models.VgerModule;
import br.com.vger.stufa.models.VgerSampling;

public class ClimatizationModuleData {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClimatizationModuleData.class);

	private Float climatization_module_1_sensor_1_heat_index;
	private Float climatization_module_1_sensor_2_heat_index;
	private Float climatization_module_1_sensor_3_heat_index;
	private Float climatization_module_2_sensor_1_heat_index;
	private Float climatization_module_2_sensor_2_heat_index;
	private Float climatization_module_2_sensor_3_heat_index;
	private Float climatization_module_3_sensor_1_heat_index;
	private Float climatization_module_3_sensor_2_heat_index;
	private Float climatization_module_3_sensor_3_heat_index;
	
	//////////////////////////////////////////////////////////////////////////
	private VgerModule module;
	private VgerSampling sampling;
	private String sensor_uuid;
	private Float heat_index;
	private Float temperature;
	private Float humidity;
	private Float rule_condition_heat_index;
	private Float rule_condition_temperature;
	private Float rule_condition_humidity;
	private String tmp_measure_type;

	public ClimatizationModuleData(VgerModule module, VgerSampling sampling, String sensor_uuid, String measure_type,
			Float value, Float rule_condition) {
		this.module = module;
		this.sampling = sampling;
		this.sensor_uuid = sensor_uuid;
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

}

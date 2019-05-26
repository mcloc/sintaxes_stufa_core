package br.com.sintechs.stufa.models;

import java.sql.Timestamp;

public class RuleEvent {

	private String rule_package;
	private String rule_name;
	private Float value;
	private Float rule_condition;
	private String cause_description;
	private String command;
	private boolean command_value;
	private Timestamp created_at;
	private Timestamp updated_at;
	private String sensor_uuid;
	private String actuator_uuid;
	private SintechsSampling sampling;
	private String measure_type;
	private SintechsModule module;
	private String module_name;

	public RuleEvent(String rule_name, SintechsSampling sampling, SintechsSamplingSensor samplingSensor,
			String cause_description, String command, boolean command_value, String sensor_uuid, String actuator_uuid) {
		this.sampling = sampling;
		this.module = sampling.getModule();
		this.module_name = sampling.getModule().getName();
		this.rule_name = rule_name;
		this.value = samplingSensor.getValue();
		this.rule_condition = samplingSensor.getRule_condition();
		this.measure_type = samplingSensor.getMeasure_type();
		this.cause_description = cause_description;
		this.command = command;
		this.command_value = command_value;
		this.sensor_uuid = samplingSensor.getSensor().getUuid();
		this.actuator_uuid = actuator_uuid;
	}

	public String getRule_name() {
		return rule_name;
	}

	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
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

	public String getDescription_cause() {
		return cause_description;
	}

	public void setDescription_cause(String description_cause) {
		this.cause_description = description_cause;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public boolean isCommand_value() {
		return command_value;
	}

	public void setCommand_value(boolean command_value) {
		this.command_value = command_value;
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

	public String getCause_description() {
		return cause_description;
	}

	public void setCause_description(String cause_description) {
		this.cause_description = cause_description;
	}

	public String getSensor_uuid() {
		return sensor_uuid;
	}

	public void setSensor_uuid(String sensor_uuid) {
		this.sensor_uuid = sensor_uuid;
	}

	public String getActuator_uuid() {
		return actuator_uuid;
	}

	public void setActuator_uuid(String actuator_uuid) {
		this.actuator_uuid = actuator_uuid;
	}

	public String getRule_package() {
		return rule_package;
	}

	public void setRule_package(String rule_package) {
		this.rule_package = rule_package;
	}

	public SintechsSampling getSampling() {
		return sampling;
	}

	public void setSampling(SintechsSampling sampling) {
		this.sampling = sampling;
	}

	public String getMeasure_type() {
		return measure_type;
	}

	public void setMeasure_type(String measure_type) {
		this.measure_type = measure_type;
	}

	public SintechsModule getModule() {
		return module;
	}

	public void setModule(SintechsModule module) {
		this.module = module;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actuator_uuid == null) ? 0 : actuator_uuid.hashCode());
		result = prime * result + ((cause_description == null) ? 0 : cause_description.hashCode());
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + (command_value ? 1231 : 1237);
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((measure_type == null) ? 0 : measure_type.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((module_name == null) ? 0 : module_name.hashCode());
		result = prime * result + ((rule_condition == null) ? 0 : rule_condition.hashCode());
		result = prime * result + ((rule_name == null) ? 0 : rule_name.hashCode());
		result = prime * result + ((rule_package == null) ? 0 : rule_package.hashCode());
		result = prime * result + ((sampling == null) ? 0 : sampling.hashCode());
		result = prime * result + ((sensor_uuid == null) ? 0 : sensor_uuid.hashCode());
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
		RuleEvent other = (RuleEvent) obj;
		if (actuator_uuid == null) {
			if (other.actuator_uuid != null)
				return false;
		} else if (!actuator_uuid.equals(other.actuator_uuid))
			return false;
		if (cause_description == null) {
			if (other.cause_description != null)
				return false;
		} else if (!cause_description.equals(other.cause_description))
			return false;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (command_value != other.command_value)
			return false;
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
		if (module_name == null) {
			if (other.module_name != null)
				return false;
		} else if (!module_name.equals(other.module_name))
			return false;
		if (rule_condition == null) {
			if (other.rule_condition != null)
				return false;
		} else if (!rule_condition.equals(other.rule_condition))
			return false;
		if (rule_name == null) {
			if (other.rule_name != null)
				return false;
		} else if (!rule_name.equals(other.rule_name))
			return false;
		if (rule_package == null) {
			if (other.rule_package != null)
				return false;
		} else if (!rule_package.equals(other.rule_package))
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

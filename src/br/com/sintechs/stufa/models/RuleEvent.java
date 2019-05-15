package br.com.sintechs.stufa.models;

import java.math.BigInteger;
import java.sql.Timestamp;

import org.json.JSONObject;

import br.com.sintechs.stufa.GlobalProperties;

public class RuleEvent {

	private String rule_name;
	private Float value;
	private Float rule_condition;
	private BigInteger sampling_id;
	private BigInteger sampling_sensor_id;
	private String cause_description;
	private String command;
	private boolean command_value;
	private Timestamp created_at;
	private Timestamp updated_at;
	private String sensor_uuid;
	private String actuator_uuid;
	
	public RuleEvent(String rule_name, Float value, Float rule_condition, BigInteger sampling_id,
			String cause_description, String command, boolean command_value, String sensor_uuid,
			String actuator_uuid) {
		this.rule_name = rule_name;
		this.value = value;
		this.rule_condition = rule_condition;
		this.sampling_id = sampling_id;
		this.cause_description = cause_description;
		this.command = command;
		this.command_value = command_value;
		this.sensor_uuid = sensor_uuid;
		this.actuator_uuid = actuator_uuid;
	}
	public RuleEvent(JSONObject json_obj) {
		this.rule_name = json_obj.getString("rule_name");
		this.value = Float.parseFloat(json_obj.getString("value"));
		this.rule_condition = Float.parseFloat(json_obj.getString("rule_condition"));
		this.sampling_id = json_obj.getBigInteger("sampling_id");
		this.sampling_sensor_id = json_obj.getBigInteger("sampling_sensor_id");
		this.cause_description = json_obj.getString("cause_description");
		this.command = json_obj.getString("command");
		this.command_value = "1".equals(json_obj.getString("command_value"));
		this.sensor_uuid = json_obj.getString("sensor_uuid");
		this.actuator_uuid = json_obj.getString("actuator_uuid");
		this.created_at = Timestamp.valueOf(json_obj.getString("created_at"));
		this.updated_at = Timestamp.valueOf(json_obj.getString("updated_at"));
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
	public BigInteger getSampling_id() {
		return sampling_id;
	}
	public void setSampling_id(BigInteger sampling_id) {
		this.sampling_id = sampling_id;
	}
	public BigInteger getSampling_sensor_id() {
		return sampling_sensor_id;
	}
	public void setSampling_sensor_id(BigInteger sampling_sensor_id) {
		this.sampling_sensor_id = sampling_sensor_id;
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
	
	
	
}

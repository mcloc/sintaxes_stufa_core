package br.com.sintechs.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kie.api.definition.type.Duration;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import br.com.sintechs.stufa.GlobalProperties;

@Role(Role.Type.EVENT)
@Expires("10m")
public class SintechsSampling implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2991985706566497971L;
	private BigInteger id;
	private int module_id;
	private String status;
	private BigInteger uptime;
	private String error_code;
	private String error_msg;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	private List<SintechsSamplingSensor> samplingSensors;
	private List<SintechsSamplingActuator> samplingActuators;
	
	private GlobalProperties globalProperties;
	
	
	public SintechsSampling(JSONObject json_obj, GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
		this.id = json_obj.getBigInteger("id");
		this.module_id = json_obj.getInt("module_id");
		this.status = json_obj.getString("status");;
		this.uptime = json_obj.getBigInteger("uptime");
		if(!json_obj.isNull("error_code"))
			this.error_code =  json_obj.getString("error_code");
		if(!json_obj.isNull("error_msg"))
			this.error_msg = json_obj.getString("error_msg");
		
		this.created_at = Timestamp.valueOf(json_obj.getString("created_at"));
		this.updated_at = Timestamp.valueOf(json_obj.getString("updated_at"));
		this.samplingSensors = new ArrayList<SintechsSamplingSensor>();
		this.samplingActuators = new ArrayList<SintechsSamplingActuator>();
		
		JSONArray sampling_sensors_arr = json_obj.getJSONArray("sampling_sensors");
		JSONArray sampling_actuators_arr = json_obj.getJSONArray("sampling_actuators");
		sampling_sensors_arr.forEach(sampling_sensor -> {
			JSONObject sampling_sensor_obj = (JSONObject) sampling_sensor;
			SintechsSamplingSensor sintechsSamplingSensor = new SintechsSamplingSensor(sampling_sensor_obj, globalProperties);
			this.samplingSensors.add(sintechsSamplingSensor);
			

		});
		
		sampling_actuators_arr.forEach(sampling_actuator -> {
			JSONObject sampling_actuator_obj = (JSONObject) sampling_actuator;
			SintechsSamplingActuator sintechsSamplingActuator = new SintechsSamplingActuator(sampling_actuator_obj);
			this.samplingActuators.add(sintechsSamplingActuator);
		});
	}
	
	
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public int getModule_id() {
		return module_id;
	}
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigInteger getUptime() {
		return uptime;
	}
	public void setUptime(BigInteger uptime) {
		this.uptime = uptime;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
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
	public List<SintechsSamplingSensor> getSamplingSensors() {
		return samplingSensors;
	}
	public void setSamplingSensors(List<SintechsSamplingSensor> samplingSensors) {
		this.samplingSensors = samplingSensors;
	}
	public List<SintechsSamplingActuator> getSamplingActuators() {
		return samplingActuators;
	}
	public void setSamplingActuators(List<SintechsSamplingActuator> samplingActuators) {
		this.samplingActuators = samplingActuators;
	}
	
	
	
	
}

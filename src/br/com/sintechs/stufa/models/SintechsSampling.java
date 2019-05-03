package br.com.sintechs.stufa.models;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public class SintechsSampling {

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

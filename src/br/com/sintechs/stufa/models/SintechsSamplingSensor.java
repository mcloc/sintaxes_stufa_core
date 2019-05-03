package br.com.sintechs.stufa.models;

import java.math.BigInteger;
import java.sql.Timestamp;

public class SintechsSamplingSensor {
	
	private BigInteger sampling_id;
	private SintechsSensor sensor;
	private String measure_type;
	private String value;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	
	public BigInteger getSampling_id() {
		return sampling_id;
	}
	public void setSampling_id(BigInteger sampling_id) {
		this.sampling_id = sampling_id;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}

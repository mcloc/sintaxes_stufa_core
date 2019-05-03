package br.com.sintechs.stufa.models;

import java.math.BigInteger;
import java.sql.Timestamp;

public class SintechsSensor {
	
	private BigInteger id;
	private String uuid;
	private String type;
	private String description;
	private String model;
	private boolean active;
	private SintechsModule module;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public SintechsModule getModule() {
		return module;
	}
	public void setModule(SintechsModule module) {
		this.module = module;
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
	
	
	
}

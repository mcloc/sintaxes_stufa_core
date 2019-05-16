package br.com.sintechs.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import org.json.JSONObject;

public class SintechsActuator  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5408809016130672000L;
	private BigInteger id;
	private String uuid;
	private String type;
	private String description;
	private String model;
	private boolean active;
	private BigInteger module_id;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	
	
	public SintechsActuator(JSONObject jsonObject) {
		this.id = jsonObject.getBigInteger("id");
		this.uuid = jsonObject.getString("uuid");
		this.type = jsonObject.getString("type");
		this.description = jsonObject.getString("description");
		this.model = jsonObject.getString("model");
		this.module_id = jsonObject.getBigInteger("module_id");
		this.active = "1".equals(jsonObject.getString("active")); // LIXO Super POG
		this.created_at = Timestamp.valueOf(jsonObject.getString("created_at"));
		this.updated_at = Timestamp.valueOf(jsonObject.getString("updated_at"));
	}
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
	public BigInteger getModule_id() {
		return module_id;
	}
	public void setModule_id(BigInteger module_id) {
		this.module_id = module_id;
	}
	
	
	
}

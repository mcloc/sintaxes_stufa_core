package br.com.sintechs.stufa.models;

import java.sql.Timestamp;

import org.json.JSONObject;

public class SintechsModule {
	private Integer id;
	private String name;
	private String description;
	private boolean enabled;
	private SintechsModulesType module_type;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	
	public SintechsModule(JSONObject jsonObject) {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public SintechsModulesType getModule_type() {
		return module_type;
	}
	public void setModule_type(SintechsModulesType module_type) {
		this.module_type = module_type;
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

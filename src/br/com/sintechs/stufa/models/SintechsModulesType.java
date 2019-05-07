package br.com.sintechs.stufa.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class SintechsModulesType  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4377273897590824796L;
	private Integer id;
	private String name;
	private String description;
	private Timestamp created_at;
	private Timestamp updated_at;
	
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

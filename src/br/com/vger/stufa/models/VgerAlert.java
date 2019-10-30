package br.com.vger.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class VgerAlert  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2280701067008673314L;
	private BigInteger id;
	private boolean readed;
	private String message;
	private VgerModule module;
	private String created_by; 
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public boolean isReaded() {
		return readed;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public VgerModule getModule() {
		return module;
	}
	public void setModule(VgerModule module) {
		this.module = module;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
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

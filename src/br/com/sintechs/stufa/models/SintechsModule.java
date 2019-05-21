package br.com.sintechs.stufa.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.json.JSONObject;

import br.com.sintechs.stufa.GlobalProperties;

public class SintechsModule  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6156684239253178520L;
	private Integer id;
	private String name;
	private String description;
	private boolean enabled;
	private boolean active;
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
	public static List<SintechsModule> hidrateMultiple(JSONObject json_obj, GlobalProperties globalProperties) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((module_type == null) ? 0 : module_type.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
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
		SintechsModule other = (SintechsModule) obj;
		if (active != other.active)
			return false;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (enabled != other.enabled)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (module_type == null) {
			if (other.module_type != null)
				return false;
		} else if (!module_type.equals(other.module_type))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		return true;
	}
	
	
	
}

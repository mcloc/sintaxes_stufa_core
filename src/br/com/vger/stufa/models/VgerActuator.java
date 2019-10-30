package br.com.vger.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import org.json.JSONObject;

import br.com.vger.stufa.GlobalProperties;

public class VgerActuator  implements Serializable {
	
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
	private Timestamp created_at;
	private Timestamp updated_at;
	private VgerModule module;
	private boolean enabled;
	
	
	
//	public VgerActuator(JSONObject jsonObject) {
//		this.id = jsonObject.getBigInteger("id");
//		this.uuid = jsonObject.getString("uuid");
//		this.type = jsonObject.getString("type");
//		this.description = jsonObject.getString("description");
//		this.model = jsonObject.getString("model");
//		this.module_id = jsonObject.getBigInteger("module_id");
//		this.active = "1".equals(jsonObject.getString("active")); // LIXO Super POG
//		this.created_at = Timestamp.valueOf(jsonObject.getString("created_at"));
//		this.updated_at = Timestamp.valueOf(jsonObject.getString("updated_at"));
//	}
	
	public static VgerActuator hidrateFromModule(JSONObject json_obj, VgerModule module,
			GlobalProperties globalProperties) {
		VgerActuator actuator = new VgerActuator();
		actuator.id = json_obj.getBigInteger("id");
		actuator.uuid = json_obj.getString("uuid");
		actuator.type = json_obj.getString("type");
		actuator.description = json_obj.getString("description");
		actuator.model = json_obj.getString("model");
		actuator.module = module;
		actuator.active = "1".equals(json_obj.getString("active")); // LIXO Super POG
		actuator.enabled = "1".equals(json_obj.getString("enabled")); // LIXO Super POG
		actuator.created_at = Timestamp.valueOf(json_obj.getString("created_at"));
		actuator.updated_at = Timestamp.valueOf(json_obj.getString("updated_at"));
		
		return actuator;
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

	public VgerModule getModule() {
		return module;
	}

	public void setModule(VgerModule module) {
		this.module = module;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		VgerActuator other = (VgerActuator) obj;
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
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	
}

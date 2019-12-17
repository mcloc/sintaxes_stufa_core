package br.com.vger.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.vger.stufa.GlobalProperties;

public class VgerModule  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6156684239253178520L;
	private BigInteger id;
	private String name;
	private long _4BCP_UUID; 
	private String ip_address;
	private String description;
	private boolean enabled;
	private boolean active;
	private VgerModulesType module_type;
	private Timestamp created_at;
	private Timestamp updated_at;
	

	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
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
	public VgerModulesType getModule_type() {
		return module_type;
	}
	public void setModule_type(VgerModulesType module_type) {
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
	
	public long get_4BCP_UUID() {
		return _4BCP_UUID;
	}
	public void set_4BCP_UUID(long _4bcp_UUID) {
		_4BCP_UUID = _4bcp_UUID;
	}
	
	
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public static List<VgerModule> hidrateMultiple(JSONObject json_obj, GlobalProperties globalProperties) throws JSONException, Exception {
		if (!json_obj.isNull("error_code"))
			throw new Exception ("json error: " + json_obj.getString("error_code"));
		if (!json_obj.isNull("error_msg"))
			throw new Exception ("json error: " + json_obj.getString("error_msg"));

		
		List<VgerModule> modules_list = new ArrayList<VgerModule>();
		
		JSONArray data_arr = json_obj.getJSONArray("data");
		// Loop for each sensor of the module (arduino_board)
		data_arr.forEach(data -> {
			
			VgerModule module = new VgerModule();
			
			JSONObject data_module_obj = (JSONObject) data;
			module.setId(data_module_obj.getBigInteger("id"));
			module.setName(data_module_obj.getString("name"));
			module.setDescription(data_module_obj.getString("description"));
			module.setActive("1".equals(data_module_obj.getInt("active")));
			module.setEnabled("1".equals(data_module_obj.getInt("enabled")));
			
			JSONObject data_module_type = data_module_obj.getJSONObject("module_type");
			VgerModulesType module_type = VgerModulesType.hidratefromJson(data_module_type);
			module.setModule_type(module_type);
			module.setCreated_at(Timestamp.valueOf( data_module_obj.getString("created_at")));
			module.setUpdated_at(Timestamp.valueOf( data_module_obj.getString("updated_at")));
			
			modules_list.add(module);
		});
		
		return modules_list;
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
		result = prime * result + (int) (_4BCP_UUID ^ (_4BCP_UUID >>> 32));
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
		VgerModule other = (VgerModule) obj;
		if (_4BCP_UUID != other._4BCP_UUID)
			return false;
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

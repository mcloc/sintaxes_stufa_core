package br.com.sintechs.stufa.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.rest.RESTClient;

@Role(Role.Type.EVENT)
@Expires("10m")
public class SintechsSampling implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(SintechsSampling.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 2991985706566497971L;
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

	private GlobalProperties globalProperties;

	public SintechsSampling(JSONObject json_obj, GlobalProperties globalProperties) throws Exception{
		this.globalProperties = globalProperties;
		boolean NEW_OBJECT = false;
		try {
			this.id = json_obj.getBigInteger("id");
		} catch (Exception e) {
			NEW_OBJECT = true;
			LOGGER.debug("new object, it ins't on DB yet");
		}

		if (NEW_OBJECT) {
			RESTClient client = new RESTClient(globalProperties);
			int module_id = client.getModuleId(json_obj.getString("module_name"));
			this.module_id = module_id;
		} else {
			this.module_id = json_obj.getInt("module_id");
		}
		this.status = json_obj.getString("status");
		this.uptime = json_obj.getBigInteger("uptime");
		
		if (!json_obj.isNull("error_code"))
			this.error_code = json_obj.getString("error_code");
		if (!json_obj.isNull("error_msg"))
			this.error_msg = json_obj.getString("error_msg");

		if (!NEW_OBJECT) {
			this.created_at = Timestamp.valueOf(json_obj.getString("created_at"));
			this.updated_at = Timestamp.valueOf(json_obj.getString("updated_at"));
		} else {
			Date date = new Date();
			long time = date.getTime();
			
			this.created_at = new Timestamp(time);
			this.updated_at = created_at;
		}
		
		JSONObject data_obj = json_obj.getJSONObject("data");

		this.samplingSensors = new ArrayList<SintechsSamplingSensor>();
		this.samplingActuators = new ArrayList<SintechsSamplingActuator>();

		// IF this is a already existent object
		if (!NEW_OBJECT) {
			JSONArray sampling_sensors_arr = json_obj.getJSONArray("sampling_sensors");
			JSONArray sampling_actuators_arr = json_obj.getJSONArray("sampling_actuators");
			sampling_sensors_arr.forEach(sampling_sensor -> {
				JSONObject sampling_sensor_obj = (JSONObject) sampling_sensor;
				SintechsSamplingSensor sintechsSamplingSensor = new SintechsSamplingSensor(sampling_sensor_obj, globalProperties);
				this.samplingSensors.add(sintechsSamplingSensor);

			});

			sampling_actuators_arr.forEach(sampling_actuator -> {
				JSONObject sampling_actuator_obj = (JSONObject) sampling_actuator;
				SintechsSamplingActuator sintechsSamplingActuator = new SintechsSamplingActuator(sampling_actuator_obj);
				this.samplingActuators.add(sintechsSamplingActuator);
			});
		} else { // New object which is not yet on database
			JSONArray sampling_sensors_arr = data_obj.getJSONArray("sensors");
			JSONArray sampling_actuators_arr = data_obj.getJSONArray("actuators");
			
			//Loop for each sensor of the module (arduino_board)
			sampling_sensors_arr.forEach(sampling_sensor -> {
				JSONObject sampling_sensor_obj = (JSONObject) sampling_sensor;
				
				SintechsSamplingSensor sintechsSamplingSensor = new SintechsSamplingSensor(globalProperties);
				RESTClient client = new RESTClient(globalProperties);
				JSONObject sensor_json_obj = client.getSensorByUUID(sampling_sensor_obj.getString("uuid"));
				
				//TODO: check for errors
				SintechsSensor sintechs_sensor = new SintechsSensor(sensor_json_obj.getJSONObject("data"));
				
				sintechsSamplingSensor.setSensor(sintechs_sensor);
				sintechsSamplingSensor.setCreated_at(this.created_at);
				sintechsSamplingSensor.setUpdated_at(this.updated_at);
				
				//Loop for one sensor values, for sensors which have multiple return values
				JSONArray sensor_arr = sampling_sensor_obj.getJSONArray("value");
				sensor_arr.forEach(sensor -> {
					JSONObject sensor_obj = (JSONObject) sensor;
					
					//Sensor value name (humidity, temperature, heat_index) for example on the same sensor
					String[] names = sensor_obj.getNames(sensor_obj);
					
					if(names.length != 1) {
						throw new RuntimeException("'value' from sensor_obj has no measure_type");
					}
					sintechsSamplingSensor.setMeasure_type(names[0]);
					sintechsSamplingSensor.setValue(sensor_obj.getFloat(names[0]));
					sintechsSamplingSensor.setRule_condition(names[0]);
					this.samplingSensors.add(sintechsSamplingSensor);
				});
			});

			//TODO: hidrate the sampling actuator
//			sampling_actuators_arr.forEach(sampling_actuator -> {
//				JSONObject sampling_actuator_obj = (JSONObject) sampling_actuator;
//				SintechsSamplingActuator sintechsSamplingActuator = new SintechsSamplingActuator(sampling_actuator_obj);
//				this.samplingActuators.add(sintechsSamplingActuator);
//			});
		}
		
		//Get a unique hashCode for this sampling
	}
	
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((error_code == null) ? 0 : error_code.hashCode());
		result = prime * result + ((error_msg == null) ? 0 : error_msg.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + module_id;
		result = prime * result + ((samplingActuators == null) ? 0 : samplingActuators.hashCode());
		result = prime * result + ((samplingSensors == null) ? 0 : samplingSensors.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
		result = prime * result + ((uptime == null) ? 0 : uptime.hashCode());
		return result;
	}


	public int getHashCode() {
		return this.hashCode();
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SintechsSampling other = (SintechsSampling) obj;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (error_code == null) {
			if (other.error_code != null)
				return false;
		} else if (!error_code.equals(other.error_code))
			return false;
		if (error_msg == null) {
			if (other.error_msg != null)
				return false;
		} else if (!error_msg.equals(other.error_msg))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (module_id != other.module_id)
			return false;
		if (samplingActuators == null) {
			if (other.samplingActuators != null)
				return false;
		} else if (!samplingActuators.equals(other.samplingActuators))
			return false;
		if (samplingSensors == null) {
			if (other.samplingSensors != null)
				return false;
		} else if (!samplingSensors.equals(other.samplingSensors))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		if (uptime == null) {
			if (other.uptime != null)
				return false;
		} else if (!uptime.equals(other.uptime))
			return false;
		return true;
	}





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

	public GlobalProperties getGlobalProperties() {
		return globalProperties;
	}

	public void setGlobalProperties(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

}

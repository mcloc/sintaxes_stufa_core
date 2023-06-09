package br.com.vger.stufa.models;

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

import br.com.vger.stufa.GlobalProperties;
import br.com.vger.stufa.rest.RESTClient;

@Role(Role.Type.EVENT)
@Expires("10m")
public class VgerSampling implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(VgerSampling.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 2991985706566497971L;
	private Timestamp uuid;
	private VgerModule module;
	private String status;
	private BigInteger uptime;
	private String error_code;
	private String error_msg;
	private Timestamp created_at;
	private Timestamp updated_at;

	private List<VgerSamplingSensor> samplingSensors;
	private List<VgerSamplingActuator> samplingActuators;

	private GlobalProperties globalProperties;
	

	public VgerSampling() {

	}

	public static VgerSampling hidrateFromModule(JSONObject json_obj, VgerModule module,
			GlobalProperties globalProperties) throws Exception {
		VgerSampling sampling = new VgerSampling();
		Date date = new Date();
		long time = date.getTime();
		sampling.uuid = new Timestamp(time);
		sampling.created_at = sampling.uuid;
		sampling.updated_at = sampling.created_at;
		sampling.globalProperties = globalProperties;
		sampling.module = module;
		sampling.status = json_obj.getString("status");
		sampling.uptime = json_obj.getBigInteger("uptime");

		if (!json_obj.isNull("error_code"))
			sampling.error_code = json_obj.getString("error_code");
		if (!json_obj.isNull("error_msg"))
			sampling.error_msg = json_obj.getString("error_msg");



		JSONObject data_obj = json_obj.getJSONObject("data");

		sampling.samplingSensors = new ArrayList<VgerSamplingSensor>();
		sampling.samplingActuators = new ArrayList<VgerSamplingActuator>();

		JSONArray sampling_sensors_arr = data_obj.getJSONArray("sensors");
		JSONArray sampling_actuators_arr = data_obj.getJSONArray("actuators");

		// Loop for each sensor of the module (arduino_board)
		sampling_sensors_arr.forEach(sampling_sensor -> {
			JSONObject sampling_sensor_obj = (JSONObject) sampling_sensor;
			RESTClient client = new RESTClient(globalProperties);
			VgerSensor sensor = client.getSensorByUUID(sampling_sensor_obj.getString("uuid"), module);
			LOGGER.info("module: "+module.getName()+ " sensor: " + sensor.getUuid());
			// Loop for one sensor values, for sensors which have multiple return values, this will happen ONCE per parent LOOP
			JSONArray sensor_arr = sampling_sensor_obj.getJSONArray("value");
			sensor_arr.forEach(sensor_values -> {
				JSONObject sensor_obj = (JSONObject) sensor_values;

				// Sensor value name (humidity, temperature, heat_index) for example on the same
				// sensor
				String[] names = JSONObject.getNames(sensor_obj);

				if (names.length < 1) {
					throw new RuntimeException("'value' from sensor_obj has no measure_type");
				}
				
				for(String sensor_name : names) {
					LOGGER.debug("module: "+module.getName()+ " sensor: " + sensor.getUuid() + " measure_type: " + sensor_name);
					VgerSamplingSensor vgerSamplingSensor = new VgerSamplingSensor(globalProperties);
					vgerSamplingSensor.setModule(module);
					vgerSamplingSensor.setSensor(sensor);
					vgerSamplingSensor.setCreated_at(sampling.created_at);
					vgerSamplingSensor.setUpdated_at(sampling.updated_at);
					vgerSamplingSensor.setMeasure_type(sensor_name);
					vgerSamplingSensor.setValue(sensor_obj.getFloat(sensor_name));
					vgerSamplingSensor.setRule_condition(sensor_name);
					vgerSamplingSensor.setSampling_uuid(sampling.getUuid());
					LOGGER.debug("module: "+module.getName()+ " sensor: " + sensor.getUuid() + " measure_type: " + sensor_name + "SamplingSensor UUID: " + vgerSamplingSensor.getSampling_uuid());
					sampling.samplingSensors.add(vgerSamplingSensor);
				}
			});
		});

		// TODO: hidrate the sampling actuator
		 sampling_actuators_arr.forEach(sampling_actuator -> {
			 JSONObject sampling_actuator_obj = (JSONObject) sampling_actuator;
			 RESTClient client = new RESTClient(globalProperties);
				VgerActuator actuator = client.getActuatorByUUID(sampling_actuator_obj.getString("uuid"), module);
				VgerSamplingActuator vgerSamplingActuator = new VgerSamplingActuator(globalProperties);
				vgerSamplingActuator.setActuator(actuator);
				vgerSamplingActuator.setModule(module);
				vgerSamplingActuator.setCreated_at(sampling.created_at);
				vgerSamplingActuator.setUpdated_at(sampling.updated_at);
				// Loop for one actuator values, for actuators which have multiple return values, this will happen ONCE per parent LOOP
				JSONArray actuator_arr = sampling_actuator_obj.getJSONArray("value");
				actuator_arr.forEach(actuator_values -> {
					JSONObject actuator_obj = (JSONObject) actuator_values;
					vgerSamplingActuator.setActive(actuator_obj.getBoolean("active"));
					vgerSamplingActuator.setActivated_time(new BigInteger("0"));
					sampling.samplingActuators.add(vgerSamplingActuator);
				});
		 });

		return sampling;
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

	public List<VgerSamplingSensor> getSamplingSensors() {
		return samplingSensors;
	}

	public void setSamplingSensors(List<VgerSamplingSensor> samplingSensors) {
		this.samplingSensors = samplingSensors;
	}

	public List<VgerSamplingActuator> getSamplingActuators() {
		return samplingActuators;
	}

	public void setSamplingActuators(List<VgerSamplingActuator> samplingActuators) {
		this.samplingActuators = samplingActuators;
	}

	public GlobalProperties getGlobalProperties() {
		return globalProperties;
	}

	public void setGlobalProperties(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	public Timestamp getUuid() {
		return uuid;
	}

	public void setUuid(Timestamp uuid) {
		this.uuid = uuid;
	}

	public VgerModule getModule() {
		return module;
	}

	public void setModule(VgerModule module) {
		this.module = module;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((error_code == null) ? 0 : error_code.hashCode());
		result = prime * result + ((error_msg == null) ? 0 : error_msg.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((samplingActuators == null) ? 0 : samplingActuators.hashCode());
		result = prime * result + ((samplingSensors == null) ? 0 : samplingSensors.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
		result = prime * result + ((uptime == null) ? 0 : uptime.hashCode());
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
		VgerSampling other = (VgerSampling) obj;
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
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
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
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}


}

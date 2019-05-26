package br.com.sintechs.stufa.models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sintechs.stufa.GlobalProperties;

public class ClimatizationEventHandler2 {

	private List<SintechsModule> modules = new ArrayList<SintechsModule>();

	private Map<SintechsModule, List<SintechsSampling>> sampling_map = new HashMap<SintechsModule, List<SintechsSampling>>();
	private Map<SintechsModule, List<Float>> heat_index_map = new HashMap<SintechsModule, List<Float>>();
	private Map<SintechsModule, List<Float>> temperature_map = new HashMap<SintechsModule, List<Float>>();
	private Map<SintechsModule, List<Float>> humidity_map = new HashMap<SintechsModule, List<Float>>();
	private Map<SintechsModule, List<RuleEvent>> rule_event_map = new HashMap<SintechsModule, List<RuleEvent>>();

	// private List<RuleEvent> rule_event_list = new ArrayList<RuleEvent>();
	private RuleEvent last_rule_event;

	private List<SintechsSamplingActuator> actuators_list = new ArrayList<SintechsSamplingActuator>();
	private Map<String, Boolean> actuators_status = new HashMap<String, Boolean>();
	private Map<String, BigInteger> actuators_status_time = new HashMap<String, BigInteger>();
	private GlobalProperties globalProperties;

	private static ClimatizationEventHandler2 instance = null;

	public ClimatizationEventHandler2() {
	}

	public static ClimatizationEventHandler2 initialize(GlobalProperties globalProperties) {
		if (instance == null)
			instance = new ClimatizationEventHandler2();

		instance.setGlobalProperties(globalProperties);

		return instance;
	}

	public static ClimatizationEventHandler2 addEvent(RuleEvent ev, SintechsSamplingSensor samplingSensor) {
		if (!instance.getModules().contains(ev.getSampling().getModule())) {
			instance.getModules().add(ev.getSampling().getModule());
			Map<SintechsModule, List<Float>> heat_index_map = new HashMap<SintechsModule, List<Float>>();
			Map<SintechsModule, List<Float>> temperature_map = new HashMap<SintechsModule, List<Float>>();
			Map<SintechsModule, List<Float>> humidity_map = new HashMap<SintechsModule, List<Float>>();
			Map<SintechsModule, List<RuleEvent>> rule_event_map = new HashMap<SintechsModule, List<RuleEvent>>();
			Map<SintechsModule, List<SintechsSampling>> sampling_map = new HashMap<SintechsModule, List<SintechsSampling>>();

			List<Float> heat_index_list = new ArrayList<Float>();
			List<Float> temperature_list = new ArrayList<Float>();
			List<Float> humidity_list = new ArrayList<Float>();
			List<RuleEvent> rule_event_list = new ArrayList<RuleEvent>();
			List<SintechsSampling> sampling_list = new ArrayList<SintechsSampling>();
			heat_index_map.put(ev.getModule(), heat_index_list);
			temperature_map.put(ev.getModule(), temperature_list);
			humidity_map.put(ev.getModule(), humidity_list);
			rule_event_map.put(ev.getModule(), rule_event_list);
			sampling_map.put(ev.getModule(), sampling_list);
			instance.setHeat_index_map(heat_index_map);
			instance.setTemperature_map(temperature_map);
			instance.setHumidity_map(humidity_map);
			instance.setRule_event_map(rule_event_map);
			instance.setSampling_map(sampling_map);
		}

		
		instance.getSampling_map().get(ev.getModule()).add(ev.getSampling());
		instance.getRule_event_map().get(ev.getModule()).add(ev);
		instance.setLast_rule_event(ev);

		switch (samplingSensor.getMeasure_type()) {
		case "heat_index":
			instance.getHeat_index_map().get(ev.getModule()).add(samplingSensor.getValue());
			break;
		case "temperature":
			instance.getTemperature_map().get(ev.getModule()).add(samplingSensor.getValue());
			break;
		case "humidity":
			instance.getHumidity_map().get(ev.getModule()).add(samplingSensor.getValue());
			break;
		}

		List<SintechsSamplingActuator> samplingActuatorsList = ev.getSampling().getSamplingActuators();
		for (SintechsSamplingActuator sampling_actuator : samplingActuatorsList) {
			instance.actuators_status.put(sampling_actuator.getActuator().getUuid(), sampling_actuator.isActive());
			instance.actuators_status_time.put(sampling_actuator.getActuator().getUuid(),
					sampling_actuator.getActivated_time());
		}

		return instance;
	}

	private Float getAvarage(List<Float> list) {
		if (list == null)
			return null;

		Float sum = 0F;
		int i = 0;
		for (Float value : list) {
			sum = sum + value;
			i++;
		}

		if (i > 0)
			return (sum / i);
		else if (i == 0 && list.size() > 0)
			return sum;

		return null;
	}

	public RuleEvent getLast_rule_event() {
		return last_rule_event;
	}

	public void setLast_rule_event(RuleEvent last_rule_event) {
		this.last_rule_event = last_rule_event;
	}

	public GlobalProperties getGlobalProperties() {
		return globalProperties;
	}

	public void setGlobalProperties(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	public Map<String, Boolean> getActuators_status() {
		return actuators_status;
	}

	public void setActuators_status(Map<String, Boolean> actuators_status) {
		this.actuators_status = actuators_status;
	}

	public List<SintechsSamplingActuator> getActuators_list() {
		return actuators_list;
	}

	public void setActuators_list(List<SintechsSamplingActuator> actuators_list) {
		this.actuators_list = actuators_list;
	}

	public List<SintechsModule> getModules() {
		return modules;
	}

	public void setModules(List<SintechsModule> modules) {
		this.modules = modules;
	}

	public Map<String, BigInteger> getActuators_status_time() {
		return actuators_status_time;
	}

	public void setActuators_status_time(Map<String, BigInteger> actuators_status_time) {
		this.actuators_status_time = actuators_status_time;
	}

	public Map<SintechsModule, List<Float>> getHeat_index_map() {
		return heat_index_map;
	}

	public void setHeat_index_map(Map<SintechsModule, List<Float>> heat_index_map) {
		this.heat_index_map = heat_index_map;
	}

	public Map<SintechsModule, List<Float>> getTemperature_map() {
		return temperature_map;
	}

	public void setTemperature_map(Map<SintechsModule, List<Float>> temperature_map) {
		this.temperature_map = temperature_map;
	}

	public Map<SintechsModule, List<Float>> getHumidity_map() {
		return humidity_map;
	}

	public void setHumidity_map(Map<SintechsModule, List<Float>> humidity_map) {
		this.humidity_map = humidity_map;
	}

	public Map<SintechsModule, List<RuleEvent>> getRule_event_map() {
		return rule_event_map;
	}

	public void setRule_event_map(Map<SintechsModule, List<RuleEvent>> rule_event_map) {
		this.rule_event_map = rule_event_map;
	}

	public Map<SintechsModule, List<SintechsSampling>> getSampling_map() {
		return sampling_map;
	}

	public void setSampling_map(Map<SintechsModule, List<SintechsSampling>> sampling_map) {
		this.sampling_map = sampling_map;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actuators_list == null) ? 0 : actuators_list.hashCode());
		result = prime * result + ((actuators_status == null) ? 0 : actuators_status.hashCode());
		result = prime * result + ((actuators_status_time == null) ? 0 : actuators_status_time.hashCode());
		result = prime * result + ((heat_index_map == null) ? 0 : heat_index_map.hashCode());
		result = prime * result + ((humidity_map == null) ? 0 : humidity_map.hashCode());
		result = prime * result + ((last_rule_event == null) ? 0 : last_rule_event.hashCode());
		result = prime * result + ((modules == null) ? 0 : modules.hashCode());
		result = prime * result + ((rule_event_map == null) ? 0 : rule_event_map.hashCode());
		result = prime * result + ((sampling_map == null) ? 0 : sampling_map.hashCode());
		result = prime * result + ((temperature_map == null) ? 0 : temperature_map.hashCode());
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
		ClimatizationEventHandler2 other = (ClimatizationEventHandler2) obj;
		if (actuators_list == null) {
			if (other.actuators_list != null)
				return false;
		} else if (!actuators_list.equals(other.actuators_list))
			return false;
		if (actuators_status == null) {
			if (other.actuators_status != null)
				return false;
		} else if (!actuators_status.equals(other.actuators_status))
			return false;
		if (actuators_status_time == null) {
			if (other.actuators_status_time != null)
				return false;
		} else if (!actuators_status_time.equals(other.actuators_status_time))
			return false;
		if (heat_index_map == null) {
			if (other.heat_index_map != null)
				return false;
		} else if (!heat_index_map.equals(other.heat_index_map))
			return false;
		if (humidity_map == null) {
			if (other.humidity_map != null)
				return false;
		} else if (!humidity_map.equals(other.humidity_map))
			return false;
		if (last_rule_event == null) {
			if (other.last_rule_event != null)
				return false;
		} else if (!last_rule_event.equals(other.last_rule_event))
			return false;
		if (modules == null) {
			if (other.modules != null)
				return false;
		} else if (!modules.equals(other.modules))
			return false;
		if (rule_event_map == null) {
			if (other.rule_event_map != null)
				return false;
		} else if (!rule_event_map.equals(other.rule_event_map))
			return false;
		if (sampling_map == null) {
			if (other.sampling_map != null)
				return false;
		} else if (!sampling_map.equals(other.sampling_map))
			return false;
		if (temperature_map == null) {
			if (other.temperature_map != null)
				return false;
		} else if (!temperature_map.equals(other.temperature_map))
			return false;
		return true;
	}

}

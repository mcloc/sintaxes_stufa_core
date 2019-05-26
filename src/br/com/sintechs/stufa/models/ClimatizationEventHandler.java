package br.com.sintechs.stufa.models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sintechs.stufa.GlobalProperties;

public class ClimatizationEventHandler {

	private List<Float> heat_index_list = new ArrayList<Float>();
	private List<Float> temperature_list = new ArrayList<Float>();
	private List<Float> humidity_list = new ArrayList<Float>();
	private List<RuleEvent> rule_event_list = new ArrayList<RuleEvent>();
	private List<SintechsSamplingActuator> actuators_list = new ArrayList<SintechsSamplingActuator>();
	private RuleEvent last_rule_event;
	private Map<String, Boolean> boards = new HashMap<String, Boolean>();
	private Map<String, Boolean> boards_evaluated = new HashMap<String, Boolean>();
	private Map<String, Boolean> actuators_status = new HashMap<String, Boolean>();
	private Map<String, BigInteger> actuators_status_time = new HashMap<String, BigInteger>();
	private int total_boards_avaliable = 0;
	private GlobalProperties globalProperties;

	private static ClimatizationEventHandler instance = null;

	public ClimatizationEventHandler() {
	}

	public static ClimatizationEventHandler initialize(GlobalProperties globalProperties) {
		if (instance == null)
			instance = new ClimatizationEventHandler();
		
		instance.setGlobalProperties(globalProperties);

		return instance;
	}

	public static ClimatizationEventHandler addEvent(RuleEvent ev, SintechsSamplingSensor samplingSensor) {
		instance.getRule_event_list().add(ev);
		instance.setLast_rule_event(ev);
		instance.setBoardReaded(ev.getSampling().getModule().getName());

		switch (samplingSensor.getMeasure_type()) {
		case "heat_index":
			instance.getHeat_index_list().add(samplingSensor.getValue());
			break;
		case "temperature":
			instance.getTemperature_list().add(samplingSensor.getValue());
			break;
		case "humidity":
			instance.getHumidity_list().add(samplingSensor.getValue());
			break;
		}
		
		List<SintechsSamplingActuator> samplingActuatorsList = ev.getSampling().getSamplingActuators();
		for(SintechsSamplingActuator sampling_actuator : samplingActuatorsList) {
			instance.actuators_status.put(sampling_actuator.getActuator().getUuid(), sampling_actuator.isActive());
			instance.actuators_status_time.put(sampling_actuator.getActuator().getUuid(), sampling_actuator.getActivated_time());
		}

		return instance;
	}

	private void setBoardReaded(String name) {
		boards.put(name, true);
	}

	public Float getAvarageHeadIndex() {
		return getAvarage(heat_index_list);
	}

	public Float getAvarageHumidity() {
		return getAvarage(humidity_list);
	}

	public Float getAvarageTemperature() {
		return getAvarage(temperature_list);
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

	public List<Float> getHeat_index_list() {
		return heat_index_list;
	}

	public void setHeat_index_list(List<Float> heat_index_list) {
		this.heat_index_list = heat_index_list;
	}

	public List<Float> getTemperature_list() {
		return temperature_list;
	}

	public void setTemperature_list(List<Float> temperature_list) {
		this.temperature_list = temperature_list;
	}

	public List<Float> getHumidity_list() {
		return humidity_list;
	}

	public void setHumidity_list(List<Float> humidity_list) {
		this.humidity_list = humidity_list;
	}

	public List<RuleEvent> getRule_event_list() {
		return rule_event_list;
	}

	public void setRule_event_list(List<RuleEvent> rule_event_list) {
		this.rule_event_list = rule_event_list;
	}

	public RuleEvent getLast_rule_event() {
		return last_rule_event;
	}

	public void setLast_rule_event(RuleEvent last_rule_event) {
		this.last_rule_event = last_rule_event;
	}

	public Map<String, Boolean> getBoards() {
		return boards;
	}

	public void setBoards(Map<String, Boolean> boards) {
		this.boards = boards;
	}

	public Map<String, Boolean> getBoards_evaluated() {
		return boards_evaluated;
	}

	public void setBoards_evaluated(Map<String, Boolean> boards_evaluated) {
		this.boards_evaluated = boards_evaluated;
	}

	public int getTotal_boards_avaliable() {
		return total_boards_avaliable;
	}

	public void setTotal_boards_avaliable(int total_boards_avaliable) {
		this.total_boards_avaliable = total_boards_avaliable;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actuators_list == null) ? 0 : actuators_list.hashCode());
		result = prime * result + ((actuators_status == null) ? 0 : actuators_status.hashCode());
		result = prime * result + ((boards == null) ? 0 : boards.hashCode());
		result = prime * result + ((boards_evaluated == null) ? 0 : boards_evaluated.hashCode());
		result = prime * result + ((heat_index_list == null) ? 0 : heat_index_list.hashCode());
		result = prime * result + ((humidity_list == null) ? 0 : humidity_list.hashCode());
		result = prime * result + ((last_rule_event == null) ? 0 : last_rule_event.hashCode());
		result = prime * result + ((rule_event_list == null) ? 0 : rule_event_list.hashCode());
		result = prime * result + ((temperature_list == null) ? 0 : temperature_list.hashCode());
		result = prime * result + total_boards_avaliable;
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
		ClimatizationEventHandler other = (ClimatizationEventHandler) obj;
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
		if (boards == null) {
			if (other.boards != null)
				return false;
		} else if (!boards.equals(other.boards))
			return false;
		if (boards_evaluated == null) {
			if (other.boards_evaluated != null)
				return false;
		} else if (!boards_evaluated.equals(other.boards_evaluated))
			return false;
		if (heat_index_list == null) {
			if (other.heat_index_list != null)
				return false;
		} else if (!heat_index_list.equals(other.heat_index_list))
			return false;
		if (humidity_list == null) {
			if (other.humidity_list != null)
				return false;
		} else if (!humidity_list.equals(other.humidity_list))
			return false;
		if (last_rule_event == null) {
			if (other.last_rule_event != null)
				return false;
		} else if (!last_rule_event.equals(other.last_rule_event))
			return false;
		if (rule_event_list == null) {
			if (other.rule_event_list != null)
				return false;
		} else if (!rule_event_list.equals(other.rule_event_list))
			return false;
		if (temperature_list == null) {
			if (other.temperature_list != null)
				return false;
		} else if (!temperature_list.equals(other.temperature_list))
			return false;
		if (total_boards_avaliable != other.total_boards_avaliable)
			return false;
		return true;
	}



}

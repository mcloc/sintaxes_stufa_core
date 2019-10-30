package br.com.vger.stufa.models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.vger.stufa.GlobalProperties;

public class ClimatizationEventHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClimatizationEventHandler.class);
	
//	private Map<VgerModule, Map<VgerSampling,List<ClimatizationEvent>>> climatization_event_map = new HashMap<VgerModule, Map<VgerSampling,List <ClimatizationEvent>>>();
	private List<ClimatizationEvent> climatization_event_list = new ArrayList <ClimatizationEvent>();
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	private List<VgerSamplingActuator> actuators_list = new ArrayList<VgerSamplingActuator>();
	private Map<String, Boolean> actuators_status = new HashMap<String, Boolean>();
	private Map<String, BigInteger> actuators_status_time = new HashMap<String, BigInteger>();
	private GlobalProperties globalProperties;

	private static ClimatizationEventHandler instance = null;
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ClimatizationEventHandler() {
	}

	public ClimatizationEventHandler(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	public static ClimatizationEventHandler initialize(GlobalProperties globalProperties) {
		if (instance == null)
			instance = new ClimatizationEventHandler();

		instance.setGlobalProperties(globalProperties);

		return instance;
	}
	
	public static ClimatizationEventHandler addStaticStack(ClimatizationEventStack climatization_event_stack) {
		instance.setClimatization_event_list(climatization_event_stack.getClimatization_event_list());
		
		return instance;
	}
	
	public   void  addStack(ClimatizationEventStack climatization_event_stack) {
		this.climatization_event_list = climatization_event_stack.getClimatization_event_list();
		
//		return instance;
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



	public List<ClimatizationEvent> getClimatization_event_list() {
		return climatization_event_list;
	}

	public void setClimatization_event_list(List<ClimatizationEvent> climatization_event_list) {
		this.climatization_event_list = climatization_event_list;
	}

	public List<VgerSamplingActuator> getActuators_list() {
		return actuators_list;
	}

	public void setActuators_list(List<VgerSamplingActuator> actuators_list) {
		this.actuators_list = actuators_list;
	}

	public Map<String, Boolean> getActuators_status() {
		return actuators_status;
	}

	public void setActuators_status(Map<String, Boolean> actuators_status) {
		this.actuators_status = actuators_status;
	}

	public Map<String, BigInteger> getActuators_status_time() {
		return actuators_status_time;
	}

	public void setActuators_status_time(Map<String, BigInteger> actuators_status_time) {
		this.actuators_status_time = actuators_status_time;
	}

	public GlobalProperties getGlobalProperties() {
		return globalProperties;
	}

	public void setGlobalProperties(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	public static ClimatizationEventHandler getInstance() {
		return instance;
	}

	public static void setInstance(ClimatizationEventHandler instance) {
		ClimatizationEventHandler.instance = instance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actuators_list == null) ? 0 : actuators_list.hashCode());
		result = prime * result + ((actuators_status == null) ? 0 : actuators_status.hashCode());
		result = prime * result + ((actuators_status_time == null) ? 0 : actuators_status_time.hashCode());
		result = prime * result + ((climatization_event_list == null) ? 0 : climatization_event_list.hashCode());
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
		if (actuators_status_time == null) {
			if (other.actuators_status_time != null)
				return false;
		} else if (!actuators_status_time.equals(other.actuators_status_time))
			return false;
		if (climatization_event_list == null) {
			if (other.climatization_event_list != null)
				return false;
		} else if (!climatization_event_list.equals(other.climatization_event_list))
			return false;
		return true;
	}


}

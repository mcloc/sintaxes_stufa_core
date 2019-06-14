package br.com.sintechs.stufa.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;

public class ClimatizationEventStack {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClimatizationEventStack.class);
	
	private List<ClimatizationEvent> climatization_event_list = new ArrayList<ClimatizationEvent>();
	private List<String> active_modules_list = new ArrayList<String>();
	private List<String> stacked_modules_list = new ArrayList<String>();
	private Map<String,List<String>> modules_sensors_map = new HashMap<String,List<String>>();
	private Map<String,List<String>> modules_actuators_map = new HashMap<String,List<String>>();
	private Map<String,List<String>> sensors_measure_type_map = new HashMap<String,List<String>>();
	private Map<String,List<String>> stacked_modules_actuators_map = new HashMap<String,List<String>>();
	private Map<String,List<String>> stacked_modules_sensors_map = new HashMap<String,List<String>>();
	private Map<String,List<String>> stacked_sensors_measure_type_map = new HashMap<String,List<String>>();
	private boolean ready = false;

	private GlobalProperties globalProperties;
	
	private static ClimatizationEventStack instance = null;
	
	public ClimatizationEventStack(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
		this.active_modules_list = globalProperties.getCLIMATIZATION_MODULES();
		this.modules_sensors_map = globalProperties.getCLIMATIZATION_MODULES_SENSORS();
		this.modules_actuators_map = globalProperties.getCLIMATIZATION_MODULES_ACTUATORS();
		this.sensors_measure_type_map = globalProperties.getCLIMATIZATION_SENSORS_MEASURE_TYPES();
	}
	
	public static ClimatizationEventStack initialize(GlobalProperties globalProperties) {
		if (instance == null)
			instance = new ClimatizationEventStack(globalProperties);

		return instance;
	}
	
	public static void reset() {
		instance.setStacked_modules_list(new ArrayList<String>());
		instance.setStacked_modules_actuators_map(new HashMap<String,List<String>>());
		instance.setStacked_modules_sensors_map(new HashMap<String,List<String>>());
		instance.setStacked_sensors_measure_type_map(new HashMap<String,List<String>>());
		instance.setClimatization_event_list(new ArrayList<ClimatizationEvent>());
		instance.setReady(false);
	}
	
	public static ClimatizationEventStack addEventSensor(ClimatizationEvent new_ce) {
		boolean ce_exists = false;
		for(int i =0; i < instance.climatization_event_list.size();i++) {
			
			//THERE IS ALREADY AN CEVENT FOR THE NEW EV MODULE AND SAMPLING AND SENSOR, SO JUST UPDATE MEASURE_TYPE VALUES
			if(instance.climatization_event_list.get(i).getModule().equals(new_ce.getModule()) &&
				instance.climatization_event_list.get(i).getSampling().equals(new_ce.getSampling())) {
				
				//SENSOR ALREADY EXISTS, UPDATE MEASURE_TYPE
				if(instance.climatization_event_list.get(i).getSensor_uuid().equals(new_ce.getSensor_uuid())) {
					//REMOVE OLD_CE TO UPDATE IT
					ClimatizationEvent ce = instance.climatization_event_list.remove(i);
					switch (new_ce.getTmp_measure_type()) {
					case "heat_index":
						ce.setHeat_index(new_ce.getHeat_index());
						ce.setRule_condition_heat_index(new_ce.getRule_condition_heat_index());
						ce_exists = true;
						break;
					case "temperature":
						ce.setTemperature(new_ce.getTemperature());
						ce.setRule_condition_temperature(new_ce.getRule_condition_temperature());
						ce_exists = true;
						break;
					case "humidity":
						ce.setHumidity(new_ce.getHumidity());
						ce.setRule_condition_humidity(new_ce.getRule_condition_humidity());
						ce_exists = true;
						break;
					}
					
					//ADD THE UPDATED CEVENT WITH THE NEW MEASURE_TYPE
					instance.climatization_event_list.add(ce);
					break; // break the loop
				}
			}
		} // AND OF LOOP in instance.climatization_event_list
		
		//THE NEW_CE DON'T EXISTS ON THE LIST SO ADD IT
		if(!ce_exists) {
			instance.climatization_event_list.add(new_ce);
		}
		
		if(!instance.stacked_modules_list.contains(new_ce.getModule().getName()))
			instance.stacked_modules_list.add(new_ce.getModule().getName());

		//Check if the module already are set in the map
		if(!instance.stacked_modules_sensors_map.containsKey(new_ce.getModule().getName())){
			//If not create a new List with the Sensor_uuid
			List<String> tmp_list = new ArrayList<String>();
			tmp_list.add(new_ce.getSensor_uuid());
			instance.stacked_modules_sensors_map.put(new_ce.getModule().getName(), tmp_list);
		} else { 
			// Modules Exists but Sensor_uuid not, so add it on the list
			instance.stacked_modules_sensors_map.get(new_ce.getModule().getName()).add(new_ce.getSensor_uuid());
		}
		
		//Check if the measure_type already are set in the map
		if(!instance.stacked_sensors_measure_type_map.containsKey(new_ce.getSensor_uuid())) {
			//If not create a new List with the Measure_type
			List<String> tmp_list = new ArrayList<String>();
			tmp_list.add( new_ce.getTmp_measure_type());
			instance.stacked_sensors_measure_type_map.put(new_ce.getSensor_uuid(),tmp_list);
		} else {
			// Sensor Exists but Measure_type not, so add it on the list
			instance.stacked_sensors_measure_type_map.get(new_ce.getSensor_uuid()).add(new_ce.getTmp_measure_type());
		}
		
		instance.checkReady();
		
		return instance;
	}

	
	public static ClimatizationEventStack addEventActuator(ClimatizationEvent new_ce) {
		boolean ce_exists = false;
		for(int i =0; i < instance.climatization_event_list.size();i++) {
			
			//THERE IS ALREADY AN CEVENT FOR THE NEW EV MODULE AND SAMPLING AND SENSOR, SO JUST UPDATE MEASURE_TYPE VALUES
			if(instance.climatization_event_list.get(i).getModule().equals(new_ce.getModule()) &&
				instance.climatization_event_list.get(i).getSampling().equals(new_ce.getSampling())) {
				
				//SENSOR ALREADY EXISTS, UPDATE MEASURE_TYPE
				if(instance.climatization_event_list.get(i).getActuator_uuid() != null &&
						instance.climatization_event_list.get(i).getActuator_uuid().equals(new_ce.getActuator_uuid())) {
					ce_exists = true;
					break; // break the loop
				}
			}
		} // AND OF LOOP in instance.climatization_event_list
		
		//THE NEW_CE DON'T EXISTS ON THE LIST SO ADD IT
		if(!ce_exists) {
			instance.climatization_event_list.add(new_ce);
		}
		
		if(!instance.stacked_modules_list.contains(new_ce.getModule().getName()))
			instance.stacked_modules_list.add(new_ce.getModule().getName());

		//Check if the module already are set in the map
		if(!instance.stacked_modules_actuators_map.containsKey(new_ce.getModule().getName())){
			//If not create a new List with the Actuator_uuid
			List<String> tmp_list = new ArrayList<String>();
			tmp_list.add(new_ce.getActuator_uuid());
			instance.stacked_modules_actuators_map.put(new_ce.getModule().getName(), tmp_list);
		} else { 
			// Modules Exists but Sensor_uuid not, so add it on the list
			instance.stacked_modules_actuators_map.get(new_ce.getModule().getName()).add(new_ce.getActuator_uuid());
		}
		
		//Check if the module already are set in the map
		if(!instance.stacked_modules_actuators_map.containsKey(new_ce.getModule().getName())){
			//If not create a new List with the Sensor_uuid
			List<String> tmp_list = new ArrayList<String>();
			tmp_list.add(new_ce.getSensor_uuid());
			instance.stacked_modules_actuators_map.put(new_ce.getModule().getName(), tmp_list);
		} else { 
			// Modules Exists but Sensor_uuid not, so add it on the list
			instance.stacked_modules_actuators_map.get(new_ce.getModule().getName()).add(new_ce.getSensor_uuid());
		}
		
		instance.checkReady();
		
		return instance;
	}
	
	private void checkReady() {
		if(instance.stacked_modules_list.containsAll(instance.active_modules_list)) {
			boolean contains_all_sensors = false;
			boolean contains_all_actuators = false;
			for(Entry<String,List<String>> entry : instance.stacked_modules_sensors_map.entrySet()){
				if(!entry.getValue().containsAll(instance.modules_sensors_map.get(entry.getKey()))) {
					contains_all_sensors = false;
					break;
				}
				contains_all_sensors = true;
			}
			
			for(Entry<String,List<String>> entry : instance.stacked_modules_actuators_map.entrySet()){
				if(!entry.getValue().containsAll(instance.modules_actuators_map.get(entry.getKey()))) {
					contains_all_actuators = false;
					break;
				}
				contains_all_actuators = true;
			}
			
			if(contains_all_sensors) {
				boolean contains_all_sensors_measure_types = true;
				for(Entry<String,List<String>> entry_sensor : instance.stacked_modules_sensors_map.entrySet()){
					for(String sensor_uuid : entry_sensor.getValue()) {
						for(Entry<String,List<String>> entry_measure_type : instance.stacked_sensors_measure_type_map.entrySet()){
							if(!entry_measure_type.getValue().containsAll(instance.sensors_measure_type_map.get(sensor_uuid))) {
								contains_all_sensors_measure_types = false;
								break;
							}
						}	
					}
				}
				
				if(contains_all_sensors_measure_types && contains_all_actuators)
					instance.ready = true;
			}
		}
	}

	public List<ClimatizationEvent> getClimatization_event_list() {
		return this.climatization_event_list;
	}

	public void setClimatization_event_list(List<ClimatizationEvent> climatization_event_list) {
		this.climatization_event_list = climatization_event_list;
	}

	public boolean isReady() {
		return this.ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public GlobalProperties getGlobalProperties() {
		return this.globalProperties;
	}

	public void setGlobalProperties(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	public List<String> getActive_modules_list() {
		return active_modules_list;
	}

	public void setActive_modules_list(List<String> active_modules_list) {
		this.active_modules_list = active_modules_list;
	}

	public List<String> getStacked_modules_list() {
		return stacked_modules_list;
	}

	public void setStacked_modules_list(List<String> stacked_modules_list) {
		this.stacked_modules_list = stacked_modules_list;
	}

	public Map<String, List<String>> getModules_sensors_map() {
		return modules_sensors_map;
	}

	public void setModules_sensors_map(Map<String, List<String>> modules_sensors_map) {
		this.modules_sensors_map = modules_sensors_map;
	}
	
	public Map<String, List<String>> getModules_actuators_map() {
		return modules_actuators_map;
	}

	public void setModules_actuators_map(Map<String, List<String>> modules_actuators_map) {
		this.modules_actuators_map = modules_actuators_map;
	}

	public Map<String, List<String>> getSensors_measure_type_map() {
		return sensors_measure_type_map;
	}

	public void setSensors_measure_type_map(Map<String, List<String>> sensors_measure_type_map) {
		this.sensors_measure_type_map = sensors_measure_type_map;
	}

	public Map<String, List<String>> getStacked_modules_sensors_map() {
		return stacked_modules_sensors_map;
	}

	public void setStacked_modules_sensors_map(Map<String, List<String>> stacked_modules_sensors_map) {
		this.stacked_modules_sensors_map = stacked_modules_sensors_map;
	}

	public Map<String, List<String>> getStacked_sensors_measure_type_map() {
		return stacked_sensors_measure_type_map;
	}

	public void setStacked_sensors_measure_type_map(Map<String, List<String>> stacked_sensors_measure_type_map) {
		this.stacked_sensors_measure_type_map = stacked_sensors_measure_type_map;
	}

	public static ClimatizationEventStack getInstance() {
		return instance;
	}

	public static void setInstance(ClimatizationEventStack instance) {
		ClimatizationEventStack.instance = instance;
	}

	public Map<String, List<String>> getStacked_modules_actuators_map() {
		return stacked_modules_actuators_map;
	}

	public void setStacked_modules_actuators_map(Map<String, List<String>> stacked_modules_actuators_map) {
		this.stacked_modules_actuators_map = stacked_modules_actuators_map;
	}


	
	
	
}

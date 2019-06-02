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
	private Map<String,List<String>> sensors_measure_type_map = new HashMap<String,List<String>>();
	private Map<String,List<String>> stacked_modules_sensors_map = new HashMap<String,List<String>>();
	private Map<String,List<String>> stacked_sensors_measure_type_map = new HashMap<String,List<String>>();	
	private boolean ready = false;

	private GlobalProperties globalProperties;
	
	public ClimatizationEventStack(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
		this.active_modules_list = globalProperties.getCLIMATIZATION_MODULES();
		this.modules_sensors_map = globalProperties.getCLIMATIZATION_MODULES_SENSORS();
		this.sensors_measure_type_map = globalProperties.getCLIMATIZATION_SENSORS_MEASURE_TYPES();
	}
	
	public void reset() {
		this.stacked_modules_list = new ArrayList<String>();
		this.stacked_modules_sensors_map = new HashMap<String,List<String>>();
		this.stacked_sensors_measure_type_map = new HashMap<String,List<String>>();
		this.climatization_event_list = new ArrayList<ClimatizationEvent>();
		this.ready = false;
	}
	
	public void addEvent(ClimatizationEvent new_ce) {
		boolean ce_exists = false;
		for(int i =0; i < climatization_event_list.size();i++) {
			
			//THERE IS ALREADY AN CEVENT FOR THE NEW EV MODULE AND SAMPLING AND SENSOR, SO JUST UPDATE MEASURE_TYPE VALUES
			if(climatization_event_list.get(i).getModule().equals(new_ce.getModule()) &&
				climatization_event_list.get(i).getSampling().equals(new_ce.getSampling())) {
				
				//SENSOR ALREADY EXISTS, UPDATE MEASURE_TYPE
				if(climatization_event_list.get(i).getSensor_uuid().equals(new_ce.getSensor_uuid())) {
					//REMOVE OLD_CE TO UPDATE IT
					ClimatizationEvent ce = climatization_event_list.remove(i);
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
					climatization_event_list.add(ce);
					break; // break the loop
				} else { // SENSOR DONT EXISTS
					climatization_event_list.add(new_ce);
//					climatization_event_list.get(i).getSampling().put(new_ce.getSampling(), )
				}
				
				
			}
		} // AND OF LOOP in climatization_event_list
		
		//THE NEW_CE DON'T EXISTS ON THE LIST SO ADD IT
		if(!ce_exists) {
			climatization_event_list.add(new_ce);
		}
		
		if(!stacked_modules_list.contains(new_ce.getModule().getName()))
			stacked_modules_list.add(new_ce.getModule().getName());

		//Check if the module already are set in the map
		if(!stacked_modules_sensors_map.containsKey(new_ce.getModule().getName())){
			//If not create a new List with the Sensor_uuid
			List<String> tmp_list = new ArrayList<String>();
			tmp_list.add(new_ce.getSensor_uuid());
			stacked_modules_sensors_map.put(new_ce.getModule().getName(), tmp_list);
		} else if(!stacked_modules_sensors_map.get(new_ce.getModule().getName()).contains(new_ce.getSensor_uuid())) { 
			// Modules Exists but Sensor_uuid not, so add it on the list
			stacked_modules_sensors_map.get(new_ce.getModule().getName()).add(new_ce.getSensor_uuid());
		}
		
		//Check if the measure_type already are set in the map
		if(!stacked_sensors_measure_type_map.containsKey(new_ce.getSensor_uuid())) {
			//If not create a new List with the Measure_type
			List<String> tmp_list = new ArrayList<String>();
			tmp_list.add( new_ce.getTmp_measure_type());
			stacked_sensors_measure_type_map.put(new_ce.getSensor_uuid(),tmp_list);
		} else if(stacked_sensors_measure_type_map.get(new_ce.getSensor_uuid()).contains(new_ce.getTmp_measure_type())) {
			// Sensor Exists but Measure_type not, so add it on the list
			stacked_sensors_measure_type_map.get(new_ce.getSensor_uuid()).add(new_ce.getTmp_measure_type());
		}
		
		
		if(stacked_modules_list.containsAll(active_modules_list)) {
			boolean contains_all_sensors = true;
			for(Entry<String,List<String>> entry : stacked_modules_sensors_map.entrySet()){
				if(!entry.getValue().containsAll(modules_sensors_map.get(entry.getKey()))) {
					contains_all_sensors = false;
					break;
				}
			}
			
			if(contains_all_sensors) {
				boolean contains_all_sensors_measure_types = true;
				for(Entry<String,List<String>> entry_sensor : stacked_modules_sensors_map.entrySet()){
					for(Entry<String,List<String>> entry_measure_type : stacked_sensors_measure_type_map.entrySet()){
						if(!entry_measure_type.getValue().containsAll(sensors_measure_type_map.get(entry_sensor.getKey()))) {
							contains_all_sensors_measure_types = false;
							break;
						}
					}	
				}
				
				if(contains_all_sensors_measure_types)
					ready = true;
			}
				
		}
	}

	public List<ClimatizationEvent> getClimatization_event_list() {
		return climatization_event_list;
	}

	public void setClimatization_event_list(List<ClimatizationEvent> climatization_event_list) {
		this.climatization_event_list = climatization_event_list;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
	
	
}

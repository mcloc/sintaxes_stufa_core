package br.com.sintechs.stufa.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;

public class ClimatizationEventStack {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClimatizationEventStack.class);
	
	private List<ClimatizationEvent> climatization_event_list = new ArrayList<ClimatizationEvent>();
	private List<String> active_modules_list = new ArrayList<String>();
	private List<String> stacked_modules_list = new ArrayList<String>();
	private Map<String,String> modules_sensors_map = new HashMap<String,String>();
	private Map<String,String> stacked_modules_sensors_map = new HashMap<String,String>();	
	private boolean ready = false;

	private GlobalProperties globalProperties;
	
	public ClimatizationEventStack(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
		this.active_modules_list = globalProperties.getCLIMATIZATION_MODULES();
		this.modules_sensors_map = globalProperties.getCLIMATIZATION_MODULES_SENSORS();
	}
	
	public void reset() {
		this.stacked_modules_list = new ArrayList<String>();
		this.stacked_modules_sensors_map = new HashMap<String,String>();
		this.climatization_event_list = new ArrayList<ClimatizationEvent>();
		ready = false;
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
					if(!stacked_modules_list.contains(new_ce.getModule().getName()))
						stacked_modules_list.add(new_ce.getModule().getName());
					
					if(!stacked_modules_sensors_map.containsValue(new_ce.getSensor_uuid()))
						stacked_modules_sensors_map.put(new_ce.getModule().getName(), new_ce.getSensor_uuid());		
				}
			}
		} // AND OF LOOP in climatization_event_list
		
		//THE NEW_CE DON'T EXISTS ON THE LIST SO ADD IT
		if(!ce_exists) {
			climatization_event_list.add(new_ce);
			if(!stacked_modules_list.contains(new_ce.getModule().getName()))
				stacked_modules_list.add(new_ce.getModule().getName());
			if(!stacked_modules_sensors_map.containsValue(new_ce.getSensor_uuid()))
				stacked_modules_sensors_map.put(new_ce.getModule().getName(), new_ce.getSensor_uuid());			
			
		}
		
		if(stacked_modules_list.containsAll(active_modules_list) && stacked_modules_sensors_map.values().containsAll(modules_sensors_map.values()))
			ready = true;
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

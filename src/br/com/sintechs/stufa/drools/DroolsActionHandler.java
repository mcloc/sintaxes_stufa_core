package br.com.sintechs.stufa.drools;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.models.RuleEvent;
import br.com.sintechs.stufa.rest.RESTClient;

public class DroolsActionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(DroolsActionHandler.class);
	private GlobalProperties globalProperties;
	
	public DroolsActionHandler(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}
	
	
	public boolean checkPreviousEvent(RuleEvent ev) {
		RESTClient rClient = new RESTClient(globalProperties);
		RuleEvent last_event = rClient.getLastEventForSensor(ev.getSensor_uuid());
		
		if(last_event.getValue() == ev.getValue())
			return false;
		
		return true;
	}
	
	public void dispatchEvent(RuleEvent ev) {
		JSONObject json_obj = new JSONObject(ev);
		RESTClient rClient = new RESTClient(globalProperties);
		rClient.postRuleEvent(json_obj);
	}
	
	public void addAlert(String message) {
		LOGGER.info(message);
	}

}

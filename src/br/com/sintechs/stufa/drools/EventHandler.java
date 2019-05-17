package br.com.sintechs.stufa.drools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sintechs.stufa.models.RuleEvent;
import br.com.sintechs.stufa.models.SintechsSampling;

public class EventHandler {
	private Map<SintechsSampling, List<RuleEvent>> ruleEvents;
	
	public EventHandler() {
		ruleEvents = new HashMap<SintechsSampling, List<RuleEvent>>();
	}
	
	
	public void addRuleEvent(SintechsSampling sampling, RuleEvent ev) {
		if(!ruleEvents.containsKey(sampling)) {
			List<RuleEvent> list = new ArrayList<RuleEvent>();
			ruleEvents.put(sampling, list);
		}
		List<RuleEvent> _list = ruleEvents.remove(sampling);
		_list.add(ev);
		ruleEvents.put(sampling, _list);
	}
	
	public void removeSampling(SintechsSampling sampling) {
		if(ruleEvents.containsKey(sampling))
			ruleEvents.remove(sampling);
	}


	public Map<SintechsSampling, List<RuleEvent>> getRuleEvents() {
		return ruleEvents;
	}


	public void setRuleEvents(Map<SintechsSampling, List<RuleEvent>> ruleEvents) {
		this.ruleEvents = ruleEvents;
	}



	
	
	

}

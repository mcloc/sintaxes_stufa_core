package br.com.vger.stufa.drools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.vger.stufa.models.RuleEvent;
import br.com.vger.stufa.models.VgerSampling;

public class EventHandler {
	private Map<VgerSampling, List<RuleEvent>> ruleEvents;
	
	public EventHandler() {
		ruleEvents = new HashMap<VgerSampling, List<RuleEvent>>();
	}
	
	
	public void addRuleEvent(VgerSampling sampling, RuleEvent ev) {
		if(!ruleEvents.containsKey(sampling)) {
			List<RuleEvent> list = new ArrayList<RuleEvent>();
			ruleEvents.put(sampling, list);
		}
		List<RuleEvent> _list = ruleEvents.remove(sampling);
		_list.add(ev);
		ruleEvents.put(sampling, _list);
	}
	
	public void removeSampling(VgerSampling sampling) {
		if(ruleEvents.containsKey(sampling))
			ruleEvents.remove(sampling);
	}


	public Map<VgerSampling, List<RuleEvent>> getRuleEvents() {
		return ruleEvents;
	}


	public void setRuleEvents(Map<VgerSampling, List<RuleEvent>> ruleEvents) {
		this.ruleEvents = ruleEvents;
	}



	
	
	

}

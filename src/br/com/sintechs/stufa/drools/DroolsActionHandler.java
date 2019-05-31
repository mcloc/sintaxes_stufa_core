package br.com.sintechs.stufa.drools;

import org.json.JSONObject;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.models.RuleEvent;
import br.com.sintechs.stufa.rest.RESTClient;

public class DroolsActionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(DroolsActionHandler.class);
	private GlobalProperties globalProperties;
	private KieSession drools_session;
	private FactHandle climatization_fact_handler;

	public DroolsActionHandler(GlobalProperties globalProperties, KieSession kieSession,
			FactHandle climatization_fact_handler) {
		this.globalProperties = globalProperties;
		this.drools_session = kieSession;
		this.climatization_fact_handler = climatization_fact_handler;
	}

	public boolean checkPreviousEvent(RuleEvent ev) {
		RESTClient rClient = new RESTClient(globalProperties);
		RuleEvent last_event = rClient.getLastEventForSensor(ev.getSensor_uuid());

		if (last_event == null)
			return true;

		if (last_event.getValue() == ev.getValue())
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

	public GlobalProperties getGlobalProperties() {
		return globalProperties;
	}

	public void setGlobalProperties(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	public KieSession getDrools_session() {
		return drools_session;
	}

	public void setDrools_session(KieSession drools_session) {
		this.drools_session = drools_session;
	}

	public FactHandle getClimatization_fact_handler() {
		return climatization_fact_handler;
	}

	public void setClimatization_fact_handler(FactHandle climatization_fact_handler) {
		this.climatization_fact_handler = climatization_fact_handler;
	}

}

package br.com.sintechs.stufa.drools;


import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugEventListener extends DefaultAgendaEventListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(DebugEventListener.class);

	@Override
	public void afterMatchFired(AfterMatchFiredEvent event) {
		Rule rule = event.getMatch().getRule();
		LOGGER.info("drools: Rule fired: " + rule.getName());
	}
}

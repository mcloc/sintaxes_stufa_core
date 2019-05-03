package br.com.sintechs.stufa.drools;

import java.util.logging.Logger;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;

public class ExpertSystemHandler extends Thread{
	private GlobalProperties globalProperties;
	private Logger _log;
	private IPCWriteInterrupt writeInterrupt;
	private KieSession kieSession;
	private EntryPoint samplingStream;

	public ExpertSystemHandler(GlobalProperties globalProperties, Logger _log,
			IPCWriteInterrupt writeInterrupt) {
		this.globalProperties = globalProperties;
		this._log = _log;
		this.writeInterrupt = writeInterrupt;
	}
	
	public void run() {
		_log.info("drools: Initialize KIE.");
//		KieBaseConfiguration config = KieServices.Factory.get().newKieBaseConfiguration();
//		config.setOption( EventProcessingOption.STREAM );
		KieServices kieServices = KieServices.Factory.get();
		// Load KieContainer from resources on classpath (i.e. kmodule.xml and rules).
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// Initializing KieSession.
		_log.info("drools: Creating KieSession.");
		 kieSession = kieContainer.newKieSession();
		 samplingStream = kieSession.getEntryPoint( "StufaSampingStream" );
		 kieSession.fireUntilHalt();

	}
	
//	public void addSampling(){
//		samplingStream.insert(object);
//	}
}

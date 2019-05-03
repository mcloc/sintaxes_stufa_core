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
	
	@Override
	public void run() {
		while(true) {
			try {
				_log.info("drools: Initialize KIE.");
				if(kieSession instanceof KieSession)
					kieSession.dispose();
				
				KieServices kieServices = KieServices.Factory.get();
				// Load KieContainer from resources on classpath (i.e. kmodule.xml and rules).
				KieContainer kieContainer = kieServices.getKieClasspathContainer();
		
				// Initializing KieSession.
				_log.info("drools: Creating KieSession.");
				 kieSession = kieContainer.newKieSession("ksession-rules");
				 samplingStream = kieSession.getEntryPoint( "StufaSampingStream" );
			} catch (Exception e ) {
				_log.severe(e.getMessage());
				if(kieSession != null)
				kieSession.dispose();
				continue;
			}
			 kieSession.fireUntilHalt();
		}
	}
	
//	public void addSampling(){
//		samplingStream.insert(object);
//	}
}

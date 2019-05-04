package br.com.sintechs.stufa.drools;


import java.math.BigInteger;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;
import br.com.sintechs.stufa.models.SintechsSampling;
import br.com.sintechs.stufa.models.Utils;

public class ExpertSystemHandler extends Thread{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExpertSystemHandler.class);
	private GlobalProperties globalProperties;
	private IPCWriteInterrupt writeInterrupt;
	private KieSession kieSession;
	private EntryPoint samplingStream;

	public ExpertSystemHandler(GlobalProperties globalProperties, IPCWriteInterrupt writeInterrupt) {
		this.globalProperties = globalProperties;
		this.writeInterrupt = writeInterrupt;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				LOGGER.info("Initialize KIE.");
				if(kieSession instanceof KieSession)
					kieSession.dispose();
				
				KieServices kieServices = KieServices.Factory.get();
				// Load KieContainer from resources on classpath (i.e. kmodule.xml and rules).
				KieContainer kieContainer = kieServices.getKieClasspathContainer();
		
				// Initializing KieSession.
				LOGGER.info("Creating KieSession.");
				 kieSession = kieContainer.newKieSession("ksession-rules");
				 kieSession.addEventListener(new DebugEventListener());
				 samplingStream = kieSession.getEntryPoint( "StufaSampingStream" );
			} catch (Exception e ) {
				LOGGER.error(e.getMessage());
				if(kieSession != null)
				kieSession.dispose();
				continue;
			}
			 kieSession.fireUntilHalt();
		}
	}
	
	public synchronized void  addSampling(SintechsSampling sampling){
		LOGGER.info("inserting samping: "+sampling.getId()+" into EntryPoint");
		samplingStream.insert(sampling);
	}
}

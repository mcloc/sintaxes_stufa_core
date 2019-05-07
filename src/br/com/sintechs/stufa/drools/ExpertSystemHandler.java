package br.com.sintechs.stufa.drools;


import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;
import br.com.sintechs.stufa.models.SintechsSampling;

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
				 KieBaseConfiguration config = kieServices.newKieBaseConfiguration();
		            config.setOption( EventProcessingOption.STREAM );
//				KieBaseConfiguration config = KieServices.Factory.get().newKieBaseConfiguration();
//				config.setOption( EventProcessingOption.STREAM );
		
				// Initializing KieSession.
				LOGGER.info("Creating KieSession.");
				 kieSession = kieContainer.newKieSession("ksession-rules");
				 kieSession.addEventListener(new DebugEventListener());
				 
				 
//				 Collection<KiePackage> x = kieSession.getKieBase().getKiePackages();
//				 DroolsActionHandler drlActionHandler = new DroolsActionHandler();
//				 kieSession.insert(drlActionHandler);
				 samplingStream = kieSession.getEntryPoint( "StufaSampingStream" );

			} catch (Exception e ) {
				LOGGER.error(e.getMessage());
				if(kieSession != null)
					kieSession.dispose();
				continue;
			}
			kieSession.fireUntilHalt();
//			new Thread() {
//				 
//		        @Override
//		        public void run() {
//		        	 kieSession.fireUntilHalt();
//		        }
//		    }.start();
			
		}
	}
	
	public synchronized void addSampling(SintechsSampling sampling){
		try {
			LOGGER.info("inserting samping: "+sampling.getId()+" into EntryPoint");
			samplingStream.insert(sampling);
		} catch (Exception e ) {
			e.getStackTrace();
			LOGGER.error(e.getMessage());
		}
	}
}

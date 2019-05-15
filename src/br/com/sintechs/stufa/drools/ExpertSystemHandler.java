package br.com.sintechs.stufa.drools;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;
import br.com.sintechs.stufa.models.SintechsSampling;

public class ExpertSystemHandler extends Thread {
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
		// This is an embedded app with Auto Crash Recovery, in the real app this
		// while(true) will be used;
//		while (true) {
			try {
				LOGGER.info("Initialize KIE.");
				if (kieSession instanceof KieSession)
					kieSession.dispose();

				KieServices kieServices = KieServices.Factory.get();
				// Load KieContainer from resources on classpath (i.e. kmodule.xml and rules).
				KieContainer kieContainer = kieServices.getKieClasspathContainer();
				KieBaseConfiguration config = kieServices.newKieBaseConfiguration();
				config.setOption(EventProcessingOption.STREAM);

				// Initializing KieSession.
				LOGGER.info("Creating KieSession.");
				kieSession = kieContainer.newKieSession("ksession-rules");
				kieSession.addEventListener(new DebugEventListener());

				// Collection<KiePackage> x = kieSession.getKieBase().getKiePackages();
				DroolsActionHandler drlActionHandler = new DroolsActionHandler(globalProperties);
				kieSession.setGlobal("drlActionHandler", drlActionHandler);
				
//				samplingStream = kieSession.getEntryPoint("StufaSampingStream");
				// WorkingMemoryEntryPoint entryPoint = kieSession.getEntryPoint("my entry point");

			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				e.getStackTrace();
				if (kieSession != null) {
					kieSession.dispose();
					kieSession.halt();
				}
//				 continue;
				 return;
			}

			// FIXME: this fireUntilHalt should be the right statement in this right place,
			// we're already in another thread dedicated for drools
			// kieSession.fireAllRules();

			// FIXME: I think there's no need to create another thread.
			new Thread() {

				@Override
				public void run() {
					kieSession.fireUntilHalt();
				}
			}.start();

//		} // Bracket of the while(true) auto crash recovery
	}

	// This is the real addSampling method called in the serialComm class
	// It's not been called any time in this TEST DEBUG version
	// Check the method below
	public synchronized void addSampling(SintechsSampling sampling) {
		try {
			LOGGER.info("inserting samping: " + sampling.getHashCode() + " into KieSession");
			kieSession.insert(sampling);
		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.error(e.getMessage());
		}
	}

	// This is a test Method so you guys can see that an Exception with no message
	// and no stacktrace
	// Just put a breakPoint on samplingStream.insert(nextInt);
//	public void addEvent(int nextInt) {
//		try {
//			LOGGER.info("inserting test event: " + nextInt + " into EntryPoint");
//			kieSession.insert(nextInt);
//		} catch (Exception e) {
//			e.getStackTrace();
//			LOGGER.error(e.getMessage());
//		}
//
//	}
}

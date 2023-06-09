package br.com.vger.stufa.drools;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.vger.stufa.GlobalProperties;
import br.com.vger.stufa.ipc.IPCWriteInterrupt;
import br.com.vger.stufa.models.ClimatizationEventHandler;
import br.com.vger.stufa.models.ClimatizationEventStack;
import br.com.vger.stufa.models.VgerModule;
import br.com.vger.stufa.models.VgerSamplingPack;

public class ExpertSystemHandler extends Thread {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExpertSystemHandler.class);
	private GlobalProperties globalProperties;
	private IPCWriteInterrupt writeInterrupt;

	private KieSession kieSession;
	private EntryPoint samplingStream;
	private List<VgerModule> activeModules = new ArrayList<VgerModule>();
	private EntryPoint climatizationStream;

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
				if (kieSession instanceof KieSession) {
					kieSession.dispose();
					kieSession.halt();
				}

				KieServices kieServices = KieServices.Factory.get();
				// Load KieContainer from resources on classpath (i.e. kmodule.xml and rules).
				KieContainer kieContainer = kieServices.getKieClasspathContainer();
				KieBaseConfiguration config = kieServices.newKieBaseConfiguration();
				config.setOption(EventProcessingOption.STREAM);

				// Initializing KieSession.
				LOGGER.info("Creating KieSession.");
				kieSession = kieContainer.newKieSession("ksession-rules");
				kieSession.addEventListener(new DebugEventListener());
				
				ClimatizationEventStack cEventStack = ClimatizationEventStack.initialize(globalProperties);
				samplingStream = kieSession.getEntryPoint("StufaSamplingStream");
				samplingStream.insert(cEventStack);
//				kieSession.setGlobal("climatizationEventStack", cEventStack);

				ClimatizationEventHandler climatizationEventHandler = ClimatizationEventHandler.initialize(globalProperties);
				climatizationStream = kieSession.getEntryPoint("StufaClimatizationStream");
				climatizationStream.insert(climatizationEventHandler);
//				kieSession.setGlobal("climatizationEventHandler", climatizationEventHandler);
				
				DroolsActionHandler drlActionHandler = new DroolsActionHandler(globalProperties, kieSession, 
						climatizationStream.getFactHandle(climatizationEventHandler), samplingStream.getFactHandle(cEventStack));
//				DroolsActionHandler drlActionHandler = new DroolsActionHandler(globalProperties, kieSession);
				kieSession.setGlobal("drlActionHandler", drlActionHandler);
				
				EventHandler eventHandler = new EventHandler();
				kieSession.setGlobal("eventHandler", eventHandler);
				

				
//				Collection<org.kie.api.runtime.rule.FactHandle> cFH;
//				Iterator it;
//				cFH = climatizationStream.getFactHandles();
//				it = cFH.iterator();
//				while (it.hasNext()) {
//					System.out.println(it.next() + "aaaaaaaaaaaaaaaaa \n ");;
//				}
				
				

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

			// FIXME: I think there's no need to create another thread.: YES THERE IS !!!
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
//	public synchronized void addSampling(VgerSampling sampling) {
//		try {
//			LOGGER.info("inserting sampling: " + sampling.hashCode() + " into Kiesession.samplingStream");
//			
////			kieSession.getEntryPoint("StufaSamplingStream").insert(sampling); //bEAUTY .ar
//			samplingStream.insert(sampling);
////			kieSession.insert(sampling);
//		} catch (Exception e) {
//			e.getStackTrace();
//			LOGGER.error(e.getMessage());
//		}
//	}
	
	public void addSamplingPack(VgerSamplingPack samplingPack) {
		try {
			LOGGER.info("inserting samplingPack: " + samplingPack.hashCode() + " into Kiesession.samplingStream");
			samplingStream.insert(samplingPack);
		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.error(e.getMessage());
		}
	}

//	public void addSamplingList(List<VgerSampling> samplingList) {
//		try {
//			LOGGER.info("inserting samping: " + samplingList.hashCode() + " into KieSession");
//			samplingStream.insert(samplingList);
//		} catch (Exception e) {
//			e.getStackTrace();
//			LOGGER.error(e.getMessage());
//		}
//	}

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

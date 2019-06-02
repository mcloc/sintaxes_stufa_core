package br.com.sintechs.stufa.modules;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.drools.ExpertSystemHandler;
import br.com.sintechs.stufa.ipc.IPCWriteInterrupt;
import br.com.sintechs.stufa.models.SintechsModule;
import br.com.sintechs.stufa.models.SintechsSampling;
import br.com.sintechs.stufa.models.SintechsSamplingPack;
import br.com.sintechs.stufa.rest.RESTClient;

public class ModulesHandler extends Thread {
	
	
	protected GlobalProperties globalProperties;
	protected IPCWriteInterrupt ipcWriteInterrupt;
	private ExpertSystemHandler drools;
    private static final Logger LOGGER = LoggerFactory.getLogger(ModulesHandler.class);

	/**
	 * Pulling trought all arduino modules
	 * @param globalProperties
	 * @param writeInterrupt
	 * @param es 
	 */
	public ModulesHandler(GlobalProperties globalProperties, IPCWriteInterrupt writeInterrupt, ExpertSystemHandler es) {
		this.globalProperties = globalProperties;
		this.ipcWriteInterrupt = writeInterrupt;
		this.drools = es;
	}

	public void run() {
		
		while(true) {
			LOGGER.info("Starting ModulesHandler...");
			try {
				
				//TODO: change to get from GLobalProperties
				RESTClient client = new RESTClient(globalProperties);
				List<SintechsModule> modules = client.getModules(); 
				
//				List<SintechsSampling> samplingList = new ArrayList<SintechsSampling>();
				SintechsSamplingPack samplingPack = new SintechsSamplingPack();
				for (SintechsModule module : modules) {
					Thread.sleep(globalProperties.getREST_MODULE_SLEEP());	
					//TODO: check for enabled modules and LOG why there're unabled
					if(!module.isEnabled())
						continue;
					
					SintechsSampling sampling = client.getModuleSampling(module);
					LOGGER.info("Adding sampling: " + sampling.getUuid());
					samplingPack.getSampling_list().add(sampling);
					
					
					LOGGER.info("Iserting "+sampling.getModule().getName()+" sampling into WorkingMemory entryPoing ");
//					drools.addSampling(sampling);
					
					//TODO: Loop trough all modules and hidrate Sampling per module
					//TODO: insert List<Sampling> into drools
					Thread.sleep(500);
					break; // DEBUG check just the first module
				}
				
//				LOGGER.info("Iserting "+samplingList.size()+" samplings into WorkingMemory entryPoing ");
//				drools.addSamplingList(samplingList);
//				LOGGER.info("Iserting "+samplingPack.getSampling_list().size()+" samplings into WorkingMemory entryPoing ");
				drools.addSamplingPack(samplingPack);
				
				
			} catch (Exception e) {
				LOGGER.error("error on module: "+e.getMessage());
			}
		}
	}

}

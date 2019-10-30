package br.com.vger.stufa.modules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.vger.stufa.GlobalProperties;
import br.com.vger.stufa.drools.ExpertSystemHandler;
import br.com.vger.stufa.ipc.IPCWriteInterrupt;
import br.com.vger.stufa.models.VgerModule;
import br.com.vger.stufa.models.VgerSampling;
import br.com.vger.stufa.models.VgerSamplingPack;
import br.com.vger.stufa.rest.RESTClient;

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
				List<VgerModule> modules = client.getModules(); 
				
//				List<VgerSampling> samplingList = new ArrayList<VgerSampling>();
				VgerSamplingPack samplingPack = new VgerSamplingPack();
				for (VgerModule module : modules) {
					Thread.sleep(globalProperties.getREST_MODULE_SLEEP());	
					//TODO: check for enabled modules and LOG why there're unabled
					if(!module.isEnabled())
						continue;
					
					VgerSampling sampling = client.getModuleSampling(module);
					LOGGER.info("Adding sampling: " + sampling.getUuid() + " to samplingPack");
					samplingPack.getSampling_list().add(sampling);
					
					
//					LOGGER.info("Iserting "+sampling.getModule().getName()+" sampling into WorkingMemory entryPoing ");
//					drools.addSampling(sampling);
					
					//TODO: Loop trough all modules and hidrate Sampling per module
					//TODO: insert List<Sampling> into drools
					Thread.sleep(500);
					if(module.getName().contains("#1")) // DEBUG
						break; // DEBUG check just the first module
				}
				
				//TODO: ETL Here
				
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

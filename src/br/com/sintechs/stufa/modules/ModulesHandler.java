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
		
//		while(true) {
			LOGGER.info("Starting ModulesHandler...");
			try {
				
				//TODO: get All active Modules
				RESTClient client = new RESTClient(globalProperties);
				List<SintechsModule> modules = client.getModules(); 
				
				List<SintechsSampling> samplingList = new ArrayList<SintechsSampling>();
				for (SintechsModule module : modules) {
					//TODO: check for enabled modules and LOG why there're unabled
					if(!module.isEnabled())
						continue;
					
					SintechsSampling sampling = client.getModuleSampling(module);
					LOGGER.info("Adding sampling: " + sampling.getUuid());
					samplingList.add(sampling);
					//TODO: Loop trough all modules and hidrate Sampling per module
					//TODO: insert List<Sampling> into drools
				}
				
				LOGGER.info("Iserting "+samplingList.size()+" samplings into WorkingMemory entryPoing ");
				drools.addSamplingList(samplingList);

				
				
				
				
				Thread.sleep(2000);	
			} catch (Exception e) {
				LOGGER.error("error on module: "+e.getMessage());
			}
			
			
//		}

		
	}

}

package br.com.sintechs.stufa.rest;


import java.math.BigInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.serial.SerialComm;

public class RESTClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(RESTClient.class);
	private String json;
	private GlobalProperties globalProperties;
	
	public RESTClient(String json, GlobalProperties globalProperties) {
		this.json = json;
		this.globalProperties = globalProperties;
	}
	
	public BigInteger postSampling() {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		try {

		    HttpPost request = new HttpPost(globalProperties.getREST_API_STORE_SAMPLING_URL());
		    StringEntity requestEntity = new StringEntity(
		    		json,
		    	    ContentType.APPLICATION_JSON);
		    request.setEntity(requestEntity);
		    
		    LOGGER.info("Posting REST storeSampling");
		    HttpResponse response = httpClient.execute(request);
		    HttpEntity entity = response.getEntity();
		    String responseString = EntityUtils.toString(entity, "UTF-8");
		    LOGGER.info(responseString);
		    //TODO: handle response here...
		    //TODO: return sampling id
		    
		    return null;
		    

		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
		
		return null;
	}

}

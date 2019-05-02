package br.com.sintechs.stufa.rest;

import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import br.com.sintechs.stufa.GlobalProperties;

public class RESTClient {
	
	private String json;
	private Logger _log;
	private GlobalProperties globalProperties;
	
	public RESTClient(String json, Logger _log, GlobalProperties globalProperties) {
		this.json = json;
		this._log = _log;
		this.globalProperties = globalProperties;
	}
	
	public void postSampling() {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		try {

		    HttpPost request = new HttpPost(globalProperties.getREST_API_STORE_SAMPLING_URL());
		    StringEntity requestEntity = new StringEntity(
		    		json,
		    	    ContentType.APPLICATION_JSON);
		    request.setEntity(requestEntity);
		    
		    _log.info("Posting REST storeSampling");
		    HttpResponse response = httpClient.execute(request);
		    HttpEntity entity = response.getEntity();
		    String responseString = EntityUtils.toString(entity, "UTF-8");
	    	_log.info(responseString);
		    //handle response here...

		}catch (Exception ex) {
		    _log.severe(ex.getMessage());
		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
	}

}

package br.com.sintechs.stufaSerialRead;

import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class RESTClient {
	
	String json;
	Logger _log;
	
	public RESTClient(String json, Logger _log) {
		this.json = json;
		this._log = _log;
	}
	
	public void postSampling() {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		try {

		    HttpPost request = new HttpPost("http://admin.sintechs.com.br:888/api/storeSampling");
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

		    //handle exception here

		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
	}

}

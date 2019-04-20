package br.com.sintechs.stufaSerialRead;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class RESTClient {
	
	String json;
	public RESTClient(String json) {
		this.json = json;
	}
	
	public void postSampling() {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		try {

		    HttpPost request = new HttpPost("http://admin.sintechs.com.br:888/api/storeSampling");
		    StringEntity params =new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
		    request.addHeader("content-type", "application/json");
		    request.addHeader("Content-Length", ""+json.length());  
		    
		    StringEntity requestEntity = new StringEntity(
		    		json,
		    	    ContentType.APPLICATION_JSON);
		    request.setEntity(requestEntity);
		    HttpResponse response = httpClient.execute(request);

		    //handle response here...

		}catch (Exception ex) {

		    //handle exception here

		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
	}

}

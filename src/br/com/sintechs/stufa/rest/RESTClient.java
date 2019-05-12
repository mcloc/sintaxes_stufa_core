package br.com.sintechs.stufa.rest;


import java.math.BigInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sintechs.stufa.GlobalProperties;
import br.com.sintechs.stufa.models.RuleEvent;
import br.com.sintechs.stufa.models.SintechsSampling;

public class RESTClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(RESTClient.class);
	private String json;
	private GlobalProperties globalProperties;
	
	public RESTClient(String json, GlobalProperties globalProperties) {
		this.json = json;
		this.globalProperties = globalProperties;
	}
	
	public RESTClient( GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

//	public RESTClient(JSONObject json_obj, GlobalProperties globalProperties) {
//		this.globalProperties = globalProperties;
//		this.json_obj = json_obj;
//	}

	public synchronized BigInteger postSampling() {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		 BigInteger sampling_id = null;
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
		    
	        // typecasting obj to JSONObject 
	        JSONObject jo = new JSONObject(responseString);
	        // getting firstName and lastName 
	        String status = jo.getString("status");
	        if(status.equals("OK")){
	        	JSONObject data = jo.getJSONObject("data");
	        	if(data == null)
	        		throw new Exception("no data found on response for storeSampling.");
	        	
	        	sampling_id = data.getBigInteger("sampling_id");
	        	LOGGER.info("sampling_id = " + sampling_id);
	        }else {
	        	throw new Exception("reponse for storeSampling is not OK. response: "+ status);
	        }
		    LOGGER.debug(responseString);
		   
			return sampling_id;
		    

		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
		
		return null;
	}
	
	public synchronized BigInteger postRuleEvent(JSONObject json_obj) {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		 BigInteger event_id = null;
		try {

		    HttpPost request = new HttpPost(globalProperties.getREST_API_STORE_RULE_EVENT_URL());
		    StringEntity requestEntity = new StringEntity(
		    		json_obj.toString(),
		    	    ContentType.APPLICATION_JSON);
		    request.setEntity(requestEntity);
		    
		    LOGGER.info("Posting REST storeRuleEvent");
		    HttpResponse response = httpClient.execute(request);
		    HttpEntity entity = response.getEntity();
		    String responseString = EntityUtils.toString(entity, "UTF-8");
		    
	        // typecasting obj to JSONObject 
	        JSONObject jo = new JSONObject(responseString);
	        // getting firstName and lastName 
	        String status = jo.getString("status");
	        if(status.equals("OK")){
	        	JSONObject data = jo.getJSONObject("data");
	        	if(data == null)
	        		throw new Exception("no data found on response for storeSampling.");
	        	
	        	event_id = data.getBigInteger("event_id");
	        	LOGGER.info("event_id = " + event_id);
	        }else {
	        	throw new Exception("reponse for storeSampling is not OK. response: "+ status);
	        }
		    LOGGER.debug(responseString);
		   
			return event_id;
		    

		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
		
		return null;
	}

	
	public synchronized SintechsSampling getSampling(BigInteger sampling_id) {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
		SintechsSampling sin_sampling = null;
		try {
		    HttpGet request = new HttpGet(globalProperties.getREST_API_GET_SAMPLING_URL()+"/"+sampling_id);
		    LOGGER.info("Posting GET getSampling : " + sampling_id);
		    HttpResponse response = httpClient.execute(request);
		    HttpEntity entity = response.getEntity();
		    String responseString = EntityUtils.toString(entity, "UTF-8");
		    
		    JSONObject json_obj = new JSONObject(responseString);
		    
		    sin_sampling = new SintechsSampling(json_obj, globalProperties);

		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
		
		return sin_sampling;
	}
	
	public synchronized RuleEvent getLastEventForSensor(String sensor_uuid) {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
		RuleEvent ev = null;
		JSONObject json_obj;
		try {
		    HttpGet request = new HttpGet(globalProperties.getREST_API_GET_LAST_SENSOR_EVENT_URL()+"/"+sensor_uuid);
		    LOGGER.info("GET getLastSensorEvent: " + sensor_uuid);
		    HttpResponse response = httpClient.execute(request);
		    HttpEntity entity = response.getEntity();
		    String responseString = EntityUtils.toString(entity, "UTF-8");
		    if(responseString != null) {
		    	json_obj = new JSONObject(responseString);
		    	ev = new RuleEvent(json_obj);
		    }
		    

		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
		
		return ev;
	}
}

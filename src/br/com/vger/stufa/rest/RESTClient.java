package br.com.vger.stufa.rest;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

import br.com.vger.stufa.GlobalProperties;
import br.com.vger.stufa.models.RuleEvent;
import br.com.vger.stufa.models.VgerActuator;
import br.com.vger.stufa.models.VgerModule;
import br.com.vger.stufa.models.VgerSampling;
import br.com.vger.stufa.models.VgerSensor;

public class RESTClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(RESTClient.class);
	private String json;
	private GlobalProperties globalProperties;

	public RESTClient(String json, GlobalProperties globalProperties) {
		this.json = json;
		this.globalProperties = globalProperties;
	}

	public RESTClient(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	// public RESTClient(JSONObject json_obj, GlobalProperties globalProperties) {
	// this.globalProperties = globalProperties;
	// this.json_obj = json_obj;
	// }

	public synchronized BigInteger postSampling() {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
		BigInteger sampling_id = null;
		try {

			HttpPost request = new HttpPost(globalProperties.getREST_API_STORE_SAMPLING_URL());
			StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
			request.setEntity(requestEntity);

			LOGGER.info("Posting REST storeSampling");
			HttpResponse response = httpClient.execute(request);
			while(response.getStatusLine().getStatusCode() == 429) {
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
				request.completed();
				request.releaseConnection();
				Thread.sleep(globalProperties.getREST_429_SLEEP());
				response = httpClient.execute(request);
			}
			
			Thread.sleep(globalProperties.getREST_RESPONSE_SLEEP());
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			// typecasting obj to JSONObject
			JSONObject jo = new JSONObject(responseString);
			request.completed();
			request.releaseConnection();
			// getting firstName and lastName
			String status = jo.getString("status");
			if (status.equals("OK")) {
				JSONObject data = jo.getJSONObject("data");
				if (data == null)
					throw new Exception("no data found on response for storeSampling.");

				sampling_id = data.getBigInteger("sampling_id");
				LOGGER.info("sampling_id = " + sampling_id);
			} else {
				throw new Exception("reponse for storeSampling is not OK. response: " + status);
			}
			LOGGER.debug(responseString);

			return sampling_id;

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			// Deprecated
			// httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	public synchronized BigInteger postRuleEvent(JSONObject json_obj) {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
		BigInteger event_id = null;
		try {

			HttpPost request = new HttpPost(globalProperties.getREST_API_STORE_RULE_EVENT_URL());
			StringEntity requestEntity = new StringEntity(json_obj.toString(), ContentType.APPLICATION_JSON);
			request.setEntity(requestEntity);

			LOGGER.info("Posting REST storeRuleEvent");
			HttpResponse response = httpClient.execute(request);
			while(response.getStatusLine().getStatusCode() == 429) {
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
				request.completed();
				request.releaseConnection();
				Thread.sleep(globalProperties.getREST_429_SLEEP());
				response = httpClient.execute(request);
			}
			Thread.sleep(globalProperties.getREST_RESPONSE_SLEEP());
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			// typecasting obj to JSONObject
			JSONObject jo = new JSONObject(responseString);
			request.completed();
			request.releaseConnection();
			// getting firstName and lastName
			String status = jo.getString("status");
			if (status.equals("OK")) {
				JSONObject data = jo.getJSONObject("data");
				if (data == null)
					throw new Exception("no data found on response for storeSampling.");

				event_id = data.getBigInteger("event_id");
				LOGGER.info("event_id = " + event_id);
			} else {
				throw new Exception("reponse for storeSampling is not OK. response: " + status);
			}
			LOGGER.debug(responseString);

			return event_id;

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			// Deprecated
			// httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

//	public synchronized VgerSampling getSampling(BigInteger sampling_id) {
//		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
//		VgerSampling sin_sampling = null;
//		try {
//			HttpGet request = new HttpGet(globalProperties.getREST_API_GET_SAMPLING_URL() + "/" + sampling_id);
//			LOGGER.info("Posting GET getSampling : " + sampling_id);
//			HttpResponse response = httpClient.execute(request);
//			HttpEntity entity = response.getEntity();
//			String responseString = EntityUtils.toString(entity, "UTF-8");
//
//			JSONObject json_obj = new JSONObject(responseString);
//
//			sin_sampling = new VgerSampling(json_obj, globalProperties);
//
//		} catch (Exception ex) {
//			LOGGER.error(ex.getMessage());
//		} finally {
//			// Deprecated
//			// httpClient.getConnectionManager().shutdown();
//		}
//
//		return sin_sampling;
//	}

	public synchronized RuleEvent getLastEventForSensor(String sensor_uuid) {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
		RuleEvent ev = null;
		JSONObject json_obj;
		try {
			HttpGet request = new HttpGet(globalProperties.getREST_API_GET_LAST_SENSOR_EVENT_URL() + "/" + sensor_uuid);
			LOGGER.debug("GET getLastSensorEvent: " + sensor_uuid);
			HttpResponse response = httpClient.execute(request);
			while(response.getStatusLine().getStatusCode() == 429) {
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
				request.completed();
				request.releaseConnection();
				Thread.sleep(globalProperties.getREST_429_SLEEP());
				response = httpClient.execute(request);
			}
			Thread.sleep(globalProperties.getREST_RESPONSE_SLEEP());
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			if (responseString != null) {
				// TODO: check response
				json_obj = new JSONObject(responseString);
				request.completed();
				request.releaseConnection();
				// ev = new RuleEvent(json_obj);
			}

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			// Deprecated
			// httpClient.getConnectionManager().shutdown();
		}

		return ev;
	}

	public synchronized int getModuleId(String module_name) {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
		Integer module_id = null;
		JSONObject json_obj;
		try {
			module_name = URLEncoder.encode(module_name, "UTF-8");
			HttpGet request = new HttpGet(globalProperties.getREST_API_GET_MODULE_ID_URL() + "/" + module_name);
			LOGGER.debug("GET getModuleId: " + module_name);
			HttpResponse response = httpClient.execute(request);
			while(response.getStatusLine().getStatusCode() == 429) {
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
				request.completed();
				request.releaseConnection();
				Thread.sleep(globalProperties.getREST_429_SLEEP());
				response = httpClient.execute(request);
			}
			Thread.sleep(globalProperties.getREST_RESPONSE_SLEEP());
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			if (responseString != null) {
				json_obj = new JSONObject(responseString);
				request.completed();
				request.releaseConnection();
				JSONObject data = null;
				try {
					data = json_obj.getJSONObject("data");
					if (data == null) {
						throw new Exception("data is NULL on getModuleId() for module: " + module_name);
					}
				} catch (Exception e) {
					String error = json_obj.getString("error");
					if (error != null)
						throw new Exception("error on getModuleId() for module: " + error);
				}
				module_id = data.getInt("module_id");
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			// Deprecated
			// httpClient.getConnectionManager().shutdown();
		}

		return module_id;
	}

	public synchronized  Integer getSensorId(String sensor_uuid) {
		Integer sensor_id = null;
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
		JSONObject json_obj;
		try {
			sensor_uuid = URLEncoder.encode(sensor_uuid, "UTF-8");
			HttpGet request = new HttpGet(globalProperties.getREST_API_GET_SENSOR_ID_URL() + "/" + sensor_uuid);
			LOGGER.debug("GET getSensorId: " + sensor_uuid);
			HttpResponse response = httpClient.execute(request);
			while(response.getStatusLine().getStatusCode() == 429) {
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
				request.completed();
				request.releaseConnection();
				Thread.sleep(globalProperties.getREST_429_SLEEP());
				response = httpClient.execute(request);
			}
			Thread.sleep(globalProperties.getREST_RESPONSE_SLEEP());
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			if (responseString != null) {
				json_obj = new JSONObject(responseString);
				request.completed();
				request.releaseConnection();
				JSONObject data = null;
				try {
					data = json_obj.getJSONObject("data");
					if (data == null) {
						throw new Exception("data is NULL on getSensorId() for module: " + sensor_uuid);
					}
				} catch (Exception e) {
					String error = json_obj.getString("error");
					if (error != null)
						throw new Exception("error on getSensorId() for module: " + error);
				}
				sensor_id = data.getInt("sensor_id");
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			// Deprecated
			// httpClient.getConnectionManager().shutdown();
		}

		return sensor_id;
	}

	public synchronized  VgerSensor getSensorByUUID(String sensor_uuid, VgerModule module) {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
		JSONObject json_obj;
		VgerSensor sensor = null;
		try {
			sensor_uuid = URLEncoder.encode(sensor_uuid, "UTF-8");
			HttpGet request = new HttpGet(globalProperties.getREST_API_GET_SENSOR_BY_UUID_URL() + "/" + sensor_uuid);
			LOGGER.debug("GET getSensorByUUID: " + sensor_uuid);
			HttpResponse response = httpClient.execute(request);
			while(response.getStatusLine().getStatusCode() == 429) {
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
				request.completed();
				request.releaseConnection();
				Thread.sleep(globalProperties.getREST_429_SLEEP());
				response = httpClient.execute(request);
			}
			Thread.sleep(globalProperties.getREST_RESPONSE_SLEEP());
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			if (responseString != null) {
				json_obj = new JSONObject(responseString);
				request.completed();
				request.releaseConnection();
				sensor = VgerSensor.hidrateFromModule(json_obj.getJSONObject("data"), module, globalProperties);
				return sensor;
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			// Deprecated
			// httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	public synchronized  VgerActuator getActuatorByUUID(String actuator_uuid, VgerModule module) {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
		JSONObject json_obj;
		VgerActuator actuator = null;
		try {
			actuator_uuid = URLEncoder.encode(actuator_uuid, "UTF-8");
			HttpGet request = new HttpGet(
					globalProperties.getREST_API_GET_ACTUATOR_BY_UUID_URL() + "/" + actuator_uuid);
			LOGGER.debug("GET getActuatorByUUID: " + actuator_uuid);
			HttpResponse response = httpClient.execute(request);
			while(response.getStatusLine().getStatusCode() == 429) {
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
				request.completed();
				request.releaseConnection();
				Thread.sleep(globalProperties.getREST_429_SLEEP());
				response = httpClient.execute(request);
			}
			Thread.sleep(globalProperties.getREST_RESPONSE_SLEEP());
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			if (responseString != null) {
				json_obj = new JSONObject(responseString);
				request.completed();
				request.releaseConnection();
				actuator = VgerActuator.hidrateFromModule(json_obj.getJSONObject("data"), module, globalProperties);
				return actuator;
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			
			// Deprecated
			// httpClient.getConnectionManager().shutdown();
		}

		return actuator;
	}

	public synchronized List<VgerModule> getModules() {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
		List<VgerModule> modules = new ArrayList<VgerModule>();
		try {
			HttpGet request = new HttpGet(globalProperties.getREST_API_GET_ACTIVE_MODULES_URL());
			HttpResponse response = httpClient.execute(request);
			while(response.getStatusLine().getStatusCode() == 429) {
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
				request.completed();
				request.releaseConnection();
				Thread.sleep(globalProperties.getREST_429_SLEEP());
				response = httpClient.execute(request);
			}
			Thread.sleep(globalProperties.getREST_RESPONSE_SLEEP());
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			JSONObject json_obj = new JSONObject(responseString);
			request.completed();
			request.releaseConnection();
			modules = VgerModule.hidrateMultiple(json_obj, globalProperties);
			LOGGER.info("GETing Active Modules: "+modules.size()+ " found");

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			// Deprecated
			// httpClient.getConnectionManager().shutdown();
		}

		return modules;
	}

	public synchronized VgerSampling getModuleSampling(VgerModule module) {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
		VgerSampling sampling = null;
		try {
			HttpGet request = new HttpGet(globalProperties.getREST_API_GET_MODULE_SAPMLING_URL()+"/"+ URLEncoder.encode(module.getName(), "UTF-8"));
			LOGGER.info("GETing Modules : "  + module.getName());
			HttpResponse response = httpClient.execute(request);
			while(response.getStatusLine().getStatusCode() == 429) {
				HttpEntity entity = response.getEntity();
				EntityUtils.consume(entity);
				request.completed();
				request.releaseConnection();
				Thread.sleep(globalProperties.getREST_429_SLEEP());
				response = httpClient.execute(request);
			}
			Thread.sleep(globalProperties.getREST_RESPONSE_SLEEP());
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			JSONObject json_obj = new JSONObject(responseString);
			request.completed();
			request.releaseConnection();

			sampling = VgerSampling.hidrateFromModule(json_obj, module, globalProperties);

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			// Deprecated
			// httpClient.getConnectionManager().shutdown();
		}

		return sampling;
	}
}

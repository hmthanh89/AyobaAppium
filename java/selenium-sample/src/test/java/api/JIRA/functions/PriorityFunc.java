package api.JIRA.functions;

import java.util.HashMap;
import java.util.List;

import api.JIRA.resources.PriorityApi;
import io.restassured.response.Response;

public class PriorityFunc {

	private PriorityApi api;
	
	public PriorityFunc() {
		api = new PriorityApi();
	}
	
	public List<HashMap<String, String>> getPriorities() {
		Response res = api.getPriorities();
		if (res.statusCode() != 200) {
			throw new RuntimeException("API - Could not get Priorities, the response is: " + res.prettyPrint());
		}
		
		List<HashMap<String, String>> priorities = res.jsonPath().getList(".");
		return priorities;
	}
}

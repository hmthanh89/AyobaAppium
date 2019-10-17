package api.JIRA.functions;

import java.util.HashMap;
import java.util.List;

import api.JIRA.resources.IssueTypeApi;
import io.restassured.response.Response;

public class IssueTypeFunc {
	
	private IssueTypeApi oAPI;
	
	public IssueTypeFunc() {
		oAPI = new IssueTypeApi();
	}
	
	public List<HashMap<String, String>> getIssueAllTypes() {
		
		Response res = oAPI.getIssueAllTypes();
		if (res.statusCode() != 200) 
			throw new RuntimeException("API - Could not get issues all types, the response is: " + res.prettyPrint());
		
		List<HashMap<String, String>> issueTypes = res.jsonPath().getList(".");
		return issueTypes;
	}
}

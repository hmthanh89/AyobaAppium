package api.JIRA.functions;

import api.JIRA.resources.ProjectApi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ProjectFunc {
	
	ProjectApi oAPI;
	public ProjectFunc() {
		oAPI = new ProjectApi();
	}
	
	public String getProjectKeyByName(String name) {
		
		Response res = oAPI.getAllProject();
		if (res.statusCode() != 200) return "";
		
		JsonPath json = res.jsonPath();
		
		for (int i=0;i<json.getList(".").size();i++)
			if (json.getString(String.format("[%s].name",i)).equals(name))
				return json.getString(String.format("[%s].key",i));
		return "";
	}

}

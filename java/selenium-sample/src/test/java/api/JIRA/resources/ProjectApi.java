package api.JIRA.resources;

import api_object.RequestMessage;
import io.restassured.response.Response;

public class ProjectApi extends BaseResource {
	
	public ProjectApi() {
		super();
	}
	
	public Response getAllProject() {
		try
		{
			RequestMessage oRequest = new RequestMessage();
			oRequest.setResources("project");
			return oWebService.GET(oRequest).getOriginalContent(Response.class); 
		}	
		catch(Exception e){}
		return null;
	}

}

package api.JIRA.resources;

import api.object.RequestMessage;
import io.restassured.response.Response;

public class SearchApi extends BaseResource 
{
	public SearchApi() {
		super();
	}
	
	public Response searchIssue(String jqlQuery) {
		
		try
		{
			RequestMessage oRequest = new RequestMessage();
			oRequest.setResources("search");
			oRequest.setQueryPrameter("jql", jqlQuery);
			return oWebService.GET(oRequest).getOriginalContent(Response.class); 
		}	
		catch(Exception e){}
		return null;
	
	}

}

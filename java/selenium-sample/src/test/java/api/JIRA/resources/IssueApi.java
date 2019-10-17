package api.JIRA.resources;

import api_object.RequestMessage;
import io.restassured.response.Response;

public class IssueApi extends BaseResource {
	
	public IssueApi()
	{
		super();
	}
	
	public Response getIssue(String sIssueIdOrKey, String fields, String expand, String properties, boolean updateHistory) {
		
		try
		{
			RequestMessage oRequest = new RequestMessage();
			oRequest.setResources("issue/{issueIdOrKey}");
			oRequest.setPathPrameter("issueIdOrKey", sIssueIdOrKey);
			oRequest.setQueryPrameter("fields", fields);
			oRequest.setQueryPrameter("expand", expand);
			oRequest.setQueryPrameter("properties", properties);
			oRequest.setQueryPrameter("updateHistory", updateHistory);
			return oWebService.GET(oRequest).getOriginalContent(Response.class); 
		}	
		catch(Exception e){}
		return null;
		
	}
	
	public Response deleteIssue(String sIssueIdOrKey) {
		
		try
		{
			RequestMessage oRequest = new RequestMessage();
			oRequest.setResources("issue/{issueIdOrKey}");
			oRequest.setPathPrameter("issueIdOrKey", sIssueIdOrKey);
			return oWebService.DELETE(oRequest).getOriginalContent(Response.class); 
		}	
		catch(Exception e){}
		return null;
		
	}
	
	public Response createIssue(String sBody) {
		try
		{
			RequestMessage oRequest = new RequestMessage();
			oRequest.setResources("issue");
			oRequest.setBody(sBody);
			return oWebService.POST(oRequest).getOriginalContent(Response.class); 
		}	
		catch(Exception e){}
		return null;
		
	}
}

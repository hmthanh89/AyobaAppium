package api.JIRA.resources;

import api.object.RequestMessage;
import io.restassured.response.Response;

public class IssueTypeApi extends BaseResource {

	public IssueTypeApi() {
		super();
	}

	public Response getIssueAllTypes() {
		try {
			RequestMessage oRequest = new RequestMessage();
			oRequest.setResources("issuetype");
			return oWebService.GET(oRequest).getOriginalContent(Response.class);
		} catch (Exception e) {
		}
		return null;

	}
}

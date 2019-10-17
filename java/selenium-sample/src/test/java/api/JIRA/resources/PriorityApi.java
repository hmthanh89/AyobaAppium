package api.JIRA.resources;

import api_object.RequestMessage;
import io.restassured.response.Response;

public class PriorityApi extends BaseResource {

	public PriorityApi() {
		super();
	}

	public Response getPriorities() {
		try {
			RequestMessage oRequest = new RequestMessage();
			oRequest.setResources("priority");
			return oWebService.GET(oRequest).getOriginalContent(Response.class);
		} catch (Exception e) {
		}
		return null;

	}
}

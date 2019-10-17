package api.JIRA.functions;

import api.JIRA.resources.SearchApi;
import io.restassured.response.Response;
import utils.helper.Logger;

public class SearchFunc {
    private SearchApi oApi;
	
	public SearchFunc() {
		oApi = new SearchApi();
	}
	
	public String searchIssueBySummay(String summary) {
		Response res = oApi.searchIssue(String.format("summary~'%s'", summary));
		if (res.statusCode() != 200) {
			Logger.info("Failed to search Issue. Status code: " + res.statusCode());
			return "";
		}
		return res.jsonPath().getString("issues[0].key");
	}

}

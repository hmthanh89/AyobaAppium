package api.JIRA.functions;

import api.JIRA.resources.IssueApi;
import data.JIRA.Issue;
import io.restassured.response.Response;

public class IssueFunc {

	private IssueApi oAPI;

	public IssueFunc() {
		oAPI = new IssueApi();
	}

	public boolean deleteIssue(String keyOrId) {

		Response oRes = oAPI.deleteIssue(keyOrId);
		return (oRes.statusCode() == 204);
	}

	public boolean createIssue(Issue oIssue) {

		ProjectFunc oProFunc = new ProjectFunc();
		String sProKey = oProFunc.getProjectKeyByName(oIssue.projectName);

		String sBody = "{\"fields\":{\"project\":{\"key\":\"%s\"},\"summary\":\"%s\",\"description\":\"%s\",\"issuetype\":{\"name\":\"%s\"}}}";
		sBody = String.format(sBody, sProKey, oIssue.summary, "", oIssue.issueType.getValue());

		Response oRes = oAPI.createIssue(sBody);
		if (oRes.statusCode() == 201) {
			oIssue.key = oRes.jsonPath().getString("key");
			return true;
		}
		return false;
	}

}

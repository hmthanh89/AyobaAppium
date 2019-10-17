package data.JIRA;

import data.JIRA.Enums.IssueType;
import data.JIRA.Enums.Priority;
import utils.common.Constants;

public class Issue {

	public String key;
	public String projectKey;
	public String projectName;
	public IssueType issueType;
	public Priority priority;
	public String summary;
	public String reporter;
	public String epicName;
	
	
	public void init(String projectName, IssueType issueType, String summary, String reporter, Priority priority) {
		this.projectKey = Constants.PROJECT_KEY;
		this.projectName = projectName;
		this.issueType = issueType;
		this.reporter = reporter;
		this.summary = summary;
		this.priority = priority;
	}
	
	public boolean isEmpty() {
		return projectName == null && issueType == null && summary == null;
	}
	
	public String toJsonString() {
		String body = "{\"fields\":{\"project\":{\"key\":\"%s\"},\"summary\":\"%s\",\"description\":\"%s\",\"issuetype\":{\"name\":\"%s\"},\"priority\":{\"id\":\"%s\"}}}";
		body = String.format(body, projectKey, summary, "", issueType.getValue(), priority.getId());
		return body;
	}
}


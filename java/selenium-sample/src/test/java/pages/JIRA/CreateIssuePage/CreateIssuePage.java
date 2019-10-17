package pages.JIRA.CreateIssuePage;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.Element;
import com.logigear.control.common.imp.TextBox;

import data.JIRA.Issue;
import pages.JIRA.GeneralPage.GeneralPage;
import pages.JIRA.GeneralPage.JIRAGeneralPage;
import utils.common.Common;
import utils.common.Constants;
import utils.custom.JiraSelect;

public class CreateIssuePage extends GeneralPage {
	private String xpathSeclectionProject = "//div[@id='project-suggestions']//a/em[text()='%s']";

	private JiraSelect issueType = $(JiraSelect.class, "issueType");
	private JiraSelect priority = $(JiraSelect.class, "priority");
	
	private Element eleLoading = $(Element.class, "eleLoading");
			
	private TextBox txtProject = $(TextBox.class, "txtProject");
	private TextBox txtSummary = $(TextBox.class, "txtSummary");
		
	private Button projectDropdown = $(Button.class, "projectDropdown");
	private Button btnCreate = $(Button.class, "btnCreate");
	
	private String path  = "Issues/Reported by Me";
	
	public CreateIssuePage(){
		super();
	}
	
	public void createIssue(Issue issue) {
		
		//Select Project name.
		projectDropdown.waitForElementClickable();
		projectDropdown.click();
		txtProject.enter(issue.projectName);
		
		Element seclectionProject = new Element(xpathSeclectionProject, issue.projectName);
		seclectionProject.waitForElementClickable();
		seclectionProject.click();
		Common.waitForPageRefresh(eleLoading, Constants.LOADING_TIME);
		
		//Select Issue Type
		issueType.selectValueByVisibleText(issue.issueType.getValue());
		Common.waitForPageRefresh(eleLoading, Constants.LOADING_TIME);
		
		//Enter Summary
		txtSummary.enter(issue.summary);
		Common.waitForPageRefresh(eleLoading, Constants.LOADING_TIME);
		
		priority.selectValueByVisibleText(issue.priority.getValue());

		btnCreate.click();
		popupSuccess.waitForDisplay();
	}
	
	public boolean navigateToMe(){
		if (!path.isEmpty()) JIRAGeneralPage.goToPage(path);//goToPage(path);
		return true;
	}
	
	@Override
	public boolean navigateToMe(String sPath){
		if (!sPath.isEmpty()) JIRAGeneralPage.goToPage(path);//goToPage(sPath);
		return true;
	}
	
}

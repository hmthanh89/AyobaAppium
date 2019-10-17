package pages.JIRA.SearchIssuePage;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.ComboBox;
import com.logigear.control.common.imp.Element;
import com.logigear.control.common.imp.Label;
import com.logigear.control.common.imp.Link;
import com.logigear.control.common.imp.TextBox;

import pages.JIRA.GeneralPage.GeneralPage;
import utils.common.Common;
import utils.common.Constants;

public class SearchIssuePage extends GeneralPage {

	private TextBox txtSearchQuery = $(TextBox.class, "txtSearchQuery");
	private TextBox txtSummaryVal = $(TextBox.class, "txtSummaryVal");
	private Button btnSearch = $(Button.class, "btnSearch");
	private ComboBox cmbWorkflow = $(ComboBox.class, "cmbWorkflow");
	private Element issueStatus =$(Element.class, "issueStatus");
	private Element issueList = $(Element.class, "issueList");
	private Element eleLoading = $(Element.class, "eleLoading");
	private Element eleIssueID = $(Element.class, "eleIssueID");
	private Link lnkModeBasic = $(Link.class, "lnkModeBasic");
	private Label lblTitleSearch = $(Label.class, "lblTitleSearch");
	private Label lblIssueHeader = $(Label.class, "lblIssueHeader");
	
	private String xpathIssueSummary = "//table[@id='issuetable']//td[@class='nav summary']//a[text()='%s']";
	private String xpathChangeIssueStatus = "//span[@class='trigger-label' and text()='%s']";

	public void searchBySummary(String summaryContent) {
		lblTitleSearch.waitForDisplay();
		if (!txtSearchQuery.isDisplayed()) {
			// Switching to Basic mode
			lnkModeBasic.click();
			txtSearchQuery.waitForDisplay();
		}
		
		txtSearchQuery.enter(summaryContent);
		btnSearch.click();
		Common.waitForPageRefresh(eleLoading, Constants.LOADING_TIME);
	}

	public int getIssueItemCount() {
		return issueList.getElements().size();
	}

	public String geIssueStatus() {
		popupSuccess.waitForElementNotPresent(Constants.SLEEP_TIME);
		return issueStatus.getText();

	}

	public boolean isSearchResultbySummaryCorrect(String summaryContent) {
		Element lstItem = new Element(xpathIssueSummary, summaryContent);
		return lstItem.getElements().size() > 0;
	}
	
	public String getIssueHeader() {
		return lblIssueHeader.getText();
	}

	public void selectIssueOnSearchPage(String summaryContent) {
		String IssueSummary = String.format(xpathIssueSummary, summaryContent);
		Link lnkIssueSummary = new Link(IssueSummary);
		lnkIssueSummary.click();
		Common.waitForPageRefresh(eleLoading, Constants.LOADING_TIME);
	}

	public String getIssueDislayed() {
		return txtSummaryVal.getText();
	}

	public String getIssueID() {
		eleIssueID.waitForDisplay();
		return eleIssueID.getText();
	}

	public void changeIssueStatus(String status) {
		Link lnkChangeStatusIssues = new Link(String.format(xpathChangeIssueStatus, status));
		switch (status) {
		case "To Do":
		case "In Progress":
			lnkChangeStatusIssues.click();
			popupSuccess.waitForPresent();
			break;
		case "In Review":
		case "Done":
		case "Resolved":
		case "Reopened":
		case "Closed":
			cmbWorkflow.click();
			lnkChangeStatusIssues.click();
			popupSuccess.waitForPresent();
			break;
		}
	}
	
}

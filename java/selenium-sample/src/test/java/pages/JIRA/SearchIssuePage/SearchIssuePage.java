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

	protected TextBox txtSearchQuery = $(TextBox.class, "txtSearchQuery");
	protected TextBox txtSummaryVal = $(TextBox.class, "txtSummaryVal");
	protected Button btnSearch = $(Button.class, "btnSearch");
	protected ComboBox cmbWorkflow = $(ComboBox.class, "cmbWorkflow");
	protected Element issueStatus =$(Element.class, "issueStatus");
	protected Element issueList = $(Element.class, "issueList");
	protected Element eleLoading = $(Element.class, "eleLoading");
	protected Element eleIssueID = $(Element.class, "eleIssueID");
	protected Link lnkModeBasic = $(Link.class, "lnkModeBasic");
	protected Label lblTitleSearch = $(Label.class, "lblTitleSearch");
	protected Label lblIssueHeader = $(Label.class, "lblIssueHeader");
	
	protected String xpathIssueSummary = "//table[@id='issuetable']//td[@class='nav summary']//a[text()='%s']";
	protected String xpathChangeIssueStatus = "//span[@class='trigger-label' and text()='%s']";

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

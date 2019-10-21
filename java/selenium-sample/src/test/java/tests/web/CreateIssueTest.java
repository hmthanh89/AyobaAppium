package tests.web;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.JIRA.functions.IssueFunc;
import api.JIRA.functions.SearchFunc;
import data.JIRA.Enums.IssueType;
import data.JIRA.Enums.Priority;
import data.JIRA.Issue;
import pages.JIRA.CreateIssuePage.CreateIssuePage;
import pages.JIRA.GeneralPage.GeneralPage;
import pages.JIRA.LoginPage.LoginPage;
import tests.WebTestBase;
import utils.common.Common;
import utils.common.Constants;
import utils.common.Util;
import utils.helper.Logger;
import utils.integration.Bug;

public class CreateIssueTest extends WebTestBase {
	
	LoginPage loginPage;
	CreateIssuePage createIssuePage;
	GeneralPage generalPage;
	SearchFunc apiSearch;
	IssueFunc apiIssue;
	
	@DataProvider
	public Object[][] getIssueData_TC01() {
		return new Object[][] { { new Issue() } };
	}
	
	@DataProvider(parallel = true)
	public Object[][] getIssueData_TC02() {
		Priority[] priorities = Priority.values();
		Object[][] issues = new Object[priorities.length][1];

		for (int i = 0; i < priorities.length; i++) {
			Issue issue = new Issue();
			issue.init(Constants.PROJECT_NAME, IssueType.BUG,
					"This is a bug with Priority is " + priorities[i].getValue(), Constants.EMAIL, priorities[i]);
			issues[i][0] = issue;
		}
		return issues;
	}

	@DataProvider(parallel = false)
	public Object[][] getIssueData_TC03() {
		List<String[]> data = Util.readCsvFile("src/test/resources/data/data.csv");
		String[] issueTypes = new String[data.size() - 1];
		String[] priorities = new String[data.size() - 1];

		for (int i=1;i<data.size()-1;i++) {
			issueTypes[i] = data.get(i)[0].toUpperCase().replace(" ", "_");
			priorities[i] = data.get(i)[1].toUpperCase();
		}
		
		List<List<Object>> combinedData = Util.combineData(Arrays.asList(
				issueTypes,
				priorities
				));
		
		Object[][] issues = new Object[combinedData.size()][1];

		for (int i = 0; i < combinedData.size(); i++) {
			IssueType issueType = IssueType.valueOf((String) combinedData.get(i).get(0));
			Priority priority = Priority.valueOf((String) combinedData.get(i).get(1));
			String summary = String.format("This is a %s with Priority is %s", issueType, priority);
			
			Issue issue = new Issue();
			issue.init(Constants.PROJECT_NAME, issueType,
					summary, Constants.EMAIL, priority);
			if (issueType == IssueType.EPIC)
				issue.epicName = "Epic name " + Common.randomString();
			issues[i][0] = issue;
		}
		return issues;
	}
	
	
	@AfterMethod
	public void cleanup(ITestResult result) {
		Logger.info("Clean up: Get then delete created issue by using API.");
		SearchFunc apiSearch = new SearchFunc();
		IssueFunc apiIssue = new IssueFunc();
		
		Issue issue = (Issue) result.getParameters()[0];
		String key = apiSearch.searchIssueBySummay(issue.summary);
		apiIssue.deleteIssue(key);
	}
	
	
	/* ============ TEST CASES ============== */
    //@Bug("CBR-8")
	@Test(description = "Create An Issue Successfully", dataProvider = "getIssueData_TC01")
	public void TC_01_Create_An_Issue(Issue issue) {

		if (issue.isEmpty()) {
			issue.init(Constants.PROJECT_NAME, IssueType.BUG,
					"There is a bug relating to my test " + Common.randomString(), 
					Constants.EMAIL, Priority.MEDIUM);
		}

		Logger.info("1.	Login to Jira");
		loginPage.login(Constants.USERNAME, Constants.PASSWORD);

		Logger.info("2. Click on 'Create' button to open Create Issue window");
		generalPage.openCreateIssueWindow();

		Logger.info("3. Create an issue");
		createIssuePage.createIssue(issue);

		Logger.verify("VP Pop up is displayed with content 'Issue .. has been successfully created.'");
		Assert.assertTrue(generalPage.isSuccessPopupDisplayed(), "The success popup does not display");
		//Assert.assertTrue(false);
	}
	
	
	@Test(description = "Create An Issue With Different Priority Successfully", dataProvider = "getIssueData_TC02")
	public void TC_02_Create_An_Issue_With_Different_Priority(Issue issue) {
		
		Logger.info("TC_02_Create_An_Issue_With_Different_Priority: " + issue.priority);
		
		this.TC_01_Create_An_Issue(issue);
	}

	
	@Test(description = "Create An Issue By Combining IssueType And Priority Successfully", dataProvider = "getIssueData_TC03")
	public void TC_03_Combination_IssueType_And_Priority(Issue issue) {
		
		Logger.info(String.format("TC_03_Combination_IssueType_And_Priority: %s vs %s",
				issue.issueType, issue.priority));
		
		this.TC_01_Create_An_Issue(issue);
	}

}

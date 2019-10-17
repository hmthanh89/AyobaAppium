package tests;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;

import com.logigear.driver.manager.Driver;

import api.JIRA.functions.IssueTypeFunc;
import api.JIRA.functions.PriorityFunc;
import data.JIRA.Enums.IssueType;
import data.JIRA.Enums.Priority;
import utils.base.TestClass;
import utils.common.Constants;
import utils.config.ModuleFactory;
import utils.helper.PropertiesHelper;

@Guice(modules = ModuleFactory.class)
public class WebTestBase extends TestClass {

	@Parameters({ "browser", "autoLogBug", "platform" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser, boolean autoLogBug, String platform, Method method, ITestContext context)
			throws Throwable {
		
		context.setAttribute("autoLogBug", autoLogBug);
		Driver.config(Constants.BROWSER_SETTING_FILE, platform, browser, method.getName());
		Driver.initDriver();
	
		Driver.setWaitForAjax(false);
		Driver.setPageLoadTimeOut(Constants.LONG_TIME);
		Driver.setTimeOut(Constants.SHORT_TIME);
		Driver.maximizeBrowser();
		Driver.navigate(PropertiesHelper.getPropValue("profile.url"));
		loadPageObject();
		initApiData();
	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp(ITestResult result) {
		// Do not change this function
		//Driver.quitBrowser(result.isSuccess());
		Driver.quit();
	}

	private void initApiData() {
		// apiJira = new JiraHandler();

		Iterable<HashMap<String, String>> issueTypes = new IssueTypeFunc().getIssueAllTypes();
		issueTypes.forEach(item -> updateIssueType(item));

		Iterable<HashMap<String, String>> priorities = new PriorityFunc().getPriorities();
		priorities.forEach(item -> updatePriority(item));
	}

	private void updateIssueType(HashMap<String, String> it) {
		try {
			IssueType tmp = IssueType.valueOf(it.get("name"));
			tmp.setId(Long.parseLong(it.get("id")));
		} catch (Exception e) {}
	}

	private void updatePriority(HashMap<String, String> it) {
		try {
			Priority tmp = Priority.valueOf(it.get("name"));
			tmp.setId(Long.parseLong(it.get("id")));
		} catch (Exception e) {}
	}
}

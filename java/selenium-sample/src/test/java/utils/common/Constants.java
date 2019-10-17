package utils.common;

import java.util.HashMap;
import java.util.Map;

import utils.helper.PropertiesHelper;

public class Constants {
	
	// Data folder controls json
	public static final String DATA_FOLDER = System.getProperty("user.dir") + "/src/test/resources/data/";

	public static final String BROWSER_SETTING_FILE = "src/test/resources/browsers.setting.properties";

	// Timeout variables
	public static final int LONG_TIME = 60;
	public static final int SHORT_TIME = 30;
	public static final int SLEEP_TIME = 3;
	public static final int LOADING_TIME = 2;

	// Username and password
	public static final String USERNAME = PropertiesHelper.getPropValue("profile.user.name");
	public static final String PASSWORD = PropertiesHelper.getPropValue("profile.user.password");
	public static final String EMAIL = PropertiesHelper.getPropValue("profile.user.email");
	public static final String HOST = PropertiesHelper.getPropValue("profile.api.host");
	public static final String BASE_PATH = PropertiesHelper.getPropValue("profile.api.basePath");

	// Create Issues variables
	public static final String ISSUE_TYPE = "Bug";
	public static final String PROJECT_KEY = PropertiesHelper.getPropValue("profile.project.key");
	public static final String PROJECT_NAME = PropertiesHelper.getPropValue("profile.project.name");
	
	public static Map<String, String> JIRA_CUSTOMFIELD = new HashMap<String, String>();
}

package tests;

import com.logigear.driver.manager.Driver;

import pages.Pinterest.GeneralPage.GeneralPage;
import utils.common.Constants;

public class DriverConfig extends GeneralPage {

	public static void loadAndroidNativeAppConfig() {
		Driver.setTimeOut(Constants.SHORT_TIME);
	}

	public static void loadWebBrowserConfig() {
		Driver.setPageLoadTimeOut(Constants.LONG_TIME);
		Driver.setTimeOut(Constants.SHORT_TIME);
		Driver.maximizeBrowser();
		Driver.navigate(Constants.URL);
	}

	public static void loadDriverConfig(String browser) {
		if (browser.equals("android.native")) {
			loadAndroidNativeAppConfig();
		} else {
			loadWebBrowserConfig();
		}
	}
}

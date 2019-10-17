package com.logigear.driver.browser.ie;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.logigear.driver.DriverProperty;
import com.logigear.driver.manager.BaseDriver;

public class LocalIEDriver extends BaseDriver {

	public LocalIEDriver(DriverProperty property) {
		super(property);
	}

	@Override
	public void createWebDriver() {
		System.setProperty("webdriver.ie.driver", property.getDriverExecutable());
		InternetExplorerOptions ops = new InternetExplorerOptions();
		
		ops.merge(property.getCapabilities());
		
		webDriver = new InternetExplorerDriver(ops);
	}
}

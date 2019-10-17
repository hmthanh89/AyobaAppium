package com.logigear.driver.browser.firefox;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.logigear.driver.DriverProperty;
import com.logigear.driver.manager.BaseDriver;

public class LocalFirefoxDriver extends BaseDriver {

	public LocalFirefoxDriver(DriverProperty property) {
		super(property);
	}

	@Override
	public void createWebDriver() {
		System.setProperty("webdriver.gecko.driver", property.getDriverExecutable());
		FirefoxOptions ops = new FirefoxOptions();
		
		ops.addArguments(property.getArguments());
		ops.merge(property.getCapabilities());
		
		webDriver = new FirefoxDriver(ops);
	}
}

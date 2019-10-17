package com.logigear.driver.browser.chrome;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.logigear.driver.DriverProperty;
import com.logigear.driver.manager.BaseDriver;

public class LocalChromeDriver extends BaseDriver {

	public LocalChromeDriver(DriverProperty property) {
		super(property);
	}

	@Override
	public void createWebDriver() {
		System.setProperty("webdriver.chrome.driver", property.getDriverExecutable());
		
		ChromeOptions ops = new ChromeOptions();

		ops.addArguments(property.getArguments());
		ops.merge(property.getCapabilities());
		
		webDriver = new ChromeDriver(ops);
	}
}

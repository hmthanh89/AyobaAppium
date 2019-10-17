package com.logigear.driver.browser.phantomjs;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.logigear.driver.DriverProperty;
import com.logigear.driver.manager.BaseDriver;

public class LocalPhantomJSDriver extends BaseDriver {

	public LocalPhantomJSDriver(DriverProperty property) {
		super(property);
	}

	@Override
	public void createWebDriver() {
		DesiredCapabilities caps = property.getCapabilities();

		if (caps == null)
			caps = new DesiredCapabilities();

		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, property.getDriverExecutable());

		webDriver = new PhantomJSDriver(caps);
	}

}

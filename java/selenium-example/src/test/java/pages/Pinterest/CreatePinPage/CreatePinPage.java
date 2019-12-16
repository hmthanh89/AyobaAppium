package pages.Pinterest.CreatePinPage;

import java.net.URL;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.TextBox;

import data.Pinterest.Pin;
import pages.Pinterest.GeneralPage.GeneralPage;
import utils.helper.Logger;

public class CreatePinPage extends GeneralPage {

	// Elements
	protected TextBox txtPinName = $(TextBox.class, "txtPinName");
	protected TextBox txtPinDescription = $(TextBox.class, "txtPinDescription");
	protected TextBox txtPinLink = $(TextBox.class, "txtPinLink");
	protected TextBox txtPinUpload = $(TextBox.class, "txtPinUpload");
	protected Button btnSave = $(Button.class, "btnSave");
	protected Button btnSeeItNow = $(Button.class, "btnSeeItNow");

	// Methods
	public void createPin(String pinName, String pinDescription, String pinLink, String pinFilePath) {
		txtPinName.waitForVisibility();
		
		txtPinUpload.enter(pinFilePath);
		txtPinName.enter(pinName);
		txtPinDescription.enter(pinDescription);
		
		if (!pinLink.equals("")) {
			try {
				new URL(pinLink);
				txtPinLink.enter(pinLink);
			} catch (Exception e) {
				Logger.warning("The input pin link not a valid URL.");
			}
		}
		btnSave.click();
		btnSeeItNow.waitForVisibility();
		btnSeeItNow.click();
	}

	public void createPin(Pin pin) {
		createPin(pin.getPinName(), pin.getNote(), pin.getLink(), pin.getImage());
	}
}

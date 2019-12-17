package pages.Pinterest.GeneralPage;

import com.logigear.control.common.imp.Element;

import utils.base.PageFactory;

public class GeneralPage extends PageFactory {

	// Elements
	protected Element eleProfile = $(Element.class, "GeneralPage.eleProfile");

	// Methods
	public void goToProfilePage() {
		eleProfile.waitForVisibility();
		eleProfile.click();
	}

}

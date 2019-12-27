package pages.Pinterest.BoardPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.logigear.control.common.IElement;
import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.Element;
import com.logigear.control.common.imp.Label;
import com.logigear.control.common.imp.TextBox;
import pages.Pinterest.GeneralPage.GeneralPage;

public class BoardPage extends GeneralPage {

	// Elements
	protected Label lblBoardName = $(Label.class, "lblBoardName");
	protected Button btnAdd = $(Button.class, "btnAdd");
	protected Element eleCreatePin = $(Element.class, "eleCreatePin");
	protected TextBox txtSearch = $(TextBox.class, "txtSearch");

	private IElement eleCarouselPin = new Element(
			"//div[@data-test-id='pinWrapper']//div[@data-test-id='carousel-pin']");
	private IElement eleDynamicCarouselPinItem = new Element(
			"(//div[@data-test-id='pinWrapper']//div[@data-test-id='carousel-pin'])[%s]//img[1]");
	private IElement eleCheckCarouselPin = new Element(
			"(//div[@data-test-id='pinWrapper']//img)[%s]/ancestor::div[@data-test-id='carousel-pin']");
	private IElement imgDynamicPin = new Element("(//div[@data-test-id='pinWrapper']//img)[%s]");
	private IElement imgTotalPin = new Element("//div[@data-test-id='pinWrapper']//img");

	// Methods
	public String getBoardName() {
		lblBoardName.waitForDisplay();
		return lblBoardName.getText();
	}

	public List<String> getPinsSource() {
		imgDynamicPin.setDynamicValue(1);
		imgDynamicPin.waitForDisplay();
		List<String> pinsSource = new ArrayList<String>();
		int totalCarousePin = eleCarouselPin.getElements().size();
		if (totalCarousePin != 0) {
			for (int i = 1; i <= totalCarousePin; i++) {
				eleDynamicCarouselPinItem.setDynamicValue(i);
				pinsSource.add(eleDynamicCarouselPinItem.getAttribute("src"));
			}
		}

		int totalPins = imgTotalPin.getElements().size();
		for (int i = 1; i <= totalPins; i++) {
			eleCheckCarouselPin.setDynamicValue(i);
			if (!eleCheckCarouselPin.isDisplayed()) {	
				imgDynamicPin.setDynamicValue(i);
				pinsSource.add(imgDynamicPin.getAttribute("src"));
			}
		}
		return pinsSource;
	}

	public boolean isBoardCreatedWithSelectedPins(String boardName, List<String> expectedPins) {
		List<String> recordArr = getPinsSource();
		Collections.sort(recordArr);
		Collections.sort(expectedPins);
		return (boardName.equals(getBoardName()) && expectedPins.equals(recordArr));
	}

	public void goToCreatePinPage() {
		btnAdd.waitForElementClickable();
		btnAdd.click();
		eleCreatePin.waitForVisibility();
		eleCreatePin.click();
	}

}

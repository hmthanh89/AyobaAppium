package pages.Pinterest.BoardPage;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.WebElement;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.Element;
import com.logigear.control.common.imp.Image;
import com.logigear.control.common.imp.Label;

import pages.Pinterest.GeneralPage.GeneralPage;

public class BoardPage extends GeneralPage {

	// Elements
	protected Label lblBoardName = $(Label.class, "lblBoardName");
	protected Image imgPins = $(Image.class, "imgPins");
	protected Button btnAdd = $(Button.class, "btnAdd");
	protected Element eleCreatePin = $(Element.class, "eleCreatePin");

	// Methods
	public String getBoardName() {
		return lblBoardName.getText();
	}

	public Set<String> getPinsSource() {
		imgPins.waitForVisibility();
		Set<String> pinsSource = new HashSet<String>();
		for (WebElement pin : imgPins.getElements()) {
			pinsSource.add(pin.getAttribute("src"));
		}
		return pinsSource;
	}

	public boolean isBoardCreatedWithSelectedPins(String boardName, Set<String> expectedPins) {
		return (boardName.equals(getBoardName()) && expectedPins.equals(getPinsSource()));
	}

	public void goToCreatePinPage() {
		btnAdd.waitForElementClickable();
		btnAdd.click();
		eleCreatePin.waitForVisibility();
		eleCreatePin.click();
	}
}

package pages.Pinterest.CreateBoardPage;

import java.util.HashSet;
import java.util.Set;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.CheckBox;
import com.logigear.control.common.imp.Element;
import com.logigear.control.common.imp.Image;
import com.logigear.control.common.imp.TextBox;

import data.Pinterest.Board;
import pages.Pinterest.GeneralPage.GeneralPage;
import utils.common.Common;
import utils.helper.Logger;

public class CreateBoardPage extends GeneralPage {

	// Dynamic-elements
	private String strImgPin = "(//div[@class='pinWrapper']//img)[%s]";
	private String strPin = "(//div[@class='withoutBoxShadow'])[%s]";

	protected Image imgPin(int index) {
		return new Image(strImgPin, index);
	}

	protected Element elePin(int index) {
		return new Element(strPin, index);
	}

	// Elements
	protected TextBox txtBoardName = $(TextBox.class, "txtBoardName");
	protected CheckBox chkboxVisibility = $(CheckBox.class, "chkboxVisibility");
	protected Button btnCreate = $(Button.class, "btnCreate");
	protected Button btnDone = $(Button.class, "btnDone");
	protected Image imgPins = $(Image.class, "imgPins");

	// Methods

	public void submitBoardGeneralInfo(String boardName, boolean visibility) {
		txtBoardName.waitForVisibility();
		txtBoardName.enter(boardName);
		chkboxVisibility.set(visibility);
		btnCreate.click();
		btnDone.waitForVisibility();
	}

	public void createBoard(String boardName, boolean visibility) {
		submitBoardGeneralInfo(boardName, visibility);
		btnDone.click();
	}

	public Set<String> createBoard(String boardName, boolean visibility, int pin) {
		Set<String> setPins = new HashSet<String>();

		// Input general info to create a board
		submitBoardGeneralInfo(boardName, visibility);

		// Select pins
		imgPins.waitForVisibility();
		int totalPins = imgPins.getElements().size();
		if (totalPins <= 0) {
			Logger.warning("No pin is displayed");
		} else {
			if (totalPins < pin) {
				pin = totalPins;
			}
			Set<Integer> selectedPins = new HashSet<Integer>();
			for (int i = 0; i < pin; i++) {
				int idx = Common.getRandomNumber(1, totalPins);
				while (selectedPins.contains(idx)) {
					idx = Common.getRandomNumber(1, totalPins);
				}
				selectedPins.add(idx);
				setPins.add(imgPin(idx).getSource());
				elePin(idx).click();
			}
		}
		// Complete create a board
		btnDone.click();
		return setPins;
	}

	public void createBoard(Board board) {
		createBoard(board.getBoardName(), board.isVisibility());
	}

	public Set<String> createBoardWithPins(Board board) {
		return createBoard(board.getBoardName(), board.isVisibility(), board.getPins());
	}

}

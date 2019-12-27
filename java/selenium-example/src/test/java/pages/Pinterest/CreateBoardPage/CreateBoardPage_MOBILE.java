package pages.Pinterest.CreateBoardPage;

import java.util.List;

import com.logigear.control.common.ITextBox;
import com.logigear.control.common.imp.TextBox;

import pages.Pinterest.BoardPage.BoardPage_MOBILE;

public class CreateBoardPage_MOBILE extends CreateBoardPage {

	BoardPage_MOBILE boardPage = new BoardPage_MOBILE();

	private ITextBox txtBoardNameAt1stTime = new TextBox("id=com.pinterest:id/board_name_et");

	@Override
	public void submitBoardGeneralInfo(String boardName, boolean visibility) {
		txtBoardName.waitForDisplay();
		if (txtBoardNameAt1stTime.isDisplayed()) {
			txtBoardNameAt1stTime.enter(boardName);
		} else {
			txtBoardName.enter(boardName);
			chkboxVisibility.set(visibility);
		}
		btnCreate.click();
		btnCreate.waitForInvisibility();
	}

	@Override
	public void createBoard(String boardName, boolean visibility) {
		submitBoardGeneralInfo(boardName, visibility);
	}

	@Override
	public List<String> createBoard(String boardName, boolean visibility, int pin) {
		submitBoardGeneralInfo(boardName, visibility);
		return boardPage.selectPins();
	}
}

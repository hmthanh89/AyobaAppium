package pages.JIRA.GeneralPage;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.Element;

import utils.base.PageFactory;

public class GeneralPage extends PageFactory {
	
	private String xpathSubSection = new String("//ul[@class='aui-list-truncate']/li/a[text()='%s']");
	private String xpathMainSection = new String("//ul[@class='aui-nav']/li/a[text()='%s']");
	
	private Element logoJira = $(Element.class, "GeneralPage.logoJira");
	public Element popupSuccess = $(Element.class, "GeneralPage.popupSuccess");
	public Element formCreateIssue = $(Element.class, "GeneralPage.formCreateIssue");
	public Button btnCreateIssue = $(Button.class, "GeneralPage.btnCreateIssue");
	
	public void goToPage(String menuPath) {
		if (menuPath.contains("/")) {
			logoJira.waitForDisplay();

			String[] parts = menuPath.split("/");
			Element finalXpathMainSection = new Element(xpathMainSection, parts[0]);
			Element finalXpathSubSection = new Element(xpathSubSection, parts[1]);

			finalXpathMainSection.waitForDisplay();
			finalXpathMainSection.click();
			finalXpathSubSection.waitForDisplay();
			finalXpathSubSection.click();
		} else {
			Element finalXpathMainSection = new Element(xpathMainSection, menuPath);

			finalXpathMainSection.waitForDisplay();
			finalXpathMainSection.click();
		}
	}
	
	public void openCreateIssueWindow() {
		btnCreateIssue.waitForElementClickable();
		btnCreateIssue.click();
		formCreateIssue.waitForDisplay();
	}
	
	public boolean isSuccessPopupDisplayed() {
		return popupSuccess.isDisplayed();
	}
	
	public String getPopupText() {
		return popupSuccess.getText();
	}
}

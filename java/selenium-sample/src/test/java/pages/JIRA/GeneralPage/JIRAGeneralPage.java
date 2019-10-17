package pages.JIRA.GeneralPage;

import com.logigear.control.common.imp.Element;

public class JIRAGeneralPage {
	private static String xpathSubSection = new String("//div[@class='aui-list']//li/a[text()='%s']");
	private static String xpathMainSection = new String("//ul[@id='main-nav']//li[./a[text()='%s']]/span/a");
	private static Element logoJira = new Element("//h1[@id='logo']//img");
	public static Element popupSuccess =new Element("//div[@class='global-msg']/div[contains(@class,'aui-message')]");
	
	public JIRAGeneralPage(){}
	
	public static void goToPage(String menuPath) {
		if (menuPath.contains("/")) {
			logoJira.waitForDisplay();

			String[] parts = menuPath.split("/");
			String part1 = parts[0];
			String part2 = parts[1];
			Element finalXpathMainSection = new Element(xpathMainSection, part1);
			Element finalXpathSubSection = new Element(xpathSubSection, part2);

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
	
	public static String getPopupText() {
		return popupSuccess.getText();
	}
}

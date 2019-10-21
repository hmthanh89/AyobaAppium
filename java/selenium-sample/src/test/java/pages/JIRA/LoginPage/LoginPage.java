package pages.JIRA.LoginPage;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.TextBox;

import pages.JIRA.GeneralPage.GeneralPage;

public class LoginPage extends GeneralPage {
	
	protected TextBox txtUsername = $(TextBox.class, "txtUsername");
	protected TextBox txtPassword = $(TextBox.class, "txtPassword");
	protected Button btnLogin = $(Button.class, "btnLogin");
	
	public void login(String username, String password) {
		txtUsername.waitForDisplay();
		txtUsername.enter(username);
		txtPassword.enter(password);
		btnLogin.click();
	}

}

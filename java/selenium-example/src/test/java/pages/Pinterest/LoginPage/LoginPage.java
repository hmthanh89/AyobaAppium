package pages.Pinterest.LoginPage;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.TextBox;

import utils.base.PageFactory;

public class LoginPage extends PageFactory {

	// Elements
	protected TextBox txtEmail = $(TextBox.class, "txtEmail");
	protected TextBox txtPassword = $(TextBox.class, "txtPassword");
	protected Button btnLogin = $(Button.class, "btnLogin");

	// Methods
	public void login(String email, String password) {
		txtEmail.waitForVisibility();
		txtEmail.enter(email);
		txtPassword.enter(password);
		btnLogin.click();
		btnLogin.waitForInvisibility();
	}

}

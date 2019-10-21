package pages.JIRA.LoginPage;

public class LoginPage_MOBILE  extends LoginPage {
	
	public LoginPage_MOBILE() {super();}
	
	@Override
	public void login(String username, String password) {
		System.out.println("This step is using for MOBILE mode");
		System.out.println("This control is using for MOBILE mode: " + btnLogin.getLocator().toString());
	}

}

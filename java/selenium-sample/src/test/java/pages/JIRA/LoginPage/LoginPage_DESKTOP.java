package pages.JIRA.LoginPage;

public class LoginPage_DESKTOP extends LoginPage {
	public LoginPage_DESKTOP() {super();}
	
	public void login(String username, String password) {
		System.out.println("This step is using for DESKTOP mode");
		super.login(username, password);
	}
}

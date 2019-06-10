using OpenQA.Selenium;
using SeleniumCSharp.Core.ElementWrapper;
using SeleniumCSharp.Core.Helpers;

namespace SeleniumTests.PageObject.Login
{
    public class TrelloLoginPageQA : ITrelloLoginPage
    {
        private readonly Button btnLogin;
        private readonly TextBox txtEmail;
        private readonly TextBox txtPassword;
        private readonly Label lblErrorMessage;
        private readonly Label lblTestLabel;

        public TrelloLoginPageQA()
        {            
            txtEmail = new TextBox("name=user");
            txtPassword = new TextBox("name=password");
            btnLogin = new Button("id=login");
            lblErrorMessage = new Label("css=#error>.error-message");
            lblTestLabel = new Label(By.Name("myname"));
        }

        public void Login(string user = null, string password = null)
        {
            if (user != null) txtEmail.SendKeys(user);

            if (password != null) txtPassword.SendKeys(password);
            btnLogin.Click();
        }

        public string GetErrorMessage()
        {
            lblErrorMessage.WaitForVisible();
            return lblErrorMessage.GetText();
        }
    }
}
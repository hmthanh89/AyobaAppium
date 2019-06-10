using OpenQA.Selenium;
using SeleniumCSharp.Core.ElementWrapper;
using SeleniumCSharp.Core.Helpers;

namespace SeleniumTests.PageObject.Login
{
    public class TrelloLoginPageDEV : ITrelloLoginPage
    {
        private readonly Button btnLogin;
        private readonly TextBox txtEmail;
        private readonly TextBox txtPassword;
        private readonly Label lblErrorMessage;
        private readonly Label lblTestLabel;

        public TrelloLoginPageDEV()
        {            
            txtEmail = new TextBox(Locator.Instance.Get("txtEmail"));
            txtPassword = new TextBox(Locator.Instance.Get("txtPassword"));
            btnLogin = new Button(Locator.Instance.Get("btnLogin"));
            lblErrorMessage = new Label(Locator.Instance.Get("lblErrorMessage"));
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
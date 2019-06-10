using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SeleniumTests.PageObject.Login
{
    public interface ITrelloLoginPage
    {
        void Login(string user = null, string password = null);
        string GetErrorMessage();
    }
}

using SeleniumTests.PageObject.Login;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SeleniumTests.Utilities
{

    public class PageFactory

    {

        public static T Get<T>()
        {
            var type = typeof(T);
            String fullName = type.FullName + Config.Env;
            type = Type.GetType(fullName);
            return (T)Activator.CreateInstance(type);
        }

    }
}

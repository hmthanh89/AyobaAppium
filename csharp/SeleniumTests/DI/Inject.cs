using Autofac;
using Autofac.Core;
using SeleniumTests.PageObject.Login;
using SeleniumTests.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SeleniumTests.DI
{
    public class Inject
    {
        private static IContainer container;
        public static void Bind(String env)
        {

            var builder = new ContainerBuilder();
            switch (env.ToLower())
            {
                case "qa":
                    builder.RegisterType<TrelloLoginPageQA>().As<ITrelloLoginPage>();
                    break;
                case "dev":
                    builder.RegisterType<TrelloLoginPageDEV>().As<ITrelloLoginPage>();
                    break;
                default:
                    break;
            }
            container = builder.Build();
        }


        public static TEntity Resolve<TEntity>()
        {
            return container.Resolve<TEntity>();
        }

        public static void Dispose()
        {
            container.Dispose();
        }
    }
}

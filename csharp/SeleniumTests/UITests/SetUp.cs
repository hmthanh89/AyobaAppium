using NUnit.Framework;
using SeleniumCSharp.Core.Helpers;
using SeleniumTests.DI;
using SeleniumTests.Utilities;

namespace SeleniumTests.Tests
{
    [SetUpFixture]
    public class SetUp
    {
        [OneTimeSetUp]
        public void BeforeAll()
        {
            Config.SetUIEnvVariables();
            Inject.Bind(Config.Env);
            ExtentManager.GetReporter();

            //Re-map the interface
            Locator.Instance.Load("\\Resources\\Selector.json");
            Locator.Instance.setMap("TrelloLoginPageDEV", "TrelloLoginPage");
            Locator.Instance.setMap("TrelloLoginPageQA", "TrelloLoginPage");            
        }

        [OneTimeTearDown]
        public void AfterAll()
        {
            ExtentManager.FlushReporter();
            Inject.Dispose();
        }
    }
}
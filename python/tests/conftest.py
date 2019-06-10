import pytest
import urllib3
import allure
from core import selenium
from allure_commons.types import AttachmentType
from tests.util import utils
from core.config.driver_config import DriverConfig

urllib3.disable_warnings()


def pytest_addoption(parser):
    parser.addoption("--conf-file", action="store",
                     help="Driver configuration file. Default is tests/configuration/driver/conf.json",
                     metavar="")
    parser.addoption("--conf-key", action="store",
                     help="Configuration key. It will be used to create new web driver. Default is chrome_browser",
                     metavar="")
    parser.addoption("--test-env", action="store",
                     help="Specify the test environment that you need to run on. "
                          "Default value is empty. Available value: qa",
                     metavar="")


def pytest_configure(config):
    pytest.conf_file = config.getoption("--conf-file", "../../tests/config/driver/conf.json", True)
    pytest.conf_key = config.getoption("--conf-key", "chrome_browser", True)
    pytest.test_env = config.getoption("--test-env", "", True)
    pytest.driver_conf = DriverConfig.get_driver_config(pytest.conf_key, pytest.conf_file)


@pytest.hookimpl(tryfirst=True, hookwrapper=True)
def pytest_runtest_makereport(item, call):
    # execute all other hooks to obtain the report object
    outcome = yield
    rep = outcome.get_result()

    # Add screen shot to allure report for test failed
    if call.when == "call":
        if rep.failed:
            file, name = utils.get_screenshot_path()
            selenium.save_screenshot(file)
            allure.attach.file(file, attachment_type=AttachmentType.PNG)

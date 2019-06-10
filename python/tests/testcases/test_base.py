import pytest
from core import selenium
from core.config.driver_config import DriverConfig


class TestBase:

    @pytest.fixture(scope="module", autouse=True)
    def setup(self):
        selenium.start_driver(pytest.driver_conf)

        # Close all browsers when tests have been finished
        yield
        selenium.quit_all()

from tests.testcases.test_base import TestBase
from tests.pages.google_home_page import GoogleHomePage
from pytest_testrail.plugin import pytestrail
from tests.util.page_injector import get_page
from tests.util.soft_assert import expect, expect_with_screenshot, assert_all
import logging
import pytest
import allure


class TestGoogleSearch(TestBase):
    google_home = get_page(GoogleHomePage)

    @pytest.mark.smoke
    @allure.feature('smoke')
    @pytestrail.case('C2')
    def test_search_001(self):
        logging.info("1. Open google page")
        self.google_home.open_google()
        logging.info("2. Search")
        self.google_home.search("hello selenium")
        logging.info("3. Verify result")
        assert self.google_home.get_searched_value() == "hello selenium"

    def test_soft_assertion(self):
        expect(1 == 2, 'one is two')
        expect_with_screenshot(1 == 3, 'one is three')
        assert_all()

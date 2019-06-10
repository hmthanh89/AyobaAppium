import time
from selenium.common.exceptions import StaleElementReferenceException
from core.config import default_config


def wait_until(condition, error=None, timeout=None, polling=None):
    if timeout is None:
        timeout = default_config.timeout
    if polling is None:
        polling = default_config.poll_during_waits
    max_time = time.time() + timeout
    not_found = None
    while time.time() < max_time:
        try:
            if condition():
                return
        except StaleElementReferenceException as err:
            not_found = err
        else:
            not_found = None
        time.sleep(polling)
    raise RuntimeError(not_found or error)

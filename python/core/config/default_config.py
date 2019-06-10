from core.config import constants
from core.util.helper import get_env

timeout = int(get_env(constants.SELENPY_TIMEOUT,
                      constants.SELENPY_DEFAULT_TIMEOUT))

poll_during_waits = float(get_env(constants.SELENPY_POLL_DURING_WAITS,
                                  constants.SELENPY_POLL_DURING_DEFAULT_WAITS))

override_driver = bool(get_env(constants.SELENPY_OVERRIDE_DRIVER, constants.SELENPY_OVERRIDE_DRIVER_DEFAULT))

element_caching = bool(get_env(constants.SELENPY_ELEMENT_CACHING, constants.SELENPY_ELEMENT_CACHING_DEFAULT))

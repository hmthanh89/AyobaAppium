import uuid
from tests.util import constants


def get_file_name(extension='.png'):
    return str(uuid.uuid4()) + extension


def get_screenshot_path(extension='.png'):
    name = get_file_name(extension)
    return constants.SCREENSHOT_FOLDER + name, name

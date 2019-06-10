import os


def get_env(key, default=None):
    try:
        return os.environ.get(key, default)
    except KeyError:
        return None


PATH = lambda p: os.path.abspath(
    os.path.join(os.path.dirname(__file__), p)
)

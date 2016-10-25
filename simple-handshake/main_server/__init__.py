from main_server import auth_server
from define import define


def init():
    server = auth_server.AuthenticatorServer(define.PORT)
    server.start()

if __name__ == '__main__':
    init()

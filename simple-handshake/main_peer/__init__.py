from main_peer import peer
from define import define


def init():
    client = peer.Peer(define.ADDRESS, define.PORT)
    client.start()

if __name__ == '__main__':
    init()

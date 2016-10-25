import socket


class ClientSocket:

    s = None

    def __init__(self):
        self.create_socket()

    def create_socket(self):
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def connect_to_server(self, address, port):
        self.s.connect((address, port))

    def send(self, message):
        self.s.send(message)

    def receive(self, size):
        return self.s.recv(size);

    def close_socket(self):
        self.s.close()

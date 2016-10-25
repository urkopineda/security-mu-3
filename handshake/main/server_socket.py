import socket


class ServerSocket:

    s = None

    def __init__(self, port):
        self.create_socket(port)

    def create_socket(self, port):
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.s.bind(('localhost', port))
        self.s.listen(1)

    def wait_connection(self):
        connection, address = self.s.accept()
        return [connection, address]

    def send(self, message):
        self.s.send(message)

    def receive(self, size):
        return self.s.recv(size)

    def close(self):
        self.s.close()

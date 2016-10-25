import struct


class Message:

    code = None
    identifier = None
    data = None

    header_len = 4

    def __init__(self, socket):
        header = bytearray()
        while len(header) < self.header_len:
            chunk = socket.receive(self.header_len - len(header))
            if chunk == '':
                raise RuntimeError("Socket connection broken!")
            header = header + chunk
        (code, identifier, length) = struct.unpack('!BBH', header)
        packet = header
        while len(packet) < length:
            chunk = socket.receive(length - len(packet))
            if chunk == '':
                raise RuntimeError("Socket connection broken!")
            packet = packet + chunk
        (code, identifier, length, data) = struct.unpack('!BBH' + str(length - self.header_len) + 's', packet)
        self.code = code
        self.identifier = identifier
        self.data = data

    def get_package(self):
        data_len = len(self.data)
        packet_len = self.header_len + data_len
        pack_format = '!BBH' + str(data_len) + 's'
        packet = struct.pack(pack_format, self.code, self.identifier, packet_len, self.data)
        return packet

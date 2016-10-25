from define import define
from package import package as pkg


class Challenge:

    identifier = None
    name = None
    data = None

    def __init__(self, identifier, name, data):
        self.identifier = identifier
        self.name = name
        self.data = data

    def to_packet(self):
        packet_length = define.HEADER_LENGTH + 1 + len(self.data) + len(self.name)
        pack = pkg.Package(define.PACKET_FORMAT,
                           packet_length,
                           define.CHALLENGE_CODE,
                           self.name,
                           len(self.data),
                           self.data)
        return pack.to_struct_object()

import struct


class Package:

    pkg_format = None
    pkg_length = None
    challenge_type = None
    name = None
    data_length = None
    data = None

    def __init__(self,
                 pkg_format,
                 pkg_length,
                 challenge_type,
                 name,
                 data_length,
                 data):
        self.pkg_format = pkg_format
        self.pkg_length = pkg_length
        self.challenge_type = challenge_type
        self.name = name
        self.data_length = data_length
        self.data = data

    def to_struct_object(self):
        return struct.pack(self.pkg_format,
                           self.challenge_type,
                           self.pkg_length,
                           self.data_length,
                           self.data,
                           self.name)

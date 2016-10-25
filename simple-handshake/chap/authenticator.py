class Authenticator:

    counter_challenge = 0
    secret_code = None

    def __init__(self, secret_code):
        self.secret_code = secret_code

    def create_challenge(self, abc):
        pass

    def create_response(self, challenge, abc):
        pass

    def validate_response(self, response, challenge):
        pass

from main import server_socket
from main import server_auth
from main import message

socket_number = 60000


def main():
    print("Handshake exercise: SERVER")
    print("   Creating socket...")
    server = server_socket.ServerSocket(socket_number)
    print("   OK!")
    print("   Waiting to the client...")
    server.wait_connection()
    print("   OK!")
    authen = server_auth.ServerAuth()
    packet = message.Message(server.s)

    if packet.code == authen.AUTH_REQUEST_CODE:


    server.close()

if __name__ == '__main__':
    main()

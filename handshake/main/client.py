from main import client_socket

socket_number = 60000


def main():
    client = client_socket.ClientSocket()
    print("Handshake exercise: CLIENT")
    address = input("   Enter the address of the server: ")
    client.connect_to_server(address, socket_number)
    while 1:
        message = input("Enter the message you want to send (type exit to finish the program): ")
        if message == 'exit':
            break
        client.send(message)

if __name__ == '__main__':
    main()

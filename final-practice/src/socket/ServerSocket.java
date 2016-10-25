package socket;

import java.io.IOException;
import java.net.*;

/**
 * Created by urko on 18/02/16.
 */
public class ServerSocket {

    private java.net.ServerSocket socketTCP;
    private DatagramSocket socketUDP;
    private int port;

    public ServerSocket(int port) {
        this.port = port;
    }

    // ######################## TCP ########################

    public void tcpConnect() {
        try {
            socketTCP = new java.net.ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket tcpWaitConnection() {
        System.out.print("Waiting connections... ");
        try {
            socketTCP.setSoTimeout(15000);
            return socketTCP.accept();
        } catch (SocketTimeoutException e) {
            System.out.println("Timeout. Bye!");
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }

    public void tcpClose() {
        try {
            socketTCP.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ######################## UDP ########################

    public void udpConnect() {
        try {
            socketUDP = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public DatagramPacket udpWaitCommand(int bufferSize) {
        byte [] buffer = new byte[bufferSize];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length)
        try {
            socketUDP.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } return packet;
    }

    public void udpSend(String message, DatagramPacket packet) {
        DatagramPacket response = new DatagramPacket(message.getBytes(), message.getBytes().length,
                                                     packet.getAddress(), packet.getPort());
        try {
            socketUDP.send(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by urko on 18/02/16.
 */
public class ClientSocket {

    private String address;
    private int socket;

    private Socket s;
    private BufferedReader input;
    private PrintWriter output;

    public ClientSocket(String address, int socket) {
        this.address = address;
        this.socket = socket;
    }

    // ######################## TCP ########################

    public void connect() {
        try {
            s = new Socket(address, socket);
            output = new PrintWriter(s.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            System.out.print("ERROR: " + e.getMessage());
            return;
        } System.out.println("Connected!");
    }

    public void send(String message) {
        output.println(message);
        output.flush();
    }

    public String receive() {
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }

    public void close() {
        try {
            s.close();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

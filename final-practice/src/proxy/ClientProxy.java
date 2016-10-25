package proxy;

import calc.Calculator;
import socket.ClientSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by urko on 15/02/16.
 */
public class ClientProxy implements Calculator {

    private Scanner keyboard;

    private ClientSocket socket;

    public ClientProxy(String address, int socket) {
        this.socket = new ClientSocket(address, socket);
        keyboard = new Scanner(System.in);
    }

    public void start() {
        System.out.println("WELCOME!");
        System.out.println("Type 'exit' to quit the program.");
        System.out.print("Connecting to the server... ");
        socket.connect();
        String command;
        do {
            System.out.print("What do you want me to calculate? (GCD or LCM): ");
            command = keyboard.nextLine();
            long x, y;
            if (!command.equals("exit")) {
                try {
                    System.out.print("  Give me the first number: ");
                    x = keyboard.nextLong();
                    keyboard.nextLine();
                    System.out.print("  Give me the second number: ");
                    y = keyboard.nextLong();
                    keyboard.nextLine();
                    switch (command.toLowerCase()) {
                        case "gcd":
                            System.out.println("  The result is: " + gdc(x, y));
                            break;
                        case "lcm":
                            System.out.println("  The result is: " + lcm(x, y));
                            break;
                        case "exit":
                            break;
                        default:
                            System.out.println("ERROR: Invalid command.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("ERROR: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("ERROR: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            } else {
                closeServer();
            }
        } while (!command.equals("exit"));
        socket.close();
        System.out.println("Bye!");
    }

    @Override
    public long gdc(long x, long y) throws IOException {
        socket.send("gdc(" + x + "," + y + ")$");
        return Long.parseLong(socket.receive());
    }

    @Override
    public long lcm(long x, long y) throws IOException {
        socket.send("lcm(" + x + "," + y + ")$");
        return Long.parseLong(socket.receive());
    }

    private void closeServer() {
        socket.send("null");
    }

}

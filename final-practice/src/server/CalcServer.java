package server;

import calc.Functions;
import data.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by urko on 15/02/16.
 */
public class CalcServer extends Thread {

    private Socket s;

    private PrintWriter output;
    private BufferedReader input;

    public CalcServer(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        openCommunications();
        boolean result;
        do {
            result = calculate(new Message(waitForCommand()));
        } while (result);
        close();
    }

    private void openCommunications() {
        try {
            output = new PrintWriter(s.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            return;
        }
    }

    private String waitForCommand() {
        String command = "";
        try {
            boolean hasEnded;
            do {
                command += input.readLine();
                hasEnded = command.contains("$");
            } while (!hasEnded);
            return command;
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }

    private boolean calculate(Message msg) {
        if (msg.getType() == null) {
            return false;
        }
        Functions fun = new Functions();
        String result = null;
        switch (msg.getType()) {
            case "gdc":
                result = String.valueOf(fun.gcd(msg.getX(), msg.getY()));
                break;
            case "lcm":
                result = String.valueOf(fun.lcm(msg.getX(), msg.getY()));
                break;
            default:
        }
        output.println(result);
        output.flush();
        return true;
    }

    private void close() {
        try {
            output.close();
            input.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}

package proxy;

import server.CalcServer;
import socket.ServerSocket;

import java.util.ArrayList;

/**
 * Created by urko on 15/02/16.
 */
public class ServerProxy {

    private ServerSocket s;
    private ArrayList<CalcServer> array;

    public ServerProxy(int port) {
        s = new ServerSocket(port);
        array = new ArrayList<>();
    }

    public void start() {
        s.udpConnect();
        boolean isSomeoneAlive = true;
        while (isSomeoneAlive) {
            CalcServer calc = new CalcServer(s.waitConnection());
            if (!s.equals(null)) {
                System.out.println("Client connected!");
                array.add(calc);
                calc.start();
            } else {
                isSomeoneAlive = false;
                for (int i = 0; i < array.size(); i++) {
                    CalcServer c = array.get(i);
                    if (c.isAlive()) isSomeoneAlive = true;
                }
                if (!isSomeoneAlive) return;
            }
        }
    }

    private boolean checkThreads() {
        for (int i = 0; i < array.size(); i++) {
            CalcServer c = array.get(i);
            if (!c.getState().equals(Thread.State.TERMINATED)) return true;
        } return false;
    }

}

package main;

import proxy.ServerProxy;

/**
 * Created by urko on 15/02/16.
 */
public class Server {

    public static void main(String [] args) {
        ServerProxy server = new ServerProxy(Integer.parseInt(args[0]));
        server.start();
    }

}

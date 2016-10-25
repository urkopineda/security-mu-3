package main;

import proxy.ClientProxy;

/**
 * Created by urko on 15/02/16.
 */
public class Client {

    public static void main(String [] args) {
        ClientProxy c = new ClientProxy(args[0], Integer.parseInt(args[1]));
        c.start();
    }

}

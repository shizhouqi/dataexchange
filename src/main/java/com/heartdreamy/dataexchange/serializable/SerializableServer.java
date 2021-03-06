package com.heartdreamy.dataexchange.serializable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SerializableServer {
    private static int DEFAULT_PORT = 22222;
    private static ServerSocket server;

    public static void main(String[] args){
        start();
    }

    public static void start() {
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int port) {

        try {
            server = new ServerSocket(port);
            System.out.println("server is start, port:" + port);
            Socket socket = server.accept();
            new Thread(new SerializableServerHandler(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != server) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("server is closed.");
                server = null;
            }
        }

    }

}

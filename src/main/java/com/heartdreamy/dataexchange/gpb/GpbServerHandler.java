package com.heartdreamy.dataexchange.gpb;

import com.google.protobuf.ByteString;
import com.heartdreamy.dataexchange.UserOuterClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GpbServerHandler implements Runnable {
    private Socket socket;

    public GpbServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader reader = null;
        try {
            ByteString bs = ByteString.readFrom(socket.getInputStream());
            System.out.println("server--received message size:" +bs.size());
            UserOuterClass.User user = UserOuterClass.User.parseFrom(bs);
            System.out.println("server--received message:" + user);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

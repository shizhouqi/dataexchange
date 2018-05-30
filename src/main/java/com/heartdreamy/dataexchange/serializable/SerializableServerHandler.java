package com.heartdreamy.dataexchange.serializable;

import com.heartdreamy.dataexchange.User;

import java.io.*;
import java.net.Socket;

public class SerializableServerHandler implements Runnable {
    private Socket socket;

    public SerializableServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readStr;
            readStr = reader.readLine();
            if (null != readStr) {
                System.out.println("server--received message size:" + readStr.length());
            }
            ByteArrayInputStream byteIn = new ByteArrayInputStream(readStr.getBytes("ISO-8859-1"));
            ObjectInputStream objIn = new ObjectInputStream(byteIn);
            try {
                User obj =(User)objIn.readObject();
                System.out.println("server--received message:" + obj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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
